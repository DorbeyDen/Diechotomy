package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState 
{	
	private int id;
	private StateBasedGame sbg;
	private static Image howToPlayIm;

	public Menu(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		this.sbg = sbg;
		howToPlayIm = new Image("res/howtoplay.png");
		howToPlayIm = howToPlayIm.getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		g.drawImage(howToPlayIm, 0, 0);
	
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{

		// This code happens every time the user presses a key
	}
	
	public void mousePressed(int button, int x, int y)
	{
		if(button == 0) {
			Color b = new Color(0,0,0);
			sbg.enterState(Main.GAME_ID,new FadeOutTransition(b,1000),new FadeInTransition(b,10));
		}
	}
	
	


}
