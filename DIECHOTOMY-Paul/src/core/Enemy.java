package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Enemy extends Unit
{
	float speed = 0;
	float angle = 0;
	
	Enemy(float x, float y, float speed, float radius, int health, int damage)
	{
		super(x, y, speed, radius, health, damage);
	}

	// update & render
	
	public void update()
	{
		super.update();
		changeSpeed();
		move();
		takeDamageFromCollision();
		pushAway(EntityManager.getCollidingEntity(this));
	}

	public void render(Graphics g)
	{
		if(EntityManager.entityIsColliding(this))
		{
			g.setColor(new Color(255, 255, 0));
		}else
		{
			g.setColor(new Color(255, 0, 0));
		}
		
		
		if(Night.getDay())
			g.setColor(new Color(255, 0, 0));
		else
			g.setColor(new Color(255, 100, 0));
		
		
		g.fillRect(x - 25, y - 25, 50, 50);
		
		g.setColor(new Color(255, 255, 255));
	}
	
	// accessors
	
	
	// methods
	private void move()
	{
		if(EntityManager.getCharacter() != null)
		{
			angle = (float) Math.atan2(EntityManager.getCharacter().y - y, EntityManager.getCharacter().x - x);
			
			x += speed*Math.cos(angle);
			y += speed*Math.sin(angle);
		}
	}
	
	private void changeSpeed()
	{
		if(Night.getDay())
			speed = 2;
		else
			speed = 4;
	}
	
	
	
	// create & destroy
	public static void create(float x, float y, float speed, float radius, int health, int damage) 
	{
		EntityManager.create(new Enemy(x, y, speed, radius, health, damage));
	}
//	
	public void destroy()
	{
		EntityManager.destroy(this);
	}
	
	protected void takeDamageFromCollision()
	{
		Entity collider = EntityManager.getCollidingEntity(this);
		
		if(collider != null && collider instanceof Bullet) 
		{	
			EntityManager.applyDamage(this);
		}
			
		if(currHealth <= 0)
		{
			currHealth = 0;
			destroy();
		}
	}
}
