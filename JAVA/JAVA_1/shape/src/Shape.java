
class Shape {
	protected int x,y;
	
	public void draw(){
		System.out.println("Shape Draw");
	}
};

class Rectangle extends Shape{
	private int width, height;

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void draw(){
		System.out.println("Rectangle Draw");
	}
}

class Triangle extends Shape{
	private int base, height;

	public void setBase(int base) {
		this.base = base;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getBase() {
		return base;
	}

	public int getHeight() {
		return height;
	}

	public void draw(){
		System.out.println("Triangle Draw");
	}
}

class Circle extends Shape{
	private int radius;

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public void draw(){
		System.out.println("Circle Draw");
	}
}

