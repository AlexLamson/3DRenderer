package entities;

public class FallingCube extends Cube
{
	public float fallSpeed = -0.01f;
	
	public FallingCube(float side)
	{
		super(side);
		setRandomFallSpeed();
	}
	
	public FallingCube(float x, float y, float z, float side)
	{
		super(x, y, z, side);
		setRandomFallSpeed();
	}
	
	public void setRandomFallSpeed()
	{
		fallSpeed = -(float)(Math.random()*.05+0.01);
	}
	
	public void tick()
	{
		super.tick();
		if(loc.z+fallSpeed-side/2 > 0)
		{
			loc.z += fallSpeed;
		}
		else
			destroyMe = true;
	}
}
