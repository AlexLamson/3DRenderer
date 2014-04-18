package entities;

import entities.visible.Visible;
import main.Main;
import math3D.*;

public class Camera extends VisibleObject
{
	public Point3 loc;
	public Rotation rot;
	public float focalLength;
	public float lensWidth = 1f;
	public float lensHeight = (lensWidth*(float)Main.pixel.height)/(float)Main.pixel.width;
	
	public Camera(Point3 loc, Rotation rot, float focalLength)
	{
		this.loc = loc;
		this.rot = rot;
		this.focalLength = focalLength;
		makeVisibleCamera();
	}
	
	public void makeVisibleCamera()
	{
		Plane plane = getPlane();
		points.add(plane.p1);
		points.add(plane.p2);
		points.add(plane.p3);
		points.add(new Point3(loc.x+lensWidth/2, loc.y+focalLength, loc.z-lensHeight/2));
		points.add(loc);
		
		int[][] faces = 
			{{0,1},
			{0,2},
			{2,3},
			{1,3},
			{0,4},
			{1,4},
			{2,4},
			{3,4}};
		
		for(int i = 0; i < faces.length; i++)
			visibles.add(Visible.pointsToVisible(faces[i]));
	}
	
    /*
   1. calc pitch (yz)
   2. calc yaw (xy)
    
   camera plane is defined as

   S1 +----------+ S2
      |          |
      |          |
   S3 +----------+ S4
	 
	 perspective: as if you looking through a camera
     */
    
	public Plane getPlane()
	{
		Point3 s1 = new Point3(loc.x-lensWidth/2, loc.y+focalLength, loc.z+lensHeight/2);
		Point3 s2 = new Point3(loc.x+lensWidth/2, loc.y+focalLength, loc.z+lensHeight/2);
		Point3 s3 = new Point3(loc.x-lensWidth/2, loc.y+focalLength, loc.z-lensHeight/2);
		
		s1 = s1.rotateAbout(rot, loc);
		s2 = s2.rotateAbout(rot, loc);
		s3 = s3.rotateAbout(rot, loc);
		
		return new Plane(s1, s2, s3);
	}
	
	public void tick()
	{
//		System.out.println(this);
		
//		if(rot.x != 0 || rot.y != 0 || rot.z != 0)
//		{
//			System.out.println(rot);
//		}
	}
	
//	public Vector getLookDir()
//	{
//		Vector lookDir = new Vector(new Point(0,focalLength,0).rotateAbout(rot, loc).add(loc.multiply(-1)));
//		return lookDir;
//	}
//	
//	public Plane getPlane()
//	{
//		Vector vec = getLookDir();
//		return new Plane(vec, new Point(loc.add(vec)));
//	}
//	
//	public void moveCam(double camSpeed)
//	{
//		Vector addThis = new Vector(rot.multiply(camSpeed));
//		loc.add(addThis);
//	}
//	
//	public void forward()
//	{
//		loc.add(getLookDir().getUnitVector().multiply(Main.camSpeed));
//	}
//	
//	public void backward()
//	{
//		loc.add(getLookDir().getUnitVector().multiply(-Main.camSpeed));
//	}
//	
//	public void rotateCam(Rotation rotation)
//	{
//		rot = new Rotation(rot.add(rotation));
//	}
}