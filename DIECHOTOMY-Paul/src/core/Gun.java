package core;

public class Gun
{
	private float radius;
	private double spread;
	static float fireRate;
	private float cooldown;
	
	public Gun(float radius, float fireRate, double spread)
	{
		this.fireRate = fireRate*Main.FRAMES_PER_SECOND;
		this.radius = radius;
		this.spread = spread;
	}
	
	public void update()
	{
		coolOff();
	}
	
	private void coolOff()
	{
		if(cooldown > 0) cooldown--;
		
		if(cooldown < 0) cooldown = 0;
	}
	
	public void shoot(float x, float y, float mouseX, float mouseY, boolean mouseL, double accuracy)
	{
		//double distance;
		double angle;
		double spread;
		float radius;
		float xAxis;
		float yAxis;
		
		coolOff();
		
		if(mouseL && cooldown == 0)
		{
			//distance = Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2));
			spread = (((Math.random()*2*(this.spread) - (this.spread))/accuracy)*Math.PI)/180;
			
			angle = Math.atan2(mouseY - y, mouseX - x) + spread;
			radius = 6;
			xAxis = (float) ((this.radius+radius+10)*Math.cos(angle));
			yAxis = (float) ((this.radius+radius+10)*Math.sin(angle));
			
//			Bullet.create(x + xAxis, y + yAxis, 6, angle, 1);
			Bullet.create(x + xAxis, y + yAxis, 4, radius, 10, 50, angle, 2);
			cooldown = fireRate;
		}
	}
}
