
public class Employee {
	private String name; //���� �̸�
	private String address; //���� �ּ�
	private String money; //����
	private String phoneNo;//��ȭ��ȣ


//���� �ʵ忡 ���Ͽ� getter�� setter �޼ҵ带 �ۼ��϶�.
//�������Լ� �������Լ�
public String getName(){return name;}
public void setName(String name){	this.name=name;}
public String getAddress(){	return address;}
public void setAddress(String address){	this.address=address;}
public String getMoney(){	return money;}
public void setMoney(String money){	this.money=money;}
public String getPhoneNo(){	return phoneNo;}
public void setPhoneNo(String phoneNo){	this.phoneNo=phoneNo;}

//toSTring()�޼ҵ带 �ۼ��Ͽ� ����.
//�迭�� ����� ��� �����͸� ����Ѵ�.
public String toString(int i) {
	String nName="Name: "+this.name;
	String nAddress="  Address: "+this.address;
	String nMoney="  Annual_salary: "+this.money;
	String nPhoneNo="  Phone: "+this.phoneNo;
	
	return ("���� ��ȣ["+(i+1)+"]   "+nName+nAddress+nMoney+nPhoneNo);
}

}
