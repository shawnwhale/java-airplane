package gamefirst;

import java.awt.Color;
import java.awt.Graphics;
import java.util.TimerTask;

public class shell2 extends object{
	double degree=0;
	Color c=Color.white;
	public shell2(enemy a) {
		c=Color.white;
		x=a.x+16;
		y=a.y+33;
		width=5;
		height=10;
		speed=13;
	}
	public shell2(enemy2 e) {
		c=Color.white;
		degree = Math.random()*Math.PI*2;
		x=e.x+16;
		y=e.y+33;
		width=5;
		height=15;
		speed=13;
	}
	public shell2(enemy3 e) {
		c=Color.red;
		degree = Math.random()*Math.PI*2;
		x=e.x+18;
		y=e.y+33;
		width=10;
		height=10;
		speed=16;
	}
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval((int)x, (int)y, width, height);
	    if(degree!=0) {
	    x += speed*Math.cos(degree);
        y += speed*Math.sin(degree);
	    }
	    else {
	    	 y += speed;
	    }

	}
}

