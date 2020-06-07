//class imports
import java.awt.Rectangle;

public class Player
{
	//private instance variables
	private Dimension dimension;
	private Location loc;

	//constructor
	public Player(Location loc, Dimension dimension)
	{
		this.loc = loc;
		this.dimension = dimension;
	}

	//ACCESSOR METHODS
	//returns new Rectangle object
	public Rectangle shape()
	{
		return new Rectangle(loc.getX(), loc.getY(), dimension.getWidth(), dimension.getHeight());
	}
	//returns dimension of player
	public Dimension dimension()
	{
		return dimension;
	}
	//return location of player
	public Location location()
	{
		return loc;
	}
}
