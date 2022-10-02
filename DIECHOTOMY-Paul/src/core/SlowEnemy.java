package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class SlowEnemy extends Enemy {
	
	SlowEnemy(float x, float y, float speed, float radius, int health, int damage)
	{
		super(x, y, speed, radius, health, damage);
	}
	
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
			g.setColor(new Color(255, 255, 0));
		
		
		g.fillRect(x - 25, y - 25, 50, 50);
		
		g.setColor(new Color(255, 255, 255));
	}
	
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
}
