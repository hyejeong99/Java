import java.io.*;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
//������ Ŭ���� ���� Goods class
public class Goods{
	private String category; //��з� �̸�
	private String code; //��ǰ��ȣ �ο�
	private String name; //��ǰ��
	private int price; //����
	private int stock;//��� ����
	Goods(String code, String cate, String n, int p, int s) {//������
		this.code=code;//�ڵ� ����
		this.category=cate;
		this.name=n;
		this.price=p;
		this.stock=s;
	}
	Goods(){//�⺻ ������
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
	String getCode() {
		return code;
	}
	void setCode(String c) {
		code=c;
	}
}
