package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

import main.Main;
import main.Render;
import main.World;
import math3D.*;
import entities.visible.*;

public class VisibleObject extends Entity
{
	//this should be acceptable: {(0,1,2,3),(4,5,6),(7,8),(9)}
	//								quad	  tri	 edge point
	
	public ArrayList<Point3> points = new ArrayList<Point3>();
	public ArrayList<Visible> visibles = new ArrayList<Visible>();
	
	public VisibleObject()
	{
		
	}
	
	public Point3 getGlobalPoint(int i)
	{
		//apply rotation
		Point3 localPoint = points.get(i).rotateAbout(rot, loc);
		
		//apply translation
		float x = loc.x + localPoint.x;
		float y = loc.y + localPoint.y;
		float z = loc.z + localPoint.z;
		
		return new Point3(x, y, z);
	}
	
	public void tick()
	{
		super.tick();
	}
	
	public void render(Graphics g)
	{
		if(this == Main.world.getSelectedCamera())
			return;
		
		Color c1 = new Color(255, 0, 0, 125);
		Color c2 = new Color(0, 0, 255, 125);
		Color c3 = new Color(0, 255, 0, 125);
		Color c4 = new Color(173, 216, 230, 125);
		Color c5 = new Color(0, 0, 0, 100);
		
		for(Visible v : visibles)
		{
			if(v.useColor)
			{
				c1 = v.color;
				c2 = v.color;
				c3 = v.color;
				c4 = v.color;
				c5 = v.color;
			}
			
			if(v instanceof VPoint)
			{
				Point2 p = Render.getLocalCoords(getGlobalPoint(v.points[0]));
				g.setColor(c1);
				g.drawOval((int)p.x-5, (int)p.y-5, 10, 10);
			}
			else if(v instanceof VEdge)
			{
				Point2 p1 = Render.getLocalCoords(getGlobalPoint(v.points[0]));
				Point2 p2 = Render.getLocalCoords(getGlobalPoint(v.points[1]));
				g.setColor(c2);
				g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
			}
			else if(v instanceof VTri)
			{
				Point2 p1 = Render.getLocalCoords(getGlobalPoint(v.points[0]));
				Point2 p2 = Render.getLocalCoords(getGlobalPoint(v.points[1]));
				Point2 p3 = Render.getLocalCoords(getGlobalPoint(v.points[2]));
				Polygon poly = new Polygon(new int[]{(int)p1.x, (int)p2.x, (int)p3.x}, new int[]{(int)p1.y, (int)p2.y, (int)p3.y}, 3);
				g.setColor(c3);
				g.fillPolygon(poly);
			}
			else if(v instanceof VQuad)
			{
				Point2 p1 = Render.getLocalCoords(getGlobalPoint(v.points[0]));
				Point2 p2 = Render.getLocalCoords(getGlobalPoint(v.points[1]));
				Point2 p3 = Render.getLocalCoords(getGlobalPoint(v.points[2]));
				Point2 p4 = Render.getLocalCoords(getGlobalPoint(v.points[3]));
				Polygon poly = new Polygon(new int[]{(int)p1.x, (int)p2.x, (int)p3.x, (int)p4.x}, new int[]{(int)p1.y, (int)p2.y, (int)p3.y, (int)p4.y}, 4);
				g.setColor(c4);
				g.fillPolygon(poly);
				g.setColor(c5);
				g.drawLine((int)p1.x, (int)p1.y, (int)p2.x, (int)p2.y);
				g.drawLine((int)p2.x, (int)p2.y, (int)p3.x, (int)p3.y);
				g.drawLine((int)p3.x, (int)p3.y, (int)p4.x, (int)p4.y);
				g.drawLine((int)p4.x, (int)p4.y, (int)p1.x, (int)p1.y);
			}
			else if(v instanceof VPoly)
			{
				ArrayList<Point2> ps = new ArrayList<Point2>();
				
				for(int i = 0; i < v.points.length; i++)
					ps.add( Render.getLocalCoords(getGlobalPoint(v.points[i])) );
				
				int[] xPolyPoints = new int[v.points.length];
				for(int i = 0; i < ps.size(); i++)
					xPolyPoints[i] = (int)(ps.get(i).x);
				
				int[] yPolyPoints = new int[v.points.length];
				for(int i = 0; i < ps.size(); i++)
					yPolyPoints[i] = (int)(ps.get(i).y);
				
				Polygon poly = new Polygon(xPolyPoints, yPolyPoints, v.points.length);
				
				g.setColor(c4);
				g.fillPolygon(poly);
				
				g.setColor(c5);
				
				int size = v.points.length;
				for(int i = 0; i < ps.size(); i++)
				{
					g.drawLine((int)(ps.get(i).x), (int)(ps.get(i).y), (int)(ps.get((i+1)%size).x), (int)(ps.get((i+1)%size).y));
				}
			}
		}
		
//		for(int i = 0; i < points.size(); i++)
//		{
//			Point2 p2 = Render.getLocalCoords(getGlobalPoint(i));
//			g.setColor(Color.red);
//			int size = 2;
//			
////			circles
////			g.drawOval((int)p2.x-size, (int)p2.y-size, 2*size, 2*size);
//			
////			X's
////			g.drawLine((int)p2.x-size, (int)p2.y-size, (int)p2.x+size, (int)p2.y+size);
////			g.drawLine((int)p2.x+size, (int)p2.y-size, (int)p2.x-size, (int)p2.y+size);
//			
////			blue debug lines
////			g.setColor(Color.blue);
////			g.drawLine(0, 0, (int)p2.x, (int)p2.y);
////			System.out.print(p2.x+","+p2.y);
////			System.out.print("\n");
//		}
	}
}