
public class BankAccountTest {//8
	public static void main(String[] args)
	{
		System.out.println("BankAccountTest Ŭ������ �����Լ�");
		BankAccount a1=new BankAccount();
		
		a1.deposit(100.45);
		a1.withdraw(60.78);
		
		a1.printBalance();
		System.out.println("a1�� �� ���ڴ�:"+a1.addInterest());
		a1.withdraw(50.698);
		a1.printBalance();
		a1.withdraw(40.412);
		a1.printBalance();
		a1.withdraw(10.387);
		a1.printBalance();
		


		// System.out.println("a1�� �ܰ�:"+a1.getbalance());
		// System.out.println("a2�� �ܰ�:"+a2.getbalance());
	
	}


}
