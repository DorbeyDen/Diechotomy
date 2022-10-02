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

import core.Character;
import core.Enemy;
import core.EntityManager;
import core.Obstacle;

public class Game extends BasicGameState 
{	
	private int id;
	
	Image test;
	
	Character c;
	Obstacle o;
	Obstacle o1;
	Obstacle o2;
	Obstacle o3;
	
	public int view_x;
	public int view_y;

	
	final static int marginR = 945;
	final static int marginL = 945;
	final static int marginU = 525;
	final static int marginD = 525;
	
	private StateBasedGame sbg;

	public Game(int id) 
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
		Obstacle.create(500, 300, 50, 3, 1, 100, 100);
//		Obstacle.create(800, 600, 100, 3, 1, 300, 100);
		Obstacle.create(1000, 100, 40, 3, 1, 80, 80);
		Obstacle.create(700, 800, 30, 3, 1, 60, 60);
		
		// assigns the player in "entities" to "c"
		test = new Image("res/starrynight.png");
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
		EntityManager.updateAll();
		
		EnemyManager.updateAll();
		
		Night.update();
		
		scroll();
		gameOver();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{	
		g.translate((float) -view_x + Main.getScreenWidth()/2,
			    	(float) -view_y + Main.getScreenHeight()/2);
		test.draw(0, 0);
		
		
		
		EntityManager.renderAll(g);
		
		Hub.render(g);
		
		g.setColor(new Color(255, 0, 255));
		g.fillRect(c.mouseX - 5, c.mouseY - 5, 10, 10);
		g.fillRect(view_x - 10, view_y - 10, 20, 20);
		g.setColor(new Color(255, 255, 255));
		
		
		
		g.translate((float) view_x - Main.getScreenWidth()/2,
					(float) view_y - Main.getScreenHeight()/2);
		
		g.drawString("time: " + Night.getFrames()/Main.FRAMES_PER_SECOND + "s", 100, 60);
		
		if(EntityManager.getCharacter() != null)
		{
			g.drawString("Player Health: " + EntityManager.getCharacter().getHealth(), 100, 30);
		}
		
		g.drawString("Spawnpoint: " + EnemyManager.getSpawnpoint(), 100, 90);
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		EnemyManager.setNewSpawnpoint();
		
		EntityManager.killAll();
		
		Character.create(Main.getScreenWidth()/2, Main.getScreenHeight()/2, 0, 25, 100, 50);
		
		c = EntityManager.getCharacter();
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char h)
	{
		if(key == Input.KEY_W)
			c.keyW = true;
		if(key == Input.KEY_A)
			c.keyA = true;
		if(key == Input.KEY_S)
			c.keyS = true;
		if(key == Input.KEY_D)
			c.keyD = true;
	}
	
	public void keyReleased(int key, char h)
	{
		if(key == Input.KEY_W)
			c.keyW = false;
		if(key == Input.KEY_A)
			c.keyA = false;
		if(key == Input.KEY_S)
			c.keyS = false;
		if(key == Input.KEY_D)
			c.keyD = false;
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
		if(button == Input.MOUSE_LEFT_BUTTON)
		{
			c.mouseL = true;
		}
		
		c.updateMouse(x + view_x, y + view_y);
	}
	
	public void mouseReleased(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
		if(button == Input.MOUSE_LEFT_BUTTON)
		{
			c.mouseL = false;
		}
		
		c.updateMouse(x + view_x, y + view_y);
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy)
	{
		c.updateMouse(newx + view_x, newy + view_y);
	}
	
	public void mouseDragged(int oldx, int oldy, int newx, int newy)
	{
		c.updateMouse(newx + view_x, newy + view_y);
	}
	
	public void scroll()
	{
		
		view_x = (int) c.x;
		view_y = (int) c.y;
	}
	
	public void gameOver()
	{
		if(c.currHealth <= 0)
		{
			sbg.enterState(Main.OVER_ID);
		}
	}
}
