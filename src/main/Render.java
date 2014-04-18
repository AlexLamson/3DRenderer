package main;

import java.awt.*;

import math3D.*;
import entities.*;

public class Render
{
	//render from the perspective of a specific camera
	public static void render(World world, Graphics g)
	{
		for(Entity ent : world.objects)
			if(ent instanceof VisibleObject)
				((VisibleObject)ent).render(g);
	}

    /*

   camera plane is defined as

   S1 +------+ S2
      |      |
      |      |
   S3 +------+ S4

     */
    
	//if returns Point2(-1f, -1f) then don't draw the point
	public static Point2 getLocalCoords(Tuple globalPoint)	//points on plane
	{
		Camera cam = Main.world.getSelectedCamera();
		Point3 camLoc = cam.loc;
		Plane camPlane = cam.getPlane();
		Point3 topLeftCorner = camPlane.p1;
		Point3 topRightCorner = camPlane.p2;
		Point3 botLeftCorner = camPlane.p3;
		
		Tuple cameraHorz = topRightCorner.sub(topLeftCorner);		//sensorWidth, as a Tuple
		Tuple cameraVert = botLeftCorner.sub(topLeftCorner);		//-sensorHeight, as a Tuple
		Tuple lookVector = cameraHorz.cross(cameraVert);	//vector pointing the way the camera is looking
		
		Tuple dR = globalPoint.sub(camLoc);		//camera's loc is now 0,0,0 from point's perspective
		
		float ndotdR = lookVector.dot(dR);
		
		if (Math.abs(ndotdR) < 1e-6f) // tolerance
		{
			return new Point2(-1f, -1f);
		}
		
		float t = -lookVector.dot(globalPoint.sub(topLeftCorner)) / ndotdR;
		Tuple M = globalPoint.add(dR.scale(t));
		
		Tuple dMS1 = M.sub(topLeftCorner);
		float u = dMS1.dot(cameraHorz);
		float v = dMS1.dot(cameraVert);
		
//		if(u >= 0.0f && u <= dS21.dot(dS21) && v >= 0.0f && v <= dS31.dot(dS31))		//if on camera's plane
		{
			float screenX = (u/cam.lensWidth)*Main.pixel.width;
			float screenY = (v/cam.lensHeight)*Main.pixel.height;
			
//			float screenX = (u/Main.world.cam.lensWidth)*100;
//			float screenY = (v/Main.world.cam.lensWidth)*100;
			
//			System.out.println("point ("+screenX+", "+screenY+")");
			
			return new Point2(screenX, screenY);
		}
		
//		return new Point2(-1f, -1f);
	}
	
	public static Color randomColor()
	{
		return new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255), 128);
	}
}