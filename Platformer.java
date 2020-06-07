//class imports
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.Ellipse2D;
import java.io.*;
import javax.imageio.*;

public class Platformer extends JPanel implements KeyListener, Runnable
{
	//private instance variables
	private JFrame frame;
	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
	private Thread thread;
	private Color color;
	private Player player;
	private int GRAVITY;
	private int strength = 0;
	private int terminalVelocity = 3;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private boolean gameOn;
	private Font font;
	private Font font1;
	private Font font2;
	private Font font3;
	private boolean startDone = true;
	private boolean begin = false;
	private long start = System.currentTimeMillis();
	private long seconds = 0;
	private int counter;

	//constructor
	public Platformer()
	{
		font = new Font("GEORGIA", Font.PLAIN, 100);
		font1 = new Font("COMIC SANS MS", Font.PLAIN, 100);
		font2 = new Font("GEORGIA", Font.PLAIN, 35);
		font3 = new Font("TIMES NEW ROMAN", Font.PLAIN, 35);

		//game is playable
		gameOn = true;

		//strength of gravity
		GRAVITY = 1;

		//all keys set to false
		right = false;
		left = false;
		up = false;
		down = false;

		//initialization of JFrame
		frame = new JFrame("Platformer");
		frame.setSize(1000, 750);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		thread = new Thread(this);

		player = new Player(new Location(100, 100), new Dimension(50, 100));
		thread.start();
	}

	//paint method
	public void paint(Graphics graphics)
	{
		//graphics2d setup
		Graphics2D graphics2d = (Graphics2D) graphics;

		if(startDone)
		{
			//starting screen
			graphics2d.setColor(Color.BLACK);
			graphics2d.fillRect(0, 0, frame.getWidth(), frame.getHeight());

			graphics2d.setColor(Color.BLUE);
			graphics2d.setFont(font);
			graphics2d.drawString("PLATFORMER", 150, 250);

			graphics2d.setColor(Color.WHITE);
			graphics2d.setFont(font2);
			graphics2d.drawString("Press Space to Start", 325, 300);
		}

		else
		{
			seconds = ((System.currentTimeMillis() - start) / 1000) % 60;

			//spawns player
			graphics2d.setColor(Color.BLACK);
			graphics2d.fillRect(0, 0, frame.getWidth(), frame.getHeight());
			graphics2d.setColor(Color.WHITE);
			graphics2d.fill(player.shape());
			graphics2d.setColor(Color.WHITE);
			graphics2d.setFont(font3);
			graphics2d.drawString(String.valueOf(seconds), 900, 100);

			//initialization of enemy ArrayList
			for(Enemy enemy: enemies)
			{
				graphics2d.setColor(Color.BLUE);
				graphics2d.fill(enemy.oval());
			}

			//if game over
			if(!gameOn)
			{
				graphics2d.setColor(Color.BLACK);
				graphics2d.fillRect(0, 0, frame.getWidth(), frame.getHeight());
				graphics2d.setColor(Color.RED);
				graphics2d.setFont(font1);
				graphics2d.drawString("GAME OVER", 180, 250);
			}
		}
	}

	//movement
	public void run()
	{
		while(true)
		{
			if(gameOn)
			{
				if(!startDone)
				{
					if(counter == 0)
						makeEnemy();

					//gravity
					strength += GRAVITY;
					if(strength > terminalVelocity)
					{
						strength = terminalVelocity;
					}
					player.location().addY(-strength);

					//regular movement
					if(left)
						player.location().addX(-1);
					if(right)
						player.location().addX(1);
					//jumping
					if(up)
						player.location().addY(5);

					counter++;

					if(counter % 4250 == 0)
						makeEnemy();

					for(Enemy enemy: enemies)
					{
						if(enemy.oval().intersects(player.shape()))
						{
							gameOn = false;
						}
					}

					//frame refresh
					repaint();

					//location restrictions
					//for player
					if(player.location().getX() >= 930)
					{
						player.location().setLocation(930, player.location().getY());
					}
					else if(player.location().getX() <= 0)
					{
						player.location().setLocation(0, player.location().getY());
					}
					else if(player.location().getY() >= 610)
					{
						player.location().setLocation(player.location().getX(), 610);
					}
					else if(player.location().getY() <= 0)
					{
						player.location().setLocation(player.location().getX(), 0);
					}

					System.out.println("(" + player.location().getX() + ", " + player.location().getY() + ")");

					for(Enemy enemy: enemies)
					{
						enemy.move();
					}
				}

				try
				{
					thread.sleep(1);
				}
				catch(InterruptedException e)
				{
				}
			}
		}
	}

	//if key pressed
	public void keyPressed(KeyEvent key)
	{
		//regular movement
		if(key.getKeyCode() == 39)
			right = true;
		if(key.getKeyCode() == 38)
			up = true;
		if(key.getKeyCode() == 40)
			down = true;
		if(key.getKeyCode() == 37)
			left = true;
	}

	//if key released
	//resets each key
	public void keyReleased(KeyEvent key)
	{
		//movement
		if(key.getKeyCode() == 39)
			right = false;
		if(key.getKeyCode() == 38)
			up = false;
		if(key.getKeyCode() == 40)
			down = false;
		if(key.getKeyCode() == 37)
			left = false;

		//to start game
		if((startDone) && (key.getKeyCode() == 32))
		{
			startDone = false;
		}
	}

	//if key typed
	public void keyTyped(KeyEvent key)
	{
	}

	public void makeEnemy()
	{
		int randomLoc = (int)(Math.random() * 900) + 1;

		enemies.add(new Enemy(new Location(randomLoc, 660), new Dimension(50, 50), "right"));
	}

	//main method
	public static void main(String[] args)
	{
		Platformer app = new Platformer();
	}
}