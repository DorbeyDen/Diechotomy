package core;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Bullet extends Unit
{
	// data
	
	// determines how long a bullet lasts before it is destroyed
	float lifetime;
	double angle;
	
	// constructor
	Bullet(float x, float y, float speed, float radius, int health, int damage, double angle, float lifetime)
	{
		super(x, y, speed, radius, health, damage);
		this.angle = angle;
		this.lifetime = lifetime*Main.FRAMES_PER_SECOND;
	}
	
	// update & render
	public void update()
	{
		move();
		countdown();
		takeDamageFromCollision();
	}
	public void render(Graphics g)
	{
		g.setColor(new Color(0, 255, 255));
		g.fillRect(x - radius, y - radius, 2*radius, 2*radius);
		g.setColor(new Color(255, 255, 255));
	}
	
	// accessors
	
	
	// methods
	
	// create a new bullet and add it to "entites"
	public static void create(float x, float y, float speed, float radius, int health, int damage, double angle, float lifetime)
	{
		EntityManager.create(new Bullet(x, y, speed, radius, health, damage, angle, lifetime));
	}
	
	// go through "entities" and destroy this one
	public void destroy()
	{
		EntityManager.destroy(this);
	}
	
	private void move()
	{
		x += speed*Math.cos(angle);
		y += speed*Math.sin(angle);
	}
	
	// count down until zero and then destroy the bullet
	private void countdown()
	{
		lifetime--;
		
		if(lifetime <= 0)
		{
			destroy();
		}
	}

	protected void takeDamageFromCollision()
	{
		if(EntityManager.entityIsColliding(this))
		{
			currHealth--;
		}
		
		if(currHealth <= 0)
		{
			destroy();
		}
	}
}
