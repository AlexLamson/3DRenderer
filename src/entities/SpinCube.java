package entities;

import java.util.ArrayList;

import entities.visible.*;
import math3D.*;

public class SpinCube extends VisibleObject
{
	public float side = 10;	//length of each edge of the cube
	
	public float thetaDelta = (float) (Math.PI/10);
	public ArrayList<Point3> storedPoints = new ArrayList<Point3>();
	
	public SpinCube(float side)
	{
		this.side = side;
		makeCube();
	}
	
	public void makeCube()
	{
		Point3[] points = 
			{new Point3( side/2,  side/2,  side/2 ),
			new Point3(  side/2, -side/2,  side/2 ),	
			new Point3( -side/2, -side/2,  side/2 ),
			new Point3( -side/2,  side/2,  side/2 ),
			new Point3(  side/2,  side/2, -side/2 ),
			new Point3(  side/2, -side/2, -side/2 ),
			new Point3( -side/2, -side/2, -side/2 ),
			new Point3( -side/2,  side/2, -side/2 )};
		
		for(Point3 p : points)
			this.points.add(p);
		
		for(Point3 p : storedPoints)
			this.points.add(p);
		
		int[][] faces =
		{{0,1,2,3},
		{0,1,5,4},
		{1,2,6,5},
		{2,3,7,6},
		{0,3,7,4},
		{4,5,6,7}};
		
		for(int[] face : faces)
			visibles.add(new VQuad(face));
	}
	
	public void tick()
	{
//		rotateCube(thetaDelta);
//		translate(0, 0.002f, 0);
		loc.x += (float)((side/2)*Math.cos(thetaDelta));
		loc.z += (float)((side/2)*Math.sin(thetaDelta));
		thetaDelta+=Math.PI/100;
	}
	
	public void rotateCube(float thetadelta)
	{
		Point3 topRotPoint = new Point3(loc.x, loc.y, loc.z+side/2);
		Point3 botRotPoint = new Point3(loc.x, loc.y, loc.z-side/2);
		
		for(int i = 0; i < 4; i++)
		{
			Point3 p = storedPoints.get(i);
			points.set(i, p.rotateAbout(new Rotation(0, 0, thetaDelta), topRotPoint));
		}
		
		for(int i = 4; i < 8; i++)
		{
			Point3 p = storedPoints.get(i);
			points.set(i, p.rotateAbout(new Rotation(0, 0, thetaDelta), botRotPoint));
		}
	}
}