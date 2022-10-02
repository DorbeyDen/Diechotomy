package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class Character extends Unit
{
	public boolean keyW;
	public boolean keyA;
	public boolean keyS;
	public boolean keyD;
	static float xSpeed;
	static float ySpeed;
	public boolean mouseL;
	public float mouseX;
	public float mouseY;
	
	Gun gun;
	double accuracy;
	
	final static double hubWidth =  Main.getScreenWidth()/2;  
	final static double hubHeight = Main.getScreenHeight()/2;
	
	static boolean stop = false;

	
		
	Character(float x, float y, float speed, float radius, int health, int damage)
	{
		super(x, y, speed, radius, health, damage);
		xSpeed = 0;
		ySpeed = 0;
		keyW = false;
		keyA = false;
		keyS = false;
		keyD = false;
		mouseL = false;
		accuracy = 0;
		
		gun = new Gun(6, 0.125f, 360);
	}
	
	public void update()
	{
		super.update();
		movement();
		shoot();
		takeDamageFromCollision();
		//onHubCharacter(); 
		//enterHub(); 
//		super.update();
	}
	
	public void render(Graphics g)
	{
		double dist = Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2));
		double angle = Math.atan2(mouseY - y, mouseX - x);
		float xAxis = (float) (100*Math.cos(angle));
		float yAxis = (float) (100*Math.sin(angle));
		
		g.setColor(new Color(255, 255, 255));
		
		if(dist < 100)
		{
			xAxis = (float) (dist*Math.cos(angle));
			yAxis = (float) (dist*Math.sin(angle));
		}
		
//		if(collider != null)
//		{
//			g.setColor(new Color(255, 255, 0));
//		}else
//		{
//			g.setColor(new Color(255, 255, 255));
//		}
		
		if(Night.getDay())
			g.setColor(new Color(255, 255, 255));
		else
			g.setColor(new Color(0, 255, 0));
		g.fillRect(x - 25, y - 25, 50, 50);
		
		// draw cursor
		if(mouseL)
		{
			g.drawOval(x + xAxis - 22.5f, y + yAxis - 22.5f, 45, 45);
		}else
		{
			g.drawOval(x + xAxis - 15, y + yAxis - 15, 30, 30);
		}
		
		
		//if(onHubCharacter()) g.drawString("Press Space to Enter", Main.getScreenWidth()/2 , Main.getScreenHeight()/2);
		
	}
	
	public static void create(float x, float y, float speed, float radius, int health, int damage)
	{
		EntityManager.create(new Character(x, y, speed, radius, health, damage));
	}
	
	public void destroy()
	{
		EntityManager.destroy(this);
	}
	
	private void movement()
	{
		if(keyW)
		{
			if(Night.getDay())
				ySpeed = -5;
			else
				ySpeed = -4;
		}else if(keyS)
		{
			if(Night.getDay())
				ySpeed = 5;
			else
				ySpeed = 4;
		}else
		{
			ySpeed = 0;
		}
		
		if(keyA)
		{
			if(Night.getDay())
				xSpeed = -5;
			else
				xSpeed = -4;
		}else if(keyD)
		{
			if(Night.getDay())
				xSpeed = 5;
			else
				xSpeed = 4;
		}else
		{
			xSpeed = 0;
		}
		
		x += xSpeed;
		y += ySpeed;
	}
	
	public static void stop()
	{
		xSpeed *= -1;
		ySpeed *= -1;
		stop = true;
	}
	
	public void updateMouse(float mx, float my)
	{
		mouseX = mx - Main.getScreenWidth()/2;
		mouseY = my - Main.getScreenHeight()/2;
	}
	
	private void shoot()
	{
		gun.shoot(x, y, mouseX, mouseY, mouseL, 1);
	}
	
	
	protected void takeDamageFromCollision()
	{
		Entity collider = EntityManager.getCollidingEntity(this);
		
		if(collider != null && !(collider instanceof Obstacle) && !(collider instanceof Bullet)) EntityManager.applyDamage(this);;
		
		if(currHealth <= 0)
		{
			currHealth = 0;
			destroy();
		}
	}
}
