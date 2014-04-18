package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import math3D.Tuple;
import entities.Camera;

public class Listening implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
	public static float camSpeed = 0.01f;
	public static float focalSpeed = 0.1f;
	public static double rotSpeed = Math.PI/20.0;
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		Camera cam = Main.world.cameras.get(World.selectedCamera);
		
		switch(key)
		{
			//wasd keys
		case KeyEvent.VK_W:
			cam.loc.y += camSpeed;
			break;
		case KeyEvent.VK_S:
			cam.loc.y += -camSpeed;
			break;
		case KeyEvent.VK_A:
			cam.loc.x += -camSpeed;
			break;
		case KeyEvent.VK_D:
			cam.loc.x += camSpeed;
			break;
		case KeyEvent.VK_E:
			cam.loc.z += camSpeed;
			break;
		case KeyEvent.VK_Q:
			cam.loc.z += -camSpeed;
			break;
			
		case KeyEvent.VK_Z:
			if(cam.focalLength-focalSpeed > 0)
				cam.focalLength += -focalSpeed;
			break;
		case KeyEvent.VK_X:
			cam.focalLength += focalSpeed;
			break;
			
		case KeyEvent.VK_C:
			System.out.println("focalLength: "+cam.focalLength+" lensWidth: "+cam.lensWidth);
			break;
			
		case KeyEvent.VK_PLUS:
			camSpeed += 0.010f;
			break;
		case KeyEvent.VK_MINUS:
			camSpeed -= 0.010f;
			break;
			
		case KeyEvent.VK_SHIFT:
			break;

		//arrow keys
		case KeyEvent.VK_LEFT:
			Main.world.cameras.get(1).rot.add(0, 0, -rotSpeed);
//			Main.world.getSelectedCamera().rot.add(0, 0, -rotSpeed);
			break;
		case KeyEvent.VK_RIGHT:
			Main.world.cameras.get(1).rot.add(0, 0, rotSpeed);
//			Main.world.getSelectedCamera().rot.add(0, 0, rotSpeed);
			break;
		case KeyEvent.VK_DOWN:
			Main.world.cameras.get(1).rot.add(-rotSpeed, 0, 0);
//			Main.world.getSelectedCamera().rot.add(-rotSpeed, 0, 0);
			break;
		case KeyEvent.VK_UP:
			Main.world.cameras.get(1).rot.add(rotSpeed, 0, 0);
//			Main.world.getSelectedCamera().rot.add(rotSpeed, 0, 0);
			break;
		
		case KeyEvent.VK_SPACE:
			System.out.println(cam.loc);
			break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();

		switch(key)
		{
			//wasd keys
		case KeyEvent.VK_W:
			break;
			
		case KeyEvent.VK_S:
			break;
			
		case KeyEvent.VK_A:
			break;
			
		case KeyEvent.VK_D:
			break;
			
		case KeyEvent.VK_SHIFT:
			break;
			
		case KeyEvent.VK_ESCAPE:
			Main.isRunning = !Main.isRunning;
			break;
		}
	}

	public void keyTyped(KeyEvent e)
	{

	}

	public void mouseClicked(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{

	}

	public void mouseExited(MouseEvent e)
	{

	}

	public void mousePressed(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)			//left click
			Main.isMouseLeft = true;
		else if(e.getButton() == MouseEvent.BUTTON2)			//left click
			Main.isMouseMiddle = true;
		else if(e.getButton() == MouseEvent.BUTTON3)	//right click
			Main.isMouseRight = true;
	}

	public void mouseReleased(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1)			//left click
			Main.isMouseLeft = false;
		else if(e.getButton() == MouseEvent.BUTTON2)			//left click
			Main.isMouseMiddle = false;
		else if(e.getButton() == MouseEvent.BUTTON3)	//right click
			Main.isMouseRight = false;
	}

	public void mouseDragged(MouseEvent e)
	{
		Main.mse.setLocation(e.getX(), e.getY());
	}

	public void mouseMoved(MouseEvent e)
	{
		Main.mse.setLocation(e.getX(), e.getY());
	}

	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if(e.getWheelRotation() < 0)		//scrolled up
		{
			camSpeed += 0.010f;
//			Main.world.cam.focalLength += 0.01;
		}
		else if(e.getWheelRotation() > 0)		//scrolled down
		{
			camSpeed += -0.010f;
//			Main.world.cam.focalLength += -0.01;
		}
//		System.out.println("speed is now "+camS1peed);
//		System.out.println("focal length is now "+Main.world.cam.focalLength);
	}
}
