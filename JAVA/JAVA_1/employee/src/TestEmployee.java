import java.util.Scanner;
public class TestEmployee {
	public static void main(String args[])
	{
		Scanner scan=new Scanner(System.in);
		//�迭 ���� ������ �����Ѵ�.
		Employee[] a;
		//�迭�� �����Ѵ�.
		a=new Employee[2];
		for(int i=0; i<a.length;i++)
		{
			a[i]=new Employee();
		}

		
		//����ڷκ��� �����͸� �޾Ƽ� �迭�� �߰��Ͽ� ����.
		for(int i=0;i<a.length;i++)
		{
			System.out.print("�̸��� �Է��ϼ���"+"[������ȣ"+(i+1)+"]:");
			a[i].setName(scan.next());
			System.out.print("�ּҸ� �Է��ϼ���"+"[������ȣ"+(i+1)+"]:");
			a[i].setAddress(scan.next());
			System.out.print("������ �Է��ϼ���"+"[������ȣ"+(i+1)+"]:");
			a[i].setMoney(scan.next());
			System.out.print("��ȭ��ȣ�� �Է��ϼ���"+"[������ȣ"+(i+1)+"]:");
			a[i].setPhoneNo(scan.next());
		}
		//�迭 ������ ����
		System.out.println("�����Ͻðڽ��ϱ�?(�����ϸ�Y, �ƴϸ�N)");
		char answer=scan.next().charAt(0);
		{
			while(answer!='y'&&answer!='Y'&&answer!='n'&&answer!='N')//y�� n�� �ƴ� �ٸ� ���� �Է½� ����ó��
			{
				System.out.println("Y�� N�� �Է����ּ���.");
				System.out.println("�����Ͻðڽ��ϱ�?(�����ϸ�Y, �ƴϸ�N)");
				answer=scan.next().charAt(0);
			
			}
			if(answer=='y'||answer=='Y')
			{
				System.out.println("�����ϰ� ���� ������ȣ�� �Է��ϼ���:");
				int num1=scan.nextInt();
				{
					while(num1!=1&&num1!=2)//1�� 2�� �ƴ� �ٸ� ��ȣ �Է½� ����ó��
					{
						System.out.println("������ȣ�� 1�� 2���Դϴ�.");
						System.out.println("�����ϰ� ���� ������ȣ�� �Է��ϼ���:");
						num1=scan.nextInt();
					
					}
				}
				//1�� ���� ���� �����ϱ�
				if(num1==1)
				{
					System.out.print("�̸��� �Է��ϼ���"+"[������ȣ1]:");
					a[0].setName(scan.next());
					System.out.print("�ּҸ� �Է��ϼ���"+"[������ȣ1]:");
					a[0].setAddress(scan.next());
					System.out.print("������ �Է��ϼ���"+"[������ȣ1]:");
					a[0].setMoney(scan.next());
					System.out.print("��ȭ��ȣ�� �Է��ϼ���"+"[������ȣ1]:");
					a[0].setPhoneNo(scan.next());
				}
				//2�� ���� ���� �����ϱ�
				if(num1==2)
				{
					System.out.print("�̸��� �Է��ϼ���"+"[������ȣ2]:");
					a[0].setName(scan.next());
					System.out.print("�ּҸ� �Է��ϼ���"+"[������ȣ2]:");
					a[0].setAddress(scan.next());
					System.out.print("������ �Է��ϼ���"+"[������ȣ2]:");
					a[0].setMoney(scan.next());
					System.out.print("��ȭ��ȣ�� �Է��ϼ���"+"[������ȣ2]:");
					a[0].setPhoneNo(scan.next());
				}
				
			}
			else if(answer=='n'||answer=='N')
				System.out.println("������ �������� �ʽ��ϴ�.");
				
		//�迭�� ����� ��� �����͸� ����Ѵ�.
		for(int i=0; i<a.length; i++)
			System.out.println(a[i].toString(i));
	
		
		
		
		}
	}

}
