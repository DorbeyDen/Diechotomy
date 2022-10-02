package core;

public class Spawnpoint extends GameObject
{
	float width;
	float height;
	
	public Spawnpoint(float x, float y, float width, float height)
	{
		super(x, y);
		
		this.width = width;
		this.height = height;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public double getSpawnPosX()
	{
		return Math.random()*2*width - width + x;
	}
	
	public double getSpawnPosY()
	{
		return Math.random()*2*height - height + y;
	}
}
