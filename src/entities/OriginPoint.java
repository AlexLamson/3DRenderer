package entities;

import entities.visible.*;
import math3D.*;

public class OriginPoint extends VisibleObject
{
	public float length = 1;	//length of each line

	public OriginPoint(float length)
	{
		this.length = length;
		makeOriginPoint();
	}
	
	public void makeOriginPoint()
	{
		Point3[] points = 
			{new Point3( 0,       0,       0 ),
			new Point3(  -length, 0,       0 ),
			new Point3(  length,  0,       0 ),
			new Point3(  0,       -length, 0 ),
			new Point3(  0,       length,  0 ),
			new Point3(  0,       0,       -length ),
			new Point3(  0,       0,       length )};
		
		for(Point3 p : points)
			this.points.add(p);
		
		int[][] faces =
		{{0,1},
		{0,2},
		{0,3},
		{0,4},
		{0,5},
		{0,6}};
		
		for(int[] face : faces)
			visibles.add(new VEdge(face));
	}
	
	public void tick()
	{
		
	}
}