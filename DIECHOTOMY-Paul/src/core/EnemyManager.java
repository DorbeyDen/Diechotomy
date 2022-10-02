package core;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public abstract class EnemyManager {
	
	// this ArrayList contains every entity (including player & bullets)
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	static Spawnpoint[] spawnpoints = {new Spawnpoint(Main.getScreenWidth()/2, -(Main.getScreenHeight()/4), 500, 200),
			new Spawnpoint(Main.getScreenWidth()/2, Main.getScreenHeight()*1.25f, 500, 200),
			new Spawnpoint(Main.getScreenWidth()*1.25f, Main.getScreenHeight()/2, 200, 500),
			new Spawnpoint(-(Main.getScreenWidth()/4), Main.getScreenHeight()/2, 200, 500)};

	static int spawnpoint = 0;
	static float spawnRate = 0.5f*Main.FRAMES_PER_SECOND;
	static float spawnCooldown;
	
	public static void updateAll()
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).update();
		}
		
		coolOff();
		
		spawnEnemy();
		
//		updateCollisions();
	}
	
	public static void create(Enemy e)
	{
		enemies.add(e);
	}
	
	public static float getSpawnRate()
	{
		return spawnRate;
	}
	
	public static float getSpawnCooldown()
	{
		return spawnCooldown;
	}
	
	public static char getSpawnpoint()
	{
		switch(spawnpoint)
		{
		case 0:
			return 'N';
		case 1:
			return 'S';
		case 2:
			return 'E';
		case 3:
			return 'W';
		}
		
		return 0;
	}
	
	public static void setNewSpawnpoint()
	{
		spawnpoint = (int) (Math.random()*spawnpoints.length);
	}
	
	public static void coolOff()
	{
		if(spawnCooldown > 0)
		{
			spawnCooldown--;
		}
		
		if(spawnCooldown < 0)
		{
			spawnCooldown = 0;
		}
	}
	
	public static void spawnEnemy()
	{
		float offsetX;
		float offsetY;
		
		if(spawnCooldown == 0)
		{
			offsetX = (float) spawnpoints[spawnpoint].getSpawnPosX();
			offsetY = (float) spawnpoints[spawnpoint].getSpawnPosY();
			
			if(EntityManager.getCharacter() != null)
			{
				Enemy.create(offsetX, offsetY, 5, 25, 100, 1);
			}
			
			spawnCooldown = spawnRate;
		}
	}
	
	public static void SpawnAlL()
	{
		for(int a = 0; a < enemies.size(); a++)
		{
			for(int b = 0; b < enemies.size(); b++)
			{
				Enemy one = enemies.get(a);
				Enemy two = enemies.get(b);
				
				if(one.isColliding(two))
				{
					one.applyCollision(two);
				}else
				{
					one.applyCollision(null);
				}
			}
		}
		
	}
	
	// Enemy tells all enemies in "enemies" to render
	public static void renderAll(Graphics g)
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			enemies.get(i).render(g);
		}
	}
	
		
	public static void createSlowEnemy(float x, float y, float speed, float radius, int health, int damage)
	{
		Enemy e = new SlowEnemy(x, y, speed, radius, health, damage);
		enemies.add(e);
	}
	
	
	public static void destroy(Entity e)
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			if(enemies.get(i) == e)
			{
				enemies.remove(i);
			}
		}
	}
	
	
	public static Enemy getEnemy()
	{
		for(int i = 0; i < enemies.size(); i++)
		{
			if(enemies.get(i) instanceof Enemy)
			{
				return (Enemy) enemies.get(i);
			}
		}
		
		return null;
	}
	
	
	
	
	
	public abstract void update();
	public abstract void render(Graphics g);
		

}
