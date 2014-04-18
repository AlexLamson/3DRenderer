package main;

import java.applet.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.*;

/*
iphone 4s
focal length = 0.00411 m
sensor width = 4.80
sensor height = 3.60

cube size = 1m x 1m x 1m
 */

public class Main extends Applet implements Runnable
{
	private static final long serialVersionUID = 8864158495101925325L;		//because stupid warnings
	public static final double version = 0.6;
	public static String windowName = "Alex's 3D Engine (V"+version+")";
	
	public static boolean debugMode = true;
	public static double camSpeed = 1;
	
	public static boolean isRunning = false;
	public static int tickTime = 50;
	public static int pixelSize = 2;

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int screenWidth = (int)screenSize.getWidth();
	public static int screenHeight = (int)screenSize.getHeight();
	public static Dimension realSize;
	public static Dimension size = new Dimension(screenHeight*3/4,screenHeight*3/4);		//will hold the window dimensions (600,500)
	public static Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);

	public static Point mse = new Point(0, 0);
	public static boolean isMouseLeft = false;
	public static boolean isMouseMiddle = false;
	public static boolean isMouseRight = false;

	private Image screen;
	public static JFrame frame;
	public static World world;

	public Main()
	{
		setPreferredSize(size);
		requestFocus();
	}

	public static void restart()
	{
		Main main = new Main();
		main.start();
	}

	public void start()
	{
//		System.out.println("pixel.width: "+pixel.width+", "+"pixel.height: "+pixel.height);
		
		//defining objects
		world = new World();
		
		addKeyListener(new Listening());
		addMouseListener(new Listening());
		addMouseMotionListener(new Listening());
		addMouseWheelListener(new Listening());

		//start the game loop
		isRunning = true;
		new Thread(this).start();
		requestFocus();
	}

	public void stop()
	{
		isRunning = false;
	}

	public void tick()
	{
//		if(frame.getWidth() != realSize.width || frame.getHeight() != realSize.height)
//			frame.pack();
		
		world.tick();
		
		if(debugMode)
		{
//			System.out.println("Camera is at "+world.cam);
		}
		
	}

	public void render()
	{
		Graphics g = screen.getGraphics();

		g.setColor(Color.lightGray);
		g.fillRect(0, 0, pixel.width, pixel.height);

		world.render(g);
		
		g = getGraphics();

		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();		//throw it away to avoid lag from too many graphics objects
	}

	public void run()
	{
		screen = createVolatileImage(pixel.width, pixel.height);	//actually use the graphics card (less lag)

		while(isRunning)
		{
			tick();
			render();

			try
			{
				Thread.sleep(tickTime);
			}catch(Exception e){ }
		}
	}

	public static void main(String[] args) {
		Main main = new Main();
		
		frame = new JFrame();
		frame.add(main);
		frame.pack();

		realSize = new Dimension(frame.getWidth(), frame.getHeight());

		frame.setTitle(windowName);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);		//null makes it go to the center
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		main.start();
	}
}
