import java.io.*;
//������ Ŭ���� ���� Goods class
public class Goods {
	private String category; //��з� �̸�
	private int code=0; //��ǰ��ȣ �ο�
	private String name; //��ǰ��
	private int price; //����
	private int stock;//��� ����
	
	Goods(String cate, String n, int p, int s) {//������
		this.category=cate;
		this.name=n;
		this.price=p;
		this.stock=s;
		code++;//�ڵ� ����
	}
	Goods(){//������
		
	}
	//������ ������ �Լ�
	String getCategory() {
		return category;
	}
	void setCategory(String cate) {
		category=cate;
	}
	String getName() {
		return name;
	}
	void setName(String n) {
		name=n;
	}
	int getPrice() {
		return price;
	}
	void setPrice(int p) {
		price=p;
	}
	int getStock() {
		return stock;
	}
	void setStock(int s) {
		stock=s;
	}
	int getCode() {
		return code;
	}
	void setCode(int c) {
		code=c;
	}
	//��� ���ϰ� ���� ����� �ϴ� �Լ���
	public void addStock(int stock) { // ��� ����
		this.stock+=stock;
	}
	
	public void subStock(int stock) throws Exception { // ��� �� 
		if(this.stock==0)
			throw new Exception("��� �����մϴ�.");
		this.stock-=stock;
	}
	void save(DataOutputStream out)throws Exception{
		//��ü�� �������ʵ��� ���� ���Ϸ� ���
		//����� ����� �̷������ ������ �ͼ���
		try {
			out.writeUTF(category);
			out.writeUTF(name);
			out.writeInt(code);
			out.writeInt(price);
			out.writeInt(stock);
		}
		catch(Exception e) { //����ó��
			throw new Exception("save�� ���� �߻�");
		}
	}
	void read(DataInputStream in)throws Exception{
		//��ü�� �������ʵ忡 ���� ����
		//�о���°� ����� �ȵǸ� �ͼ���
		try {
			category=in.readUTF();
			name=in.readUTF();
			code=in.readInt();
			price=in.readInt();
			stock=in.readInt();
		}
		catch (Exception e) { //����ó��
			throw new Exception("load�� ���� �߻�");
		}
	}
}
