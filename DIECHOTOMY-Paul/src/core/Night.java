package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.Enemy;

public class Night {
	// this ArrayList contains every entity (including player & bullets)
	static int frames = 0; 
	static boolean day = true;
	static boolean fightTime = true;
	static Image dayB;
	static Image nightB;
	
	public Night()
	{
		frames = 0;
		day = true;
		
		try {
			dayB = new Image("res/starryday.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		try {
			nightB = new Image("res/starrynight.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public static void update()
	{
		if(fightTime)
		{
			frames++;
			time();
			
			if(day)
			{
				EnemyManager.spawnRate = 0.5f*Main.FRAMES_PER_SECOND;
			}else
			{
				EnemyManager.spawnRate = 0.25f*Main.FRAMES_PER_SECOND;
			}
		}
	}
	
	
	public static int getFrames()
	{
		return frames;
	}
	
	public static boolean getDay()
	{
		return day;
	}
	
	
	public static void time()
	{
		if(day && (frames/Main.FRAMES_PER_SECOND) > 60)
		{
			day = false;
			
			frames = 0;
		}else if(!day && (frames/Main.FRAMES_PER_SECOND) > 40)
		{
			day = true;
			
			EnemyManager.setNewSpawnpoint();
			
			frames = 0;
		}
	}
	
	public static Image background()
	{
		if(day)
		{
			return dayB;
		}
		else
		{
			return nightB;
		}
	}
	
	public static void setup()
	{
		frames = 0;
		day = true;
	}

}
