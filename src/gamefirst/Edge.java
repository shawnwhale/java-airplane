package gamefirst;

public class Edge {
	static int x=600;
	static int y=600;
	
	public static void edge(Plane e) {
	    	if(e.x>x-25) {
	    		e.x=575;
	    	}
	    	if(e.x<0) {
	    		e.x=0;
	    	}
	    	if(e.y<0) {
	    		e.y=0;
	    	}
	    	if(e.y>y-25) {
	    		e.y=575;
	    	}
	    }
}
