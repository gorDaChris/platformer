public class Location
{
	//private instance variables
	private int x;
	private int y;

	//constructor
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	//ACCESSOR METHODS
	//returns x coordinate
	public int getX()
	{
		return x;
	}
	//returns y coordinate
	public int getY()
	{
		return y;
	}

	//MUTATOR METHODS
	//modifies x coordinate
	public void addX(int a)
	{
		x += a;
	}
	//modifies y coordinate
	public void addY(int b)
	{
		y -= b;
	}
	//sets location
	public void setLocation(int a,int b)
	{
		x = a;
		y = b;
	}
}