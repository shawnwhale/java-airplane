package gamefirst;


import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;


public class frame  extends  Frame {
	 	Image bgImg = GameUtil.getImage("image/bg.jpg");
	    Image planeImg = GameUtil.getImage("image/plane.png");
	    Image enemyImg= GameUtil.getImage("image/enemy.jpg");
	    Image enemy2Img= GameUtil.getImage("image/long.png");
	    Image enemy3Img= GameUtil.getImage("image/enemy3.jpg");
	    Plane plane = new Plane(planeImg,400,400,12);
	    static ArrayList<shell> shellList=new ArrayList<shell>();
	    static ArrayList<shell2> shell2List=new ArrayList<shell2>();
	    static ArrayList<enemy> enemyList=new ArrayList<enemy>();
	    static ArrayList<enemy2> enemy2List=new ArrayList<enemy2>();
	    static ArrayList<enemy3> enemy3List=new ArrayList<enemy3>();
	    static ArrayList<glass> glassList=new ArrayList<glass>();
	    long startTime = System.currentTimeMillis();    //游戏起始时刻
	    long endTime;  //游戏结束时刻
	    int period=0;
	    int score=0;
	    int oldscore=100;
	    int level=0;
	    static int boomnumber=1;
	    
	    private Image offScreenImage = null;  
	    public void update(Graphics g) {
	        if(offScreenImage == null)
	            offScreenImage = this.createImage(600,600);//这是游戏窗口的宽度和高度
	         
	        Graphics gOff = offScreenImage.getGraphics();
	        paint(gOff);
	        g.drawImage(offScreenImage, 0, 0, null);
	    }  
	    
	  public void paint(Graphics g) {  
	        g.drawImage(bgImg, 0, 0, null);
	        plane.drawself(g);    //画出飞机本身
	        printInfo(g, "得分："+score+"分", 20, 10, 565, Color.white);
	        printInfo(g, "净化新星："+boomnumber+"个", 20, 400, 565, Color.white);
	        for(int i=0;i<shellList.size();i++){
	            shell b =  shellList.get(i);
	            b.draw(g);
//	            boolean peng = b.getRect().intersects(plane.getRect());
//	            if(peng){
//	                plane.live = false;   //飞机死掉,画面不显示
//	                endTime=new Date();
//	            }
	        }
	        for(int i=0;i<glassList.size();i++){
	        	glass b =  glassList.get(i);
	            b.draw(g);      
	        }
	        for(int i=0;i<shell2List.size();i++){
	            shell2 b =  shell2List.get(i);
	            if(b.live) {
	            b.draw(g);
	            boolean peng = b.getRect().intersects(plane.getRect());
	            if(peng){
	                plane.live = false;   //飞机死掉,画面不显示
	                endTime=System.currentTimeMillis();
	            }
	            }
	            else {
	            	shell2List.remove(b);
	            }
	        }
	        for(int i=0;i<enemyList.size();i++){
	            enemy b =  enemyList.get(i);
	            if(b.live) {
	            score+=b.move(g);
	            b.count++;
	            if(b.count%40==0)
	            {
	            	shell2 a=new shell2(b);
	            	shell2List.add(a);
	            }	            
	            }
	            else {
	            	enemyList.remove(b);
	            }
	        }
	        for(int i=0;i<enemy2List.size();i++){
	            enemy2 b =  enemy2List.get(i);
	            if(b.live) {
	            score+=b.move(g);
	            b.count++;
	            if(b.count%50==0)
	            {
	            	for(int j=0;j<10;j++) {
	            		shell2 a=b.shoot();
	            		shell2List.add(a);
	            	}
	            }	            
	            }
	            else {
	            	enemy2List.remove(b);
	            }
	  
	        }
	        for(int i=0;i<enemy3List.size();i++){
	            enemy3 b =  enemy3List.get(i);
	            if(b.live) {
	            score+=b.move(g);
	            b.count++;
	            if(b.count%50==0)
	            {
	            	for(int j=0;j<10;j++) {
	            		shell2 a=b.shoot();
	            		shell2List.add(a);
	            	}
	            }	            
	            }
	            else {
	            	enemy3List.remove(b);
	            }
	  
	        }
	        if(!plane.live) {
	        	if(endTime==0){
	                endTime = System.currentTimeMillis();
	            }
	        	if(period==0) {
	            period = (int)((endTime-startTime)/1000);
	            printInfo(g, "时间："+period+"秒", 50, 180, 360, Color.white);
	        	}
	        	else {
	        		printInfo(g, "时间："+period+"秒", 50, 180, 360, Color.white);
	        	}
	        }
	       if(score>oldscore) {
	    	   boomnumber+=1;
	    	   oldscore+=200;
	       }
	    }  
	/**
	 * 初始化窗口
	 */
	public  void  launchFrame(){
		Timer time=new Timer();
		this.setTitle("黑暗在翻腾 cxy");
		this.setVisible(true);
		this.setSize(600, 600);
		this.setLocation(300, 100);
		new paintthread().start();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		addKeyListener(new KeyMonitor());
		time.schedule(new TimerTask() { 
				int count=0;
			   public void run() { 
				   for(int i=0;i<3+level;i++){
						double x=Math.random()*600;
						double y=Math.random()*100;
				        enemy b = new enemy(enemyImg,(int)x,(int)y+50,4+level);
				        enemyList.add(b);     
				    }
				   if(count!=0&&count%2==0) {
					   for(int i=0;i<2;i++){
							double x=Math.random()*600;
							double y=Math.random()*100;
					        enemy2 b = new enemy2(enemy2Img,(int)x,(int)y+50,4);
					        enemy2List.add(b);     
					    }
				   }
				   if(count!=0&&count%5==0) {
					   for(int i=0;i<1;i++){
							double x=Math.random()*600;
							double y=Math.random()*100;
					        enemy3 b = new enemy3(enemy3Img,(int)x,(int)y+50,4);
					        enemy3List.add(b);     
					    }
				   }
				   count++;
				   if(count%3==0) {
					   level++;
				   }
				   repaint();			   
			   } 
			 
			  }, 3*1000, 5*1000); 
	}
	
	class paintthread extends Thread {
        public void run(){
            while(true){
                repaint();
                try {
                    Thread.sleep(60); //1s = 1000ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }   
            }
        }
    }  
	
	 class KeyMonitor extends KeyAdapter {	       
	        public void keyPressed(KeyEvent e) {
	            plane.addDirection(e);
	        }
	        public void keyReleased(KeyEvent e) {
	            plane.minusDirection(e);
	        }
	    }
	
	public static void main(String[] args) {
		frame  f = new frame();
		f.launchFrame();
		
	}
	
	public void printInfo(Graphics g,String str,int size,int x,int y,Color color){
        Color c = g.getColor();
        g.setColor(color);
        Font f = new Font("宋体",Font.BOLD,size);
        g.setFont(f);
        g.drawString(str,x,y);
        g.setColor(c);
    }  
	
}
