package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Obstacle extends Entity {
	
	int width; 
	int height;
	
	Obstacle(float x, float y, float radius, int health, int damage, int width, int height)
	{
		super(x, y, radius, health, damage);
		this.width = width;
		this.height = height;
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(new Color(0, 0, 0));
		g.fillRect(x - radius, y - radius, radius*2, radius*2);
	}
	
	public static void create(float x, float y, float radius, int health, int damage, int width, int height)
	{
		EntityManager.create(new Obstacle(x, y, radius, health, damage, width, height));
	}

	@Override
	protected void takeDamage(float damage) {
		// TODO Auto-generated method stub
		currHealth -= damage;
	}
}
