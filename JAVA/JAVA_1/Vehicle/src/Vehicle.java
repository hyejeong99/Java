abstract class Vehicle {//Vehicle 클래스를 추상클래스로 정의
	
	int speed=0; //정수형 변수 speed 추가
	public abstract double getKilosPerLiter(); //getKilosPerLiter 메소드를 추상 클래스로 정의
	public void printSpeed() { //현재 속도를 문자열로 출력하는 메소드
		System.out.println("현재 속도는"+speed+"입니다.");
		
	}
	
	//접근자함수 설정자 함수
		int getSpeed() {
			return speed;
		}
		void setSpeed(int speed) {
			this.speed=speed;
		}
		
}


//값 입력 안받는 방법
 /*abstract class Vehicle {//Vehicle 클래스를 추상클래스로 정의
	
	int speed=0; //정수형 변수 speed 추가
	public abstract double getKilosPerLiter(); //getKilosPerLiter 메소드를 추상 클래스로 정의
	public void printSpeed() { //현재 속도를 문자열로 출력하는 메소드
		System.out.println("현재 속도는"+speed+"입니다.");
		
	}
			
}*/

