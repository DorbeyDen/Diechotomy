package core;

import org.newdawn.slick.Graphics;

public class Hub {
	
	final static int hubXPosition =  Main.getScreenWidth()/2;  
	final static int hubYPosition = Main.getScreenHeight()/2;
	boolean inHub = false;
	
	public void update()
	{
		
	}
	
	public static void render(Graphics g) {
		g.drawRect(hubXPosition, hubYPosition, 80, 80); 
		
	}

}
