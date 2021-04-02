import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
public class ManageProduct extends JFrame implements ActionListener{
	//private JButton load=null;//저장하기
	private JPanel panel=new JPanel();
	private Container contentPane;
	private JFrame frame;private JLabel label,label1;private JTable table;private JComboBox comboBox;
	private JButton add_button,del_button,save_button;
	private JTextField cate,name,sale,stock;
	String productArr[]={"카테고리","물품명","가격","재고","코드"};
	String productCate[]= {"라면","도시락","삼각김밥","과자","탄산음료","주스","우유","물","젤리","초콜릿"};
	private Object[]newArr=null; int code=0;
	//private LinkedList<Goods>goods=new LinkedList<Goods>();//배열을 LinkedList로
	//private int manageSize=goods.size();
	//private int totalSales=0;
	Management manager;
	DefaultTableModel model;
	ObjectInputStream in=null;ObjectOutputStream out=null;
	public void closeFrame() {
		this.setVisible(false);
	}
	public ManageProduct(){
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
		//물품 추가 버튼
		add_button=new JButton("추가");panel.add(add_button);
		add_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					//배열에 문자열 추가
					newArr=new Object[5];//카테고리,물품명
					newArr[0]=comboBox.getSelectedItem().toString();
					newArr[1]=name.getText();
					newArr[2]=sale.getText();
					newArr[3]=stock.getText();
					newArr[4]=++code;
					model.addRow(newArr);
					try {
						String cate=comboBox.getSelectedItem().toString();
						String name1=name.getText();
						int price=Integer.parseInt(sale.getText());//int형변환
						int stock1=Integer.parseInt(stock.getText());
						Goods newGoods=new Goods(cate,name1,price,stock1,code);
						manager.insertGoods(newGoods);
						System.out.println("물품이 추가되었습니다.");
					} catch(java.lang.NumberFormatException e1) {
						String msg = e1.getMessage();
						System.out.println(msg);
					}
					catch(IndexOutOfBoundsException e2) {//예외처리
						String msg = e2.getMessage();
						System.out.println(msg);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("오류가 발생했습니다.");
					}
				}catch(java.lang.NumberFormatException e1) {
					System.out.println("오류입니다.");
				}
				
			}
		});
		//물품 삭제 버튼
		del_button=new JButton("삭제");panel.add(del_button);
		del_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row == -1) return;
				model = (DefaultTableModel) table.getModel();
				model.removeRow(row);
				try {
					manager.deleteGoods(row);
				} catch (Exception e1) {
					String msg = e1.getMessage();
					System.out.println(msg);
				}
				System.out.println("삭제되었습니다.");
			}
		});
		//저장 버튼
		save_button=new JButton("저장");panel.add(save_button);
		save_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//저장 버튼
				try {
					//out에 fOut저장
					out = new ObjectOutputStream(new FileOutputStream("information.data"));
					//out에 객체의 프로그램 실행 내용을 저장
					manager.saveFile(out);
					System.out.println("데이터가 저장되었습니다.");
				}
				catch(IOException e1) {	//파일로 출력이 제대로 이루어지지 않는 경우
					System.out.println("파일로 출력할 수 없습니다.");
				}
				catch(Exception e1) {	//출력을 하는 과정에서 에러가 발생한 경우
					System.out.println("저장에 실패했습니다.");
				}
				finally {
					try {
						out.close();
						}
					catch (Exception e1){
					}
				}
			}
		});
		contentPane.add(panel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 눌렀을 때 윈도우 닫히도록
		frame.pack();frame.setVisible(true);//프레임을 디스플레이
		//기존 정보 테이블에 저장해주기
		/////////////////////////////////////여기 해야됨
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}