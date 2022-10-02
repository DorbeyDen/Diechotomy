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

public abstract class EntityManager{
	
	// this ArrayList contains every entity (including player & bullets)
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	
	protected static ArrayList<Entity> collisionListOne = new ArrayList<Entity>();
	protected static ArrayList<Entity> collisionListTwo = new ArrayList<Entity>();
	
//	public static void initAll()
//	{
//		Character.create(Main.getScreenWidth()/2, Main.getScreenHeight()/2, 5, 5, 100, 10);
//	}
	
	// Entity tells all entities in "entities" to update
	public static void updateAll()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).update();
		}
		
		updateCollisions();
	}
	
	public static void updateCollisions()
	{
		collisionListOne.clear();
		collisionListTwo.clear();
		
		// log every collision to list one and list two
		for(int a = 0; a < entities.size(); a++)
		{
			for(int b = 0; b < entities.size(); b++)
			{
				Entity one = entities.get(a);
				Entity two = entities.get(b);
				
				// this part logs the collision as either happening with an entity or with null
				if(one.isColliding(two))
				{
					collisionListOne.add(one);
					collisionListTwo.add(two);
				}
			}
		}
		
		// apply every logged collision
		for(int i = 0; i < collisionListOne.size(); i++)
		{
			collisionListOne.get(i).applyCollision(collisionListTwo.get(i));
		}
	}
	
	// Entity tells all entities in "entities" to render
	public static void renderAll(Graphics g)
	{
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(g);
		}
	}
	
	
	public static void create(Entity e)
	{
		entities.add(e);
	}
	
	public static void destroy(Entity e)
	{
		for(int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i) == e)
			{
				entities.remove(i);
			}
		}
	}
	
	public static void killAll()
	{
		entities.clear();
	}
	
	// if there's a character in "entities" it returns it,
	// otherwise it returns null (sorry) 
	public static Character getCharacter()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i) instanceof Character)
			{
				return (Character) entities.get(i);
			}
		}
		
		return null;
	}
	
	public static boolean entityIsColliding(Entity entity)
	{
		for(int i = 0; i < collisionListOne.size(); i++)
		{
			if(collisionListOne.get(i) == entity)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static Entity getCollidingEntity(Entity entity)
	{
		for(int i = 0; i < collisionListOne.size(); i++)
		{
			if(collisionListOne.get(i) == entity)
			{
				return collisionListTwo.get(i);
			}
		}
		
		return null;
	}
	
	public static void applyDamage(Entity entity)
	{
		entity.takeDamage(getCollidingEntity(entity).getDamage());
	} 
	
	public abstract void update();
	public abstract void render(Graphics g);
}
