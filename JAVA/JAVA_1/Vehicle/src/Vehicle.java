abstract class Vehicle {//Vehicle Ŭ������ �߻�Ŭ������ ����
	
	int speed=0; //������ ���� speed �߰�
	public abstract double getKilosPerLiter(); //getKilosPerLiter �޼ҵ带 �߻� Ŭ������ ����
	public void printSpeed() { //���� �ӵ��� ���ڿ��� ����ϴ� �޼ҵ�
		System.out.println("���� �ӵ���"+speed+"�Դϴ�.");
		
	}
	
	//�������Լ� ������ �Լ�
		int getSpeed() {
			return speed;
		}
		void setSpeed(int speed) {
			this.speed=speed;
		}
		
}


//�� �Է� �ȹ޴� ���
 /*abstract class Vehicle {//Vehicle Ŭ������ �߻�Ŭ������ ����
	
	int speed=0; //������ ���� speed �߰�
	public abstract double getKilosPerLiter(); //getKilosPerLiter �޼ҵ带 �߻� Ŭ������ ����
	public void printSpeed() { //���� �ӵ��� ���ڿ��� ����ϴ� �޼ҵ�
		System.out.println("���� �ӵ���"+speed+"�Դϴ�.");
		
	}
			
}*/

