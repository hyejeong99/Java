import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;
public class SellProduct extends JFrame implements  ActionListener, MouseListener{
	//private JButton load=null;//저장하기
	private JPanel panel=new JPanel();
	private Container contentPane;private JFrame frame;private JTable table;private JLabel label1;
	private JTextField buy_stock;private JButton buy_button,back_button,exit_button;
	private JComboBox comboBox;
	String productArr[]={"코드","카테고리","물품명","가격","재고"};
	String productCate[]= {"라면","도시락","삼각김밥","과자","탄산음료","주스","우유","물","젤리","초콜릿"};
	//private Object[][] data;//콤보박스에 맞춰 데이터 출력
	private Object buyName, buyPrice; private int stock=0;
	DefaultTableModel model;
	//Info info=new Info();//객체 생성
	Connection conn1 = null;	Statement stmt1 = null;	ResultSet rs1=null;	PreparedStatement pst1=null;
	ArrayList<Goods>goods1=new ArrayList<Goods>();
	//프레임 닫는 함수
	public void closeFrame() {
		this.setVisible(false);
	}
	public SellProduct(Connection conn, Statement stmt, ResultSet rs, PreparedStatement pst) throws Exception {//생성자
		conn1=conn;
		stmt1=stmt;
		rs1=rs;
		pst1=pst;
		frame=new JFrame("##구매자 모드##");//제목 설정
		frame.setPreferredSize(new Dimension(640, 400));
		frame.setLocation(600, 400);
		contentPane = frame.getContentPane();
		model = new DefaultTableModel(productArr, 0);
		table=new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		panel=new JPanel();
		panel.add(new JLabel("카테고리"));
		//기본 데이터 출력
		ArrayList<Goods>goods=new ArrayList<Goods>();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select*from goodsinfo");//데이터 가져오기 (info)테이블에서
			if(rs.next()) {
				do {
					Goods newGoods=new Goods(rs.getString("code"),
							rs.getString("category"),
							rs.getString("name"),
							rs.getInt("price"),
							rs.getInt("stock"));
					goods.add(newGoods);
					{
						String code=rs.getString("code");
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
				label1.setText("데이터 출력 완료");
				System.out.println("데이터 출력 완료");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//콤보박스
		comboBox=new JComboBox(productCate);
		comboBox.addActionListener(this);panel.add(comboBox);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {//콤보박스 검색
				//String search="select*from info where category like'"+comboBox+"';";
				try {
					stmt1=conn.createStatement();
					rs1=stmt1.executeQuery("select*from goodsinfo category like'"+comboBox+"';");//comboBox의 카테고리 출력
					if(rs1.next()) {
						do {
							Goods newGoods1=new Goods(rs1.getString("code"),
									rs1.getString("category"),
									rs1.getString("name"),
									rs1.getInt("price"),
									rs1.getInt("stock"));
							goods1.add(newGoods1);
						}while(rs1.next());
						System.out.println("데이터 출력 완료"); 
						//DefaultTableModel dfm = new DefaultTableModel(newGoods1, productArr);
						//table.setModel(dfm);
					}
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		//구매 수량 입력하는 필드
		buy_stock = new JTextField(6);//구매 수량
		panel.add(new JLabel("구매 수량")); panel.add(buy_stock);
		//구매 버튼
		buy_button=new JButton("구매");panel.add(buy_button);//구매 버튼 추가
		buy_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();//선택된 테이블 번호 받기
				try {//구매 버튼 눌렸을 때->얼만지 알려주고, 총 매출 알려주고->삭제
					String sql="select*from goodsinfo where code=?";
					pst1=conn1.prepareStatement(sql);
					pst1.setInt(1, row);
					rs1=pst1.executeQuery();
				} catch (Exception e1) {
					String msg = e1.getMessage();
					System.out.println(msg);
				}
				int total=0;
				try {
					stock=Integer.parseInt(buy_stock.getText());
					total = goods1.get(row).getPrice()*stock;
					JOptionPane.showMessageDialog(null,"구매 정보:지불 금액은 "+total+"입니다.");
					Goods goods=new Goods();
					if(stock>=goods.getStock()) {
						String sql="delete from goodsinfo where code=?";//삭제 명령문
						pst1=conn1.prepareStatement(sql);
						pst1.setInt(1, row);
						pst1.execute();
					}
				}catch(IndexOutOfBoundsException ex) {
					String msg = ex.getMessage();
					System.out.println(msg);
				}catch(Exception ex){
					System.out.println("물품 구매 오류입니다.");
				}System.out.println("구매완료");
			}
		});
		label1=new JLabel();panel.add(label1);label1.setForeground(Color.red);
		contentPane.add(panel, BorderLayout.SOUTH);//contentpane에 추가하기
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 눌렀을 때 윈도우 닫히도록
		frame.pack();frame.setVisible(true);//프레임을 디스플레이
		//model= new DefaultTableModel(in, productArr);
		//table.setModel(model);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

