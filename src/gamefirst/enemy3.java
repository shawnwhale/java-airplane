package gamefirst;

import java.awt.Graphics;
import java.awt.Image;

public class enemy3 extends object{
	int degree;
	int count=0;
	int life=3;
	public boolean live=true;
	public int move(Graphics g) {
		if(live) {
		switch(degree) {
		case 1:
			x+=speed;
			break;
		case 2:
			x-=speed;
			break;
		case 3:
			y+=speed;
			break;
		case 4:
			y-=speed;
		}
		 if(y>Edge.y-25){
	    		degree = 4;
	    		}
	    if(x>Edge.x-25){
	    		degree = 2;
	    		}
	    if(x<0){
    		degree = 1;
    			}
	    if(y<0) {
	    	live=false;
	    	}
	    g.drawImage(img,(int)x,(int)y,null);
	    for(int i=0;i<frame.shellList.size();i++){
            shell b =  frame.shellList.get(i);
            if(b.live) {
            boolean peng = b.getRect().intersects(this.getRect());
            if(peng) {
            	this.life-=1;
            	b.live=false;
            	if(this.life<=0) {
            		this.live=false;
            		return 40;
            	}
            	frame.shell2List.remove(b);
            
            		}
            	}
	    	}
	   
		}
		return 0;
		
	   
	}
	
	public shell2 shoot() {
		        return new shell2(this);

	}
	
	 public enemy3(Image img, double x, double y,int speed) {
	        super(img,x,y);
	        this.speed=speed;
	        super.img=img;
	        degree=(int)(Math.random()*3)+1;
	    }
}
