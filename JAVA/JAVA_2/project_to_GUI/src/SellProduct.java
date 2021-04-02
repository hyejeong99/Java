import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
public class SellProduct extends JFrame implements  ActionListener, MouseListener{
	//private JButton load=null;//저장하기
	private JPanel panel=new JPanel();
	private Container contentPane;private JFrame frame;private JTable table;private JLabel label1;
	private JTextField buy_stock;private JButton buy_button,back_button,exit_button;
	private JComboBox comboBox;
	String productArr[]={"카테고리","물품명","가격","재고","코드"};
	String productCate[]= {"라면","도시락","삼각김밥","과자","탄산음료","주스","우유","물","젤리","초콜릿"};
	private Object[][] data;//콤보박스에 맞춰 데이터 출력
	private Object buyName, buyPrice;	private int buyNum;
	Management manage;	DefaultTableModel model;	ObjectInputStream in=null;
	Object[] newArr=null;
	//프레임 닫는 함수
	public void closeFrame() {
		this.setVisible(false);
	}
	public SellProduct() throws Exception {//생성자
		/*try {
			in=new ObjectInputStream(new FileInputStream("information.data"));
		}catch(Exception e) {
			System.out.println("파일 읽기에 실패했습니다.");
		}*/
		frame=new JFrame("##구매자 모드##");//제목 설정
		frame.setPreferredSize(new Dimension(640, 400));
		frame.setLocation(600, 400);
		contentPane = frame.getContentPane();
		model = new DefaultTableModel(productArr, 0);
		table=new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		panel=new JPanel();
		panel.add(new JLabel("카테고리"));
		//콤보박스
		comboBox=new JComboBox(productCate);
		comboBox.addActionListener(this);panel.add(comboBox);
		comboBox.addActionListener(new ActionListener(){
			Goods[] list=null;
			public void actionPerformed(ActionEvent e) {
				String cate=comboBox.getSelectedItem().toString();
				try {
					list=manage.findGoodsCategory(cate);//카테고리 넣으면 배열 반환해줌
				}
				 catch (Exception e1) {
					 String msg = e1.getMessage();
					 System.out.println(msg);
				}
				try{
					data=new Object[list.length][4];
					for(int i=0; i<list.length; i++) {
						data[i][0] = list[i].getCategory();
						data[i][1] = list[i].getName();
						data[i][2] = list[i].getPrice();
						data[i][3] = list[i].getStock();
					}
				}
				catch(java.lang.NullPointerException e1) {
					label1.setText("         물품 목록이 비었습니다.");label1.setForeground(Color.red);
				}
				DefaultTableModel dfm = new DefaultTableModel(data, productArr);
				table.setModel(dfm);
			}
		});
		//구매 수량 입력하는 필드
		buy_stock = new JTextField(6);//구매 수량
		panel.add(new JLabel("구매 수량")); panel.add(buy_stock);
		//구매 버튼
		buy_button=new JButton("구매");panel.add(buy_button);//구매 버튼 추가
		buy_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//다이얼로그 띄워주기
				int result = JOptionPane.showConfirmDialog(null, "구매하시겠습니까?", "구매", JOptionPane.OK_CANCEL_OPTION);
				if(result == 0) {
					int index = 0;
					try {
						index = manage.findGoodsIndex(String.valueOf(buyName));
					} catch (Exception e1) {
						String msg = e1.getMessage();
						System.out.println(msg);
					}
					int total;
					try {
						total = manage.sell(index, buyNum);
						label1.setText("    구매 정보:지불 금액은 "+total+"입니다.");label1.setForeground(Color.red);
						//System.out.println("지불 금액은 "+total+"입니다.");
					}catch(IndexOutOfBoundsException ex) {
						String msg = ex.getMessage();
						System.out.println(msg);
					}catch(Exception ex){
						System.out.println("물품 구매 오류입니다.");
					}System.out.println("구매완료");
				}
			}
		});
		label1=new JLabel();panel.add(label1);label1.setForeground(Color.red);
		contentPane.add(panel, BorderLayout.SOUTH);//contentpane에 추가하기
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 눌렀을 때 윈도우 닫히도록
		frame.pack();frame.setVisible(true);//프레임을 디스플레이
		//model= new DefaultTableModel(in, productArr);
		//table.setModel(model);
	}public void mouseClicked(MouseEvent e) {
		if(e.getSource()==table) {//테이블 선택
			int row=table.getSelectedRow();//행 선택
			buyName=table.getValueAt(row, 1);
			buyPrice=table.getValueAt(row, 2);
			buyNum=Integer.parseInt(buy_stock.getText());//구매 수량
			label1.setText("    구매 정보:"+buyName+"의 가격은"+buyPrice+"입니다.");
			label1.setForeground(Color.red);
		}
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
}

