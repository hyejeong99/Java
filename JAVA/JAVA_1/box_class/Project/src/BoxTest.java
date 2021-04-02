
public class BoxTest {
	public static void main(String[] args)
	{
		Box box1 = new Box(); //8
		box1.setWidth(100); //9
		box1.setLength(100); //9
		box1.setHeight(100); //9
		System.out.println(box1.getVolume()); //10

		Box box2 = new Box(); //12
		box2.setWidth(200); //12
		box2.setLength(200); //12
		box2.setHeight(200); //12
		System.out.println(box2.getWidth()); //13
		System.out.println(box2.getLength()); //13
		System.out.println(box2.getHeight()); //13
		System.out.println(box2.getVolume()); //13
	}
}
