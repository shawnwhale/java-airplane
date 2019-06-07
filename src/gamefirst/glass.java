package gamefirst;

import java.awt.Color;
import java.awt.Graphics;

public class glass extends object{
	double degree;
	public glass(Plane plane) {
		degree = Math.random()*Math.PI*2;
		x=plane.x+11;
		y=plane.y;
		width=5;
		height=5;
		speed=75;
	}
	public void draw(Graphics g) {
		if(live) {
		Color c=g.getColor();
		g.setColor(Color.cyan);
		g.fillOval((int)x, (int)y, width, height);
		 x += speed*Math.cos(degree);
	     y += speed*Math.sin(degree);
	    g.setColor(c);
		}
	}
}
