import java.io.*;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//������ Ŭ���� ���� Goods class
public class Goods{
	private String category; //��з� �̸�
	private int code=0; //��ǰ��ȣ �ο�
	private String name; //��ǰ��
	private int price; //����
	private int stock;//��� ����
	
	Goods(String cate, String n, int p, int s, int code) {//������
		this.category=cate;
		this.name=n;
		this.price=p;
		this.stock=s;
		this.code=code;//�ڵ� ����
	}
	Goods(){//������
	}
	//������ ������ �Լ�
	String getCategory() {
		return category;
	}
	void setCategory(String c) {
		category=c;
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
	/*public void addStock(int s) { // ��� ����
		this.stock+=s;
	}
	public void subStock(int s) throws Exception { // ��� �� 
		if(this.stock==0)
			throw new Exception("��� �����մϴ�.");
		else this.stock-=stock;
	}*/
	void save(ObjectOutputStream out)throws Exception{
		//��ü�� �������ʵ��� ���� ���Ϸ� ��� ����� ����� �̷������ ������ �ͼ���
		try {
			out.writeUTF(category);
			out.writeUTF(name);
			out.writeInt(price);
			out.writeInt(stock);
			out.writeInt(code);
		}
		catch(IOException ioe) {
			throw new Exception("���� ��� ����");
		}
		catch(Exception e) { //����ó��
			throw new Exception("save�� ���� �߻�");
		}
	}
	void read(ObjectInputStream in)throws Exception{
		//��ü�� �������ʵ忡 ���� ���� �о���°� ����� �ȵǸ� �ͼ���
		try {
			category=in.readUTF();
			name=in.readUTF();
			price=in.readInt();
			stock=in.readInt();
			code=in.readInt();
		}
		catch(UTFDataFormatException e) {
			throw new Exception("���� �б� ����");
		}
		catch(IOException e) { //������ ���� �� ���� ���
			throw new Exception("���� �б� ����");
		}
		catch (Exception e) { //����ó��
			throw new Exception("load�� ���� �߻�");
		}
	}
}
