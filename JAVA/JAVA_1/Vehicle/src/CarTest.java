import java.util.Scanner;

public class CarTest{
	public static void main(String args[]){
		//객체 생성 문장
		Car c1 =new Car();
		
		//값 입력 받기
		Scanner input=new Scanner(System.in);
		System.out.print("kilos=");
		c1.setKilos(input.nextDouble());
		System.out.print("liter=");
		c1.setLiter(input.nextDouble());
		
		//연비와 속도 출력해주기
		System.out.println("연비는"+c1.getKilosPerLiter()+"입니다.");
		c1.printSpeed();

		
	}

}

//값 입력 안받는 방법

/*public class CarTest{
	public static void main(String args[]){
		//객체 생성 문장
		Car c1 =new Car();
		//연비와 속도 출력해주기
		System.out.println("연비는"+c1.getKilosPerLiter()+"입니다.");
		c1.printSpeed();

		
	}

}
*/
