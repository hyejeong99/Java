import java.util.Scanner;
public class TestEmployee {
	public static void main(String args[])
	{
		Scanner scan=new Scanner(System.in);
		//배열 참조 변수를 선언한다.
		Employee[] a;
		//배열을 생선한다.
		a=new Employee[2];
		for(int i=0; i<a.length;i++)
		{
			a[i]=new Employee();
		}

		
		//사용자로부터 데이터를 받아서 배열에 추가하여 본다.
		for(int i=0;i<a.length;i++)
		{
			System.out.print("이름을 입력하세요"+"[직원번호"+(i+1)+"]:");
			a[i].setName(scan.next());
			System.out.print("주소를 입력하세요"+"[직원번호"+(i+1)+"]:");
			a[i].setAddress(scan.next());
			System.out.print("연봉을 입력하세요"+"[직원번호"+(i+1)+"]:");
			a[i].setMoney(scan.next());
			System.out.print("전화번호를 입력하세요"+"[직원번호"+(i+1)+"]:");
			a[i].setPhoneNo(scan.next());
		}
		//배열 내용을 수정
		System.out.println("수정하시겠습니까?(수정하면Y, 아니면N)");
		char answer=scan.next().charAt(0);
		{
			while(answer!='y'&&answer!='Y'&&answer!='n'&&answer!='N')//y나 n이 아닌 다른 문자 입력시 예외처리
			{
				System.out.println("Y나 N로 입력해주세요.");
				System.out.println("수정하시겠습니까?(수정하면Y, 아니면N)");
				answer=scan.next().charAt(0);
			
			}
			if(answer=='y'||answer=='Y')
			{
				System.out.println("수정하고 싶은 직원번호를 입력하세요:");
				int num1=scan.nextInt();
				{
					while(num1!=1&&num1!=2)//1과 2가 아닌 다른 번호 입력시 예외처리
					{
						System.out.println("직원번호는 1과 2뿐입니다.");
						System.out.println("수정하고 싶은 직원번호를 입력하세요:");
						num1=scan.nextInt();
					
					}
				}
				//1번 직원 내용 수정하기
				if(num1==1)
				{
					System.out.print("이름을 입력하세요"+"[직원번호1]:");
					a[0].setName(scan.next());
					System.out.print("주소를 입력하세요"+"[직원번호1]:");
					a[0].setAddress(scan.next());
					System.out.print("연봉을 입력하세요"+"[직원번호1]:");
					a[0].setMoney(scan.next());
					System.out.print("전화번호를 입력하세요"+"[직원번호1]:");
					a[0].setPhoneNo(scan.next());
				}
				//2번 직원 내용 수정하기
				if(num1==2)
				{
					System.out.print("이름을 입력하세요"+"[직원번호2]:");
					a[0].setName(scan.next());
					System.out.print("주소를 입력하세요"+"[직원번호2]:");
					a[0].setAddress(scan.next());
					System.out.print("연봉을 입력하세요"+"[직원번호2]:");
					a[0].setMoney(scan.next());
					System.out.print("전화번호를 입력하세요"+"[직원번호2]:");
					a[0].setPhoneNo(scan.next());
				}
				
			}
			else if(answer=='n'||answer=='N')
				System.out.println("내용을 수정하지 않습니다.");
				
		//배열에 저장된 모든 데이터를 출력한다.
		for(int i=0; i<a.length; i++)
			System.out.println(a[i].toString(i));
	
		
		
		
		}
	}

}
