import java.util.Scanner;

public class CarTest{
	public static void main(String args[]){
		//��ü ���� ����
		Car c1 =new Car();
		
		//�� �Է� �ޱ�
		Scanner input=new Scanner(System.in);
		System.out.print("kilos=");
		c1.setKilos(input.nextDouble());
		System.out.print("liter=");
		c1.setLiter(input.nextDouble());
		
		//����� �ӵ� ������ֱ�
		System.out.println("�����"+c1.getKilosPerLiter()+"�Դϴ�.");
		c1.printSpeed();

		
	}

}

//�� �Է� �ȹ޴� ���

/*public class CarTest{
	public static void main(String args[]){
		//��ü ���� ����
		Car c1 =new Car();
		//����� �ӵ� ������ֱ�
		System.out.println("�����"+c1.getKilosPerLiter()+"�Դϴ�.");
		c1.printSpeed();

		
	}

}
*/
