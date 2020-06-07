//class imports
import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy
{
	//private instance variables
	private Player player;
	private Location location;
	private Dimension dimension;
	private String direction;

	//constructor
	public Enemy(Location location, Dimension dimension, String direction)
	{
		this.location = location;
		this.dimension = dimension;
		this.direction = direction;
	}

	//overloaded constructor
	public Enemy(Location location, Dimension dimension)
	{
		this.location = location;
		this.dimension = dimension;
	}

	//ACCESSOR METHODS
	public Location getLoc()
	{
		return location;
	}
	public Dimension getDim()
	{
		return dimension;
	}

	//to move
	public void move()
	{
		if(direction.equals("left"))
		{
			getLoc().addX(-1);
		}
		if(direction.equals("right"))
		{
			getLoc().addX(1);
		}
		if(direction.equals("up"))
		{
			getLoc().addY(-1);
		}
		if(direction.equals("down"))
		{
			getLoc().addY(1);
		}

		//for enemy
		if(getLoc().getX() >= 950)
		{
			getLoc().setLocation(950, getLoc().getY());
			direction = "left";
		}
		else if(getLoc().getX() <= 0)
		{
			getLoc().setLocation(0, getLoc().getY());
			direction = "right";
		}
	}

	//return an oval
	public Ellipse2D.Double oval()
	{
		return new Ellipse2D.Double(location.getX(), location.getY(), dimension.getWidth(), dimension.getHeight());
	}

	//returns a rectangle
	public Rectangle rectangle()
	{
		return new Rectangle(location.getX(), location.getY(), dimension.getWidth(), dimension.getHeight());
	}

}