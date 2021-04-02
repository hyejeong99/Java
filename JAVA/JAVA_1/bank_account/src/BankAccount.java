
public class BankAccount {
	double balance; //잔액을 표시하는 변수 //6
	void deposit(double amount) //저금
	{
		balance+=amount;
		System.out.println(amount+"원이 예금되었습니다.");
	}
	void withdraw(double amount) //인출 //5번 /*잔고가 없습니다 안됨*/
	{
		
		if(balance>=amount)
		{
			System.out.println(amount+"원이 인출되었습니다.");
			balance-=amount;
			
		}
		else
		{
			if(balance<=0)
			{
				System.out.println("잔고가 없습니다. 인출 실패!!");
			}
			System.out.println("잔액이 모자랍니다. 인출 실패!!");
		}
	}
	double getbalance()
	{
		return balance;
	}
	void setbalance(int balance) //1번
	{
		this.balance=balance;
	}
	void printBalance() //3번 
	{
		
		System.out.println("현재 잔액:"+getbalance());
	}
	double addInterest() //4번
	{
		return (getbalance()*0.075);
	}
	

}
