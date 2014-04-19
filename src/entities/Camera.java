package entities;

import java.awt.Color;

import entities.visible.Visible;
import main.Main;
import math3D.*;

public class Camera extends VisibleObject
{
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
		points.add(new Point3(getLookDir()));
		
		int[][] faces = 
			{{0,1},
			{0,2},
			{2,3},
			{1,3},
			{0,4},
			{1,4},
			{2,4},
			{3,4},
			{4,5}};
		
		for(int i = 0; i < faces.length; i++)
		{
			if(i < faces.length -1)
				visibles.add(Visible.pointsToVisible(faces[i]));
			else
				visibles.add(Visible.pointsToVisible(faces[i], Color.red));
		}
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
	
	public Vector getLookDir()
	{
		Plane camPlane = getPlane();
		Point3 topLeftCorner = camPlane.p1;
		Point3 topRightCorner = camPlane.p2;
		Point3 botLeftCorner = camPlane.p3;
		
		Tuple cameraHorz = topRightCorner.sub(topLeftCorner);		//sensorWidth, as a Tuple
		Tuple cameraVert = botLeftCorner.sub(topLeftCorner);		//-sensorHeight, as a Tuple
		Tuple lookVector = cameraHorz.cross(cameraVert);	//vector pointing the way the camera is looking
		return new Vector(lookVector.add(new Tuple(0,0,-1)));
	}
	
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