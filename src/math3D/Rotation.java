package math3D;

public class Rotation
{
	public double x, y, z;
	
	public Rotation(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(double deltaX, double deltaY, double deltaZ)
	{
		x += deltaX;
		y += deltaY;
		z += deltaZ;
	}
	
	public String toString()
	{
		return x+", "+y+", "+z;
	}
}
