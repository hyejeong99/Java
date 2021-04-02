import java.io.*;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
//편의점 클래스 정의 Goods class
public class Goods{
	private String category; //대분류 이름
	private int code=0; //물품번호 부여
	private String name; //제품명
	private int price; //가격
	private int stock;//재고 수량
	Goods(int code, String cate, String n, int p, int s) {//생성자
		this.code=code;
		this.category=cate;
		this.name=n;
		this.price=p;
		this.stock=s;
	}
	Goods(){//기본 생성자
	}
	//접근자 변경자 함수
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
		return ++code;
	}
	void setCode(int c) {
		code=c;
	}
}
