package gamefirst;

import java.awt.Color;
import java.awt.Graphics;



public class shell extends object{
	
	public shell(Plane plane) {
		x=plane.x+11;
		y=plane.y;
		width=8;
		height=16;
		speed=24;
	}
	public void draw(Graphics g) {
		if(live) {
		Color c=g.getColor();
		g.setColor(Color.YELLOW);
		g.fillOval((int)x, (int)y, width, height);
	    y -= speed;
//	    if(y>500||y<30){
//            degree = -degree;
//        }
//        if(x<0||x>500){
//            degree = Math.PI-degree;
//        }
	    g.setColor(c);
		}
	}
}
