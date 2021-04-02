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
	System.out.println("---숫자 맞추기 게임---");

	while(num<5)
		{
			System.out.print("발생된 숫자는?");
			findAnswer=scan.nextInt();
			if(findAnswer>key)
			{
				num++;	
				System.out.print("입력한 숫자가 정답보다 큰 수입니다.");
			}

 			else if(findAnswer<key)
			{
				num++;	
				System.out.print("입력한 숫자가 정답보다 적은 수입니다.");
			}
			else
			{
				num++;	
				System.out.print("num" "회수만에 정답을 맞추었습니다.");
				break;
			}
	break;
	System.out.print("정답을 5번 시도에 맞추지 못했습니다.")
		
		}
	}
}