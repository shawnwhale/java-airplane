package gamefirst;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Date;

public class Plane extends object{
	boolean left,up,right,down,shoot,boom;
	boolean live=true;
	long shoottime=0,sectime=0;
	long boomtime=0,secboom=0;
	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=true;
			break;
		case KeyEvent.VK_UP:
			up=true;
			break;
		case KeyEvent.VK_RIGHT:
			right=true;
			break;
		case KeyEvent.VK_DOWN:
			down=true;
			break;
		case KeyEvent.VK_Z:
			shoot=true;
			sectime=System.currentTimeMillis();
			break;
		case KeyEvent.VK_SPACE:
			boom=true;
			secboom=System.currentTimeMillis();
			break;
		default:
			break;
		}
	}
	
	public void minusDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left=false;
			break;
		case KeyEvent.VK_UP:
			up=false;
			break;
		case KeyEvent.VK_RIGHT:
			right=false;
			break;
		case KeyEvent.VK_DOWN:
			down=false;
			break;
		case KeyEvent.VK_Z:
			shoot=false;
			break;
		case KeyEvent.VK_SPACE:
			boom=true;
			break;
		default:
			break;
		}
	}
	
	 public void drawself(Graphics g) {
		 	if(live) {
	        super.drawself(g);
	        if(left) {
	        	x-=speed;
	        	Edge.edge(this);
	        }
	        if(right) {
	        	x+=speed;
	        	Edge.edge(this);
	        }
	        if(up) {
	        	y-=speed;
	        	Edge.edge(this);
	        }
	        if(down) {
	        	y+=speed;
	        	Edge.edge(this);
	        }
	        if(shoot) {
	        	if(sectime-shoottime>300) {
	        		shell b=new shell(this);
	        		frame.shellList.add(b);
	        		shoottime=sectime;
	        	}
	        }
	        if(boom) {
	        	if(frame.boomnumber>0&&secboom-boomtime>500) {
	        		frame.boomnumber-=1;
	        		boomtime=secboom;
	        		boomf();
	        	}
	        }
	        for(int i=0;i<frame.enemyList.size();i++){
	            enemy b =  frame.enemyList.get(i);
	            boolean peng = b.getRect().intersects(this.getRect());
	            if(peng) {
	            	this.live=false;
	            }
		    	}
		 }
	    }
	     
	    public Plane(Image img, double x, double y,int speed) {
	        super(img,x,y);
	        this.speed=speed;
	    }
	    
	    public void boomf() {
	    	for(int i=0;i<frame.shell2List.size();i++){
	            shell2 b =  frame.shell2List.get(i);
	            b.live=false;
	            
	        }
	    	for(int i=0;i<50;i++) {
	    		glass b=new glass(this);
	    		frame.glassList.add(b);
	    	}
	    }
	   
}
