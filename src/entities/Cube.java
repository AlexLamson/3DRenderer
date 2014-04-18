package entities;

import java.awt.Color;

import entities.visible.Visible;

import main.Render;
import math3D.*;

public class Cube extends VisibleObject
{
	public float side = 10;	//width of the cube
	
	public Cube(float side)
	{
		this.side = side;
		makeCube();
	}
	
	public Cube(float x, float y, float z, float side)
	{
		this(side);
		loc.x = x;
		loc.y = y;
		loc.z = z;
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
		
		int[][] faces =
			{{0,1,2,3},
			{0,1,5,4},
			{1,2,6,5},
			{2,3,7,6},
			{0,3,7,4},
			{4,5,6,7}};
		
		Color[] colors = new Color[6];
		Color randomColor = Render.randomColor();
		for(int i = 0; i < colors.length; i++)
		{
			boolean randomizeEveryFace = true;
			if(randomizeEveryFace)
				colors[i] = Render.randomColor();
			else
				colors[i] = randomColor;
		}
		
		for(int i = 0; i < faces.length; i++)
			visibles.add(Visible.pointsToVisible(faces[i], colors[i]));
	}
	
	public void tick()
	{
		
	}
}