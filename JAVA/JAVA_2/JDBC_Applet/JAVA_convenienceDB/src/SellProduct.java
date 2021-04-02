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
	Statement state = null;	ResultSet result=null;	PreparedStatement prepared=null; Connection connection=null;
	DefaultTableModel model;
	//Info info=new Info();//객체 생성
	Connection conn1 = null;
	ArrayList<Goods>goods1=new ArrayList<Goods>();
	//프레임 닫는 함수
	public void closeFrame() {
		this.setVisible(false);
	}
	public SellProduct(Connection conn, Statement stmt, ResultSet rs, PreparedStatement pst) throws Exception {//생성자
		state=stmt;result=rs;prepared=pst;connection=conn;
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
			state=connection.createStatement();
			//rs=stmt.executeQuery("select*from goodsinfo");//데이터 가져오기 (info)테이블에서
			result=state.executeQuery("select*from info");//데이터 가져오기 (info)테이블에서
			if(result.next()) {
				do {
					Goods newGoods=new Goods(result.getInt("code"),
							result.getString("category"),
							result.getString("name"),
							result.getInt("price"),
							result.getInt("stock"));
					goods.add(newGoods);
					{
						int code=result.getInt("code");
						String cate=result.getString("category");
						String name=result.getString("name");
						int price=result.getInt("price");
						int stock=result.getInt("stock");
						Vector row=new Vector();
						row.add(code);row.add(cate);row.add(name);
						row.add(price);row.add(stock);
						model.addRow(row);
					}
				}while(result.next());
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
				try {
					model.setNumRows(0);//테이블 비워주기
					state=connection.createStatement();
					String sql="SELECT*FROM info WHERE category=?";
					prepared=connection.prepareStatement(sql);
					String combo=String.valueOf(comboBox.getSelectedItem());
					prepared.setString(1, combo);
					result=prepared.executeQuery();
					while(result.next()) {
						int code=result.getInt("code");
						String cate=result.getString("category");
						String name=result.getString("name");
						int price=result.getInt("price");
						int stock=result.getInt("stock");
						Vector row=new Vector();
						row.add(code);row.add(cate);row.add(name);
						row.add(price);row.add(stock);
						model.addRow(row);
						if(row==null) label1.setText("카테고리 물품 없음");
						else label1.setText("데이터 출력 완료");
					}
					System.out.println("데이터 출력 완료");
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
				int total=0;
				try {
					if(table.getRowCount()!=0) {
						stock=Integer.parseInt(buy_stock.getText());
						total = goods1.get(row).getPrice()*stock;
						if(buy_stock.getText()==null) {//구매 수량 입력 안했을 때
							label1.setText("구매 수량 입력해주세요");
						}else {	
							int selectStock=Integer.valueOf((String)table.getValueAt(row, 4));
							if(stock==selectStock) {//다 팜
								String sql="delete from goodsinfo where code=?";//삭제 명령문
								prepared=connection.prepareStatement(sql);
								prepared.setInt(1, Integer.valueOf((String)table.getValueAt(row, 0)));
								prepared.execute();
							}else if(stock>selectStock) {//재고보다 많이 사고자 할 때
								label1.setText("재고가 부족합니다.");
							}else {//판매
								JOptionPane.showMessageDialog(null,"구매 정보:지불 금액은 "+total+"입니다.");
								String sql="update info set code=? where stock=?";//삭제 명령문
								prepared=connection.prepareStatement(sql);
								prepared.setInt(1, Integer.valueOf((String)table.getValueAt(row, 0)));
								prepared.setInt(1, stock);
								prepared.executeUpdate();
							}
						}
					}
					JOptionPane.showMessageDialog(null,"구매 정보:지불 금액은 "+5000+"입니다.");
				}catch(IndexOutOfBoundsException ex) {
					String msg = ex.getMessage();
					System.out.println(msg);
				}catch(Exception ex){
					System.out.println("물품 구매 오류입니다.");
				}
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
