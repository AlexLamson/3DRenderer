package entities;

import entities.visible.Visible;
import math3D.*;

//todo: turn this into a Cone class that defaults to 4 sides
public class Pyramid extends VisibleObject
{
	public float side = 10;	//width of the pyramid (default is 10)

	public Pyramid(float side)
	{
		this.side = side;
		makePyramid();
	}
	
	public Pyramid(float x, float y, float z, float side)
	{
		this(side);
		loc.x = x;
		loc.y = y;
		loc.z = z;
	}
	
	public void makePyramid()
	{
		Point3[] points = {
		new Point3( 0,  0, side ),
		new Point3( side/2,   side/2, -side/2 ),
		new Point3( side/2,  -side/2, -side/2 ),
		new Point3( -side/2, -side/2, -side/2 ),
		new Point3( -side/2,  side/2, -side/2 )};
		
		for(Point3 p : points)
			this.points.add(p);
		
		
		int[][] faces =
		{{0,1,2},
		{0,2,3},
		{0,3,4},
		{0,4,1},
		{1,2,3,4}};
		
		for(int[] face : faces)
			visibles.add(Visible.pointsToVisible(face));
	}
}