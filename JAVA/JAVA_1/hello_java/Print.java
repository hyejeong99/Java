import java.util.Scanner;

public class Print {
	public static void main (String[] args){
		int num1;
		int num2;

	Scanner input=new Scanner(System.in);
	char ch;
	int num1=ch
	System.out.print("라인 문자는?:");
	num1=input.nextInt();
	System.out.print("문자 길이는?:");
	num2=input.nextInt();
		printCharacter(num1, num2);
		System.out.println("Hello, Java");
		printCharcater(num1, num2);
	}
	static void printCharacter(char ch, int num)
		for (int cnt=0; cnt<num; cnt++)
			System.out.print(ch);
		System.out.println();
	}
}