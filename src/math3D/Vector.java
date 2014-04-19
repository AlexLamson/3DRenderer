package math3D;

public class Vector extends Tuple
{
	public Vector(float x, float y, float z)
	{
		super(x, y, z);
	}
	
	public Vector(Tuple tup)
	{
		super(tup.x, tup.y, tup.z);
	}
}
