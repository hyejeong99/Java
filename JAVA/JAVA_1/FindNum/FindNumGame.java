import java.util.Random;
import java.util.Scanner;
 

public class FindNumGame 
{

	public static void main (String[] args)
	{
	String answer;
	Scanner scan=new Scanner(System.in);
	Random rand = new Random();
	int key;
	int findAnswer;
	int num=0;
	key=rand.nextInt(100)+1;
	System.out.println("---���� ���߱� ����---");

	while(num<5)
		{
			System.out.print("�߻��� ���ڴ�?");
			findAnswer=scan.nextInt();
			if(findAnswer>key)
			{
				num++;	
				System.out.print("�Է��� ���ڰ� ���亸�� ū ���Դϴ�.");
			}

 			else if(findAnswer<key)
			{
				num++;	
				System.out.print("�Է��� ���ڰ� ���亸�� ���� ���Դϴ�.");
			}
			else
			{
				num++;	
				System.out.print("num" "ȸ������ ������ ���߾����ϴ�.");
				break;
			}
	break;
	System.out.print("������ 5�� �õ��� ������ ���߽��ϴ�.")
		
		}
	}
}