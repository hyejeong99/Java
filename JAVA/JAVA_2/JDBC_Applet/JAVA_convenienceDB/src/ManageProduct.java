import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.LinkedList;
public class ManageProduct extends JFrame implements ActionListener{//관리자 모드
	private JPanel panel=new JPanel();
	private Container contentPane;
	private JFrame frame;private JLabel label,label1;private JTable table;private JComboBox comboBox;
	private JButton add_button,del_button;//추가,삭제 버튼
	private JTextField cate,name,sale,stock;
	String productArr[]={"코드","카테고리","물품명","가격","재고"};//물품 정보
	String productCate[]= {"라면","도시락","삼각김밥","과자","탄산음료","주스","우유","물","젤리","초콜릿"};//콤보박스 카테고리
	private Object[]newArr=null; int code=0, rowNum=0, stockNum=0;
	DefaultTableModel model;
	//private LinkedList<Goods> newGoods=new LinkedList<Goods>();
	Goods newGoods=new Goods();int code1=0;
	Connection connection = null;	Statement stmt = null;	ResultSet rs=null;	PreparedStatement prepared=null;
	public void closeFrame() {
		this.setVisible(false);
	}
	public ManageProduct(Connection conn, Statement stmt, ResultSet result, PreparedStatement pst){
		connection=conn;
		stmt=stmt;
		rs=result;
		prepared=pst;
		frame=new JFrame("##관리자 모드##");//제목 설정
		frame.setPreferredSize(new Dimension(640, 400));
		frame.setLocation(500, 400);
		Container contentPane = frame.getContentPane(); 
		model = new DefaultTableModel(productArr, 0);
		table=new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		panel=new JPanel();
		comboBox = new JComboBox(productCate);//콤보박스 목록 추가
		name = new JTextField(6);//물품명
		sale = new JTextField(3);//가격
		stock = new JTextField(3);//재고
		panel.add(new JLabel("카테고리"));	panel.add(comboBox);
		panel.add(new JLabel("물품명")); panel.add(name);
		panel.add(new JLabel("가격")); panel.add(sale);
		panel.add(new JLabel("재고")); panel.add(stock);
		//데이터 출력
		ArrayList<Goods>goods=new ArrayList<Goods>();
		try {
			stmt=conn.createStatement();
			//rs=stmt.executeQuery("select*from goodsinfo");//데이터 가져오기 (info)테이블에서
			rs=stmt.executeQuery("select*from info");//데이터 가져오기 (info)테이블에서
			if(rs.next()) {
				do {//데이터베이스에서 데이터 가져오기
					newGoods=new Goods(rs.getInt("code"),
							rs.getString("category"),
							rs.getString("name"),
							rs.getInt("price"),
							rs.getInt("stock"));
					goods.add(newGoods);
					{//테이블에 추가하기
						int code=rs.getInt("code");
						String cate=rs.getString("category");
						String name=rs.getString("name");
						int price=rs.getInt("price");
						int stock=rs.getInt("stock");
						Vector row=new Vector();
						row.add(code);row.add(cate);row.add(name);
						row.add(price);row.add(stock);
						model.addRow(row);
					}
				}while(rs.next());
				System.out.println("데이터 출력 완료");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//물품 추가 버튼
		add_button=new JButton("추가");panel.add(add_button);
		add_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					//String sql="insert into goodsinfo values(?,?,?,?,?)";//insert 테이블과 내용을 형성
					String sql="insert into info values(?,?,?,?,?)";//insert 테이블과 내용을 형성
					prepared=connection.prepareStatement(sql);//preparedStatement 객체 생성
					code1=newGoods.getCode();//goods객체에서 code값 가져오기
					//1~5번 물음표에 값 대입하기
					prepared.setInt(1, code1);
					prepared.setString(2,(String)comboBox.getSelectedItem());
					prepared.setString(3,name.getText());
					prepared.setInt(4,Integer.parseInt(sale.getText()));
					prepared.setInt(5,Integer.parseInt(stock.getText()));
					prepared.executeUpdate();
					System.out.println("물품이 등록되었습니다.");
					{
						Vector row=new Vector();
						row.add(code1);row.add((String)comboBox.getSelectedItem());row.add(name.getText());
						row.add(sale.getText());row.add(stock.getText());
						model.addRow(row);
					}
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
			}
		});
		//물품 삭제 버튼
		del_button=new JButton("삭제");panel.add(del_button);
		del_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					//테이블 삭제
					int row = table.getSelectedRow();
					if(row == -1)return;
					model = (DefaultTableModel) table.getModel();
					model.removeRow(row);
					//데이터베이스 삭제
					JOptionPane.showMessageDialog(null,"데이터 삭제 완료");//알림판
					System.out.println("물품이 삭제되었습니다.");
					int selectCode=Integer.valueOf((String)table.getValueAt(0, 0));
					String sql="delete from info where code=?";//삭제 명령문
					prepared=connection.prepareStatement(sql);
					prepared.setInt(1, selectCode);
					prepared.execute();
				}catch(Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		contentPane.add(panel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 눌렀을 때 윈도우 닫히도록
		frame.pack();frame.setVisible(true);//프레임을 디스플레이
		
	}
	protected String toLatin1(String str) {
		try {
			byte[]b=str.getBytes();
			return new String(b,"ISO-8859-1");
		}catch (java.io.UnsupportedEncodingException uee) {
			System.out.println(uee.getMessage());
		}
		return null;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}