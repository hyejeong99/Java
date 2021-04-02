
public class ShapeTest {

	private static Shape arrayOfShapes[];
	
	public static void main(String[] args) {
		init();
		drawAll();
		System.out.println();
		System.out.println(calcArea(arrayOfShapes[0]));
		System.out.println(calcArea(arrayOfShapes[1]));
		System.out.println(calcArea(arrayOfShapes[2]));
	}

	public static void init(){
		arrayOfShapes = new Shape[3];
		arrayOfShapes[0] = new Rectangle();
		arrayOfShapes[1] = new Triangle();
		arrayOfShapes[2] = new Circle();
		
		((Rectangle) arrayOfShapes[0]).setWidth(5);
		((Rectangle) arrayOfShapes[0]).setHeight(3);
		((Triangle) arrayOfShapes[1]).setBase(5);
		((Triangle) arrayOfShapes[1]).setHeight(3);
		((Circle) arrayOfShapes[2]).setRadius(3);
		
		}

	public static void drawAll(){
		for(int i = 0; i<arrayOfShapes.length; i++){
			arrayOfShapes[i].draw();
		}
	}
	

	public static double calcArea(Shape s) {
		double area=0.0;
		if(s instanceof Rectangle) {
			int w=((Rectangle)s).getWidth();
			int h=((Rectangle)s).getHeight();
			area=(double)(w*h);
		}
		//다른 도형들의 면적을 구한다.
		if(s instanceof Triangle) {
			int b=((Triangle)s).getBase();
			int h=((Triangle)s).getHeight();
			area=(double)(0.5*b*h);
		}
		if(s instanceof Circle) {
			int r=((Circle)s).getRadius();
			area=(double)(r*r*3.14);
		}
		return area;
	}
	
}