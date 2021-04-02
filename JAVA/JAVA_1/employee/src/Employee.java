
public class Employee {
	private String name; //직원 이름
	private String address; //직원 주소
	private String money; //연봉
	private String phoneNo;//전화번호


//각각 필드에 대하여 getter와 setter 메소드를 작성하라.
//접근자함수 설정자함수
public String getName(){return name;}
public void setName(String name){	this.name=name;}
public String getAddress(){	return address;}
public void setAddress(String address){	this.address=address;}
public String getMoney(){	return money;}
public void setMoney(String money){	this.money=money;}
public String getPhoneNo(){	return phoneNo;}
public void setPhoneNo(String phoneNo){	this.phoneNo=phoneNo;}

//toSTring()메소드를 작성하여 본다.
//배열에 저장된 모든 데이터를 출력한다.
public String toString(int i) {
	String nName="Name: "+this.name;
	String nAddress="  Address: "+this.address;
	String nMoney="  Annual_salary: "+this.money;
	String nPhoneNo="  Phone: "+this.phoneNo;
	
	return ("직원 번호["+(i+1)+"]   "+nName+nAddress+nMoney+nPhoneNo);
}

}
