package entities.visible;

import java.awt.Color;

//A Visible is an array of references to the 3d Points in the ArrayList of points in a VisibleObject
public class Visible
{
	public int[] points;
	public Color color;
	public boolean useColor = false;
	
	public Visible(int[] points)
	{
		this.points = points;
	}
	
	public Visible(int[] points, Color color)
	{
		this.points = points;
		this.color = color;
		useColor = true;
	}
	
	public static Visible pointsToVisible(int[] vis)
	{
		switch(vis.length)
		{
		case 1:
			return new VPoint(vis);
		case 2:
			return new VEdge(vis);
		case 3:
			return new VTri(vis);
		case 4:
			return new VQuad(vis);
		}
		return new VPoly(vis);
	}
	
	public static Visible pointsToVisible(int[] vis, Color color)
	{
		switch(vis.length)
		{
		case 1:
			return new VPoint(vis);
		case 2:
			return new VEdge(vis, color);
		case 3:
			return new VTri(vis, color);
		case 4:
			return new VQuad(vis, color);
		}
		return new VPoly(vis, color);
	}
}
