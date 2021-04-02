public class Car extends Vehicle{ //추상 클래스 상속받은 Car클래스
	
	double liter=0.0;
	double kilos=0.0;
	
	//접근자 함수 설정자 함수
	double getKilos() {
		return kilos;
	}
	void setKilos(double kilos) {
		this.kilos=kilos;
	}
	double getLiter() {
		return liter;
	}
	void setLiter(double liter) {
		this.liter=liter;
	}
	
	public double getKilosPerLiter() {
		return (kilos/liter);
	}
	
	
}
//값 입력 안받는 방법
  
  /*public class Car extends Vehicle{ //추상 클래스 상속받은 Car클래스

	public double getKilosPerLiter() {
		return 12.4;
	}
	
}
*/
	
