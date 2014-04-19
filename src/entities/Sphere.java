package entities;

import entities.visible.Visible;
import math3D.*;

public class Sphere extends VisibleObject
{
	public double radius = 10;	//width of the pyramid (default is 10)
	public int horzRings = 5;
	public int vertRings = 5;

	public Sphere(double radius, int horzRings, int vertRings)
	{
		this.radius = radius;
		this.horzRings = horzRings;
		this.vertRings = vertRings;
		makeSphere();
	}
	
	public Sphere(float x, float y, float z, double radius, int horzRings, int vertRings)
	{
		this(radius, horzRings, vertRings);
		loc.x = x;
		loc.y = y;
		loc.z = z;
	}
	
	public void makeSphere()
	{
		//generate all the points
		double deltaZ = 2.0*radius/horzRings;
		double xyDeltaTheta = 2.0*Math.PI/vertRings;
		
		for(double z = 0; z < 2.0*radius; z += deltaZ)
		{
			for(double xyTheta = 0; xyTheta < 2*Math.PI; xyTheta += xyDeltaTheta)
			{
				double r = radius*Math.sin( Math.PI * z/(2.0*radius) );
				double x = r*Math.cos(xyTheta);
				double y = r*Math.sin(xyTheta);
				this.points.add(new Point3((float)x, (float)y, (float)z));
			}
		}
		
		for(int i = 0; i < points.size()-1; i++)
		{
			if((i+1) % vertRings != 0)
				visibles.add(Visible.pointsToVisible(new int[]{i,i+1}));
		}
	}
}