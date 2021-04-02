import java.io.*;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//편의점 클래스 정의 Goods class
public class Goods{
	private String category; //대분류 이름
	private int code=0; //물품번호 부여
	private String name; //제품명
	private int price; //가격
	private int stock;//재고 수량
	
	Goods(String cate, String n, int p, int s, int code) {//생성자
		this.category=cate;
		this.name=n;
		this.price=p;
		this.stock=s;
		this.code=code;//코드 증가
	}
	Goods(){//생성자
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
		return code;
	}
	void setCode(int c) {
		code=c;
	}
	//재고를 더하고 빼는 기능을 하는 함수들
	/*public void addStock(int s) { // 재고를 더함
		this.stock+=s;
	}
	public void subStock(int s) throws Exception { // 재고를 뺌 
		if(this.stock==0)
			throw new Exception("재고가 부족합니다.");
		else this.stock-=stock;
	}*/
	void save(ObjectOutputStream out)throws Exception{
		//객체의 데이터필드의 값을 파일로 출력 출력이 제대로 이루어지지 않으면 익셉션
		try {
			out.writeUTF(category);
			out.writeUTF(name);
			out.writeInt(price);
			out.writeInt(stock);
			out.writeInt(code);
		}
		catch(IOException ioe) {
			throw new Exception("파일 출력 오류");
		}
		catch(Exception e) { //예외처리
			throw new Exception("save중 에러 발생");
		}
	}
	void read(ObjectInputStream in)throws Exception{
		//객체의 데이터필드에 값을 저장 읽어오는게 제대로 안되면 익셉션
		try {
			category=in.readUTF();
			name=in.readUTF();
			price=in.readInt();
			stock=in.readInt();
			code=in.readInt();
		}
		catch(UTFDataFormatException e) {
			throw new Exception("파일 읽기 오류");
		}
		catch(IOException e) { //파일을 읽을 수 없을 경우
			throw new Exception("파일 읽기 오류");
		}
		catch (Exception e) { //예외처리
			throw new Exception("load중 에러 발생");
		}
	}
}
