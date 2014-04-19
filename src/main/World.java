package main;

import java.awt.Graphics;
import java.util.ArrayList;

import math3D.Point3;
import math3D.Rotation;
import entities.*;

public class World
{
	public ArrayList<Entity> objects = new ArrayList<Entity>();
	public ArrayList<Camera> cameras = new ArrayList<Camera>();
	public static int selectedCamera = 0;	//refers to position in cameras ArrayList
	
	public Timer timer = new Timer(1);		//fire every tick (higher = slower spawn rate)
	public boolean fallingCubesMode = false;
	
	public static Cube cube = new Cube(1.0f);
	
	public World()
	{
		Camera cam = new Camera(new math3D.Point3(0,-2.0f,0), new Rotation(0,0,0), 0.1f);	//1 is for the focal length of the camera;
		add(cam);
		
//		add(new OriginPoint(0.1f));
		
		if(fallingCubesMode)
			cam.loc = new Point3(0.0f, -2.082f, 0.697f);
//		else
//			objects.add(new Cube(1.0f));
		
		add(cube);
		
//		add(new FallingCube(0, 0, 2, 0.5f));
//		add(new SpinCube(0.1f));
//		add(new Sphere(0.5, 10, 10));
//		add(new Pyramid(0.5f));
		
		add(new Camera(new math3D.Point3(0,-0.5f,0), new Rotation(0,0,0), 1.0f));
	}
	
	public void switchCameras()
	{
		selectedCamera++;
		if(selectedCamera >= cameras.size())
			selectedCamera = 0;
	}
	
	public Camera getSelectedCamera()
	{
		return cameras.get(selectedCamera);
	}
	
	public void add(Entity entity)
	{
		objects.add(entity);
		if(entity instanceof Camera)
			cameras.add((Camera)entity);
	}
	
	public void remove(Entity entity)
	{
		objects.remove(entity);
	}
	
	public void tick()
	{
//		cube.rot.add(Math.PI/20.0, 0, Math.PI/20.0);
//		cameras.get(1).rot.add(Math.PI/20.0, 0, Math.PI/20.0);c
		
		for(int i = 0; i < objects.size(); i++)
			objects.get(i).tick();
		
		if(fallingCubesMode)
		{
			timer.tick();
			if(timer.isFiring())
				objects.add(new FallingCube((float)(Math.random()*2.0-1), (float)(Math.random()*2.0-1), 2.0f, (float)(Math.random()*0.5+0.3)));
		}
		
		//check for objects that need to be destroyed
		for(int i = 0; i < objects.size(); i++)
			if(objects.get(i).destroyMe)
				objects.remove(objects.get(i));
	}
	
	public void render(Graphics g)
	{
		Render.render(this, g);
	}
}
