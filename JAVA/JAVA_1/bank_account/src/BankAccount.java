
public class BankAccount {
	double balance; //�ܾ��� ǥ���ϴ� ���� //6
	void deposit(double amount) //����
	{
		balance+=amount;
		System.out.println(amount+"���� ���ݵǾ����ϴ�.");
	}
	void withdraw(double amount) //���� //5�� /*�ܰ� �����ϴ� �ȵ�*/
	{
		
		if(balance>=amount)
		{
			System.out.println(amount+"���� ����Ǿ����ϴ�.");
			balance-=amount;
			
		}
		else
		{
			if(balance<=0)
			{
				System.out.println("�ܰ� �����ϴ�. ���� ����!!");
			}
			System.out.println("�ܾ��� ���ڶ��ϴ�. ���� ����!!");
		}
	}
	double getbalance()
	{
		return balance;
	}
	void setbalance(int balance) //1��
	{
		this.balance=balance;
	}
	void printBalance() //3�� 
	{
		
		System.out.println("���� �ܾ�:"+getbalance());
	}
	double addInterest() //4��
	{
		return (getbalance()*0.075);
	}
	

}
