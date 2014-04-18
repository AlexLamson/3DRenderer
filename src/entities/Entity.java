package entities;

import math3D.*;

public class Entity
{
	public Point3 loc;
	public Rotation rot;
	public boolean destroyMe = false;		//if true, then the World will try to destroy the object

	public Entity()
	{
		loc = new Point3(0, 0 , 0);
		rot = new Rotation(0, 0, 0);
	}
	
	public void tick()
	{
		
	}
	
	public String toString()
	{
		return loc.toString();
	}
}