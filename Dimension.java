public class Dimension
{
	//private instance variables
	private int width;
	private int height;

	//constructor
	public  Dimension(int width,int height)
	{
		this.width = width;
		this.height = height;
	}

	//ACCESSOR METHODS
	//returns width
	public int getWidth()
	{
		return width;
	}
	//returns height
	public int getHeight()
	{
		return height;
	}

	//MUTATOR METHODS
	//sets width
	public void setWidth(int a)
	{
		width = a;
	}
	//sets height
	public void setHeight(int b)
	{
		width = b;
	}
}