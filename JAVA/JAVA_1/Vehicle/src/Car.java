public class Car extends Vehicle{ //�߻� Ŭ���� ��ӹ��� CarŬ����
	
	double liter=0.0;
	double kilos=0.0;
	
	//������ �Լ� ������ �Լ�
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
//�� �Է� �ȹ޴� ���
  
  /*public class Car extends Vehicle{ //�߻� Ŭ���� ��ӹ��� CarŬ����

	public double getKilosPerLiter() {
		return 12.4;
	}
	
}
*/
	
