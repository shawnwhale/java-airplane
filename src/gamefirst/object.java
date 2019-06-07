package gamefirst;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class object {
	Image img;
	double x,y;
	int speed;
	int width,height;
	boolean live=true;
	public void drawself(Graphics g) {
		g.drawImage(img,(int)x,(int)y,null);
	}
	public object(Image img,double x,double y) {
		this.img=img;
		this.x=x;
		this.y=y;
		if(img!=null) {
			this.width=img.getWidth(null);
			this.height=img.getHeight(null);
		}
	}
	public object(Image img, double x, double y, int speed, int width,
            int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }
	public object() {
    }
	public Rectangle getRect(){  //返回一个矩形
        return  new Rectangle((int)x,(int) y, width, height);
    }  
     
}
