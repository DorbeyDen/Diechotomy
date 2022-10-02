package core;

import org.newdawn.slick.Graphics;

public abstract class Unit extends Entity {
	

	float speed;
	int frames = 0;
	
	Unit(float x, float y, float speed, float radius, int health, int damage)
	{
		super(x, y, radius, health, damage);
		this.speed = speed;
	//	collisions = new ArrayList<Entity>();
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g)
	{
		
	}
	
//	protected void pushAway(Entity entity)
//	{
//		double angle;
//		double dist;
//		double combinedWidth;
//		
//		if(entity != null)
//		{
//			angle = getAngle(entity, this);
//			dist = getDistance(entity);
//			combinedWidth = radius + entity.radius;
//			
//			if(collider != null)
//			{
//				x += (combinedWidth - dist)*Math.cos(angle);
//				y += (combinedWidth - dist)*Math.sin(angle);
//			}
//		}
//	}
	
	protected void takeDamage(float damage) {
		currHealth -= damage;
	}
	
	protected abstract void takeDamageFromCollision();
}
