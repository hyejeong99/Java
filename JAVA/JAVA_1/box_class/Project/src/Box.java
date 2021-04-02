
public class Box {
	int width; //3
	int length; //3
	int height; //3
	
	void setWidth(int width) //4
	{
		this.width=width;
	}
	int getWidth() //4
	{
		return width;
	}
	void setLength(int length) //4
	{
		this.length=length;
	}
	int getLength() //4
	{
		return length;
	}
	void setHeight(int height) //4
	{
		this.height=height;
	}
	int getHeight() //4
	{
		return height;
	}

	int getVolume()  //5
	{
		return (width*length*height);
	}

}
