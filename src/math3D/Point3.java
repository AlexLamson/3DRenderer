package math3D;

public class Point3 extends Tuple
{
	public Point3(float x, float y, float z)
	{
		super(x, y, z);
	}
	
	public Point3(Tuple tup)
	{
		super(tup.x, tup.y, tup.z);
	}
	
	//rotate this point about point by rot
	public Point3 rotateAbout(Rotation rot, Point3 point)
	{
		//subtract offset
		Tuple offsetPoint = new Tuple(x, y, z).add(point.scale(-1));
		
		float oldX = offsetPoint.x, oldY = offsetPoint.y, oldZ = offsetPoint.z;
		
		float ySave = (float) (oldY * Math.cos(rot.x) - oldZ * Math.sin(rot.x)); //rotate about X
		float zSave = (float) (oldY * Math.sin(rot.x) + oldZ * Math.cos(rot.x)); //rotate about X

		oldZ = zSave;
		float xSave = (float) (oldZ * Math.sin(rot.y) + oldX * Math.cos(rot.y)); //rotate about Y
		zSave = (float) (oldZ * Math.cos(rot.y) - oldX * Math.sin(rot.y));      //rotate about Y

		oldX = xSave;
		oldY = ySave;
		xSave = (float) (oldX * Math.cos(rot.z) - oldY * Math.sin(rot.z));      //rotate about Z
		ySave = (float) (oldX * Math.sin(rot.z) + oldY * Math.cos(rot.z));      //rotate about Z
		
		//add offset
		offsetPoint.x = xSave;
		offsetPoint.y = ySave;
		offsetPoint.z = zSave;
		
		Tuple result = offsetPoint.add(point);
		
		return new Point3(result.x, result.y, result.z);
	}
}
