package core;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public abstract class Entity extends GameObject
{

	float radius;
	int maxHealth;
	int currHealth;
	int damage;
	
	//ArrayList<Entity> collisions;
	
	Entity(float x, float y, float radius, int health, int damage)
	{
		super(x, y);
		this.radius = radius;
		maxHealth = health;
		currHealth = health;
		this.damage = damage;
	//	collisions = new ArrayList<Entity>();
	}
	
	public void update()
	{
	//	collisions.clear();
	}
	
	public void render(Graphics g)
	{
		
	}
	
	public int getHealth()
	{
		return currHealth;
	}
	
	public int getDamage()
	{
		return damage;
	}

	public boolean isColliding(Entity other)
	{
		double dist = Math.abs(Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2)));
		
		if(this == other)
		{
			return false;
		}
		
		return dist <= radius + other.radius;
	}
		
	protected void applyCollision(Entity other)
	{
		Entity collider = other;
//		collisions.add(other);
		
		if (other instanceof Enemy && this instanceof Character)
		{
			if(collider != null) {
				takeDamage(collider.getDamage());
				pushAway(collider);
			}
		}
		
		if(other instanceof Obstacle && this instanceof Character || this instanceof Bullet || this instanceof Enemy)
		{
			if(collider != null) pushAway(collider);
		}
		
		if(other instanceof Bullet && this instanceof Enemy)
		{
			if(collider != null) {
				takeDamage(collider.getDamage());
				pushAway(collider);
			}
		}
	}
	
	public double getAngle(Entity entity1, Entity entity2)
	{
		return Math.atan2(entity2.y - entity1.y, entity2.x - entity1.x);
	}
	
	public double getDistance(Entity entity)
	{
		return Math.abs(Math.sqrt(Math.pow(entity.x - x, 2) + Math.pow(entity.y - y, 2)));
	}
	
	protected void pushAway(Entity entity)
	{
		double angle;
		double dist;
		double combinedWidth;
		
		if(entity != null)
		{
			angle = getAngle(entity, this);
			dist = getDistance(entity);
			combinedWidth = radius + entity.radius;
			
			if(EntityManager.getCollidingEntity(this) != null)
			{
				x += (combinedWidth - dist)*Math.cos(angle);
				y += (combinedWidth - dist)*Math.sin(angle);
			}
		}
	}
	
	
	protected abstract void takeDamage(float damage);
}
