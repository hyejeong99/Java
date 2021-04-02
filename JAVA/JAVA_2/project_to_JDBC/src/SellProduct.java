import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.sql.*;
public class SellProduct extends JFrame implements  ActionListener, MouseListener{
	//private JButton load=null;//�����ϱ�
	private JPanel panel=new JPanel();
	private Container contentPane;private JFrame frame;private JTable table;private JLabel label1;
	private JTextField buy_stock;private JButton buy_button,back_button,exit_button;
	private JComboBox comboBox;
	String productArr[]={"�ڵ�","ī�װ�","��ǰ��","����","���"};
	String productCate[]= {"���","���ö�","�ﰢ���","����","ź������","�ֽ�","����","��","����","���ݸ�"};
	//private Object[][] data;//�޺��ڽ��� ���� ������ ���
	private Object buyName, buyPrice; private int stock=0;
	DefaultTableModel model;
	//Info info=new Info();//��ü ����
	Connection conn1 = null;	Statement stmt1 = null;	ResultSet rs1=null;	PreparedStatement pst1=null;
	ArrayList<Goods>goods1=new ArrayList<Goods>();
	//������ �ݴ� �Լ�
	public void closeFrame() {
		this.setVisible(false);
	}
	public SellProduct(Connection conn, Statement stmt, ResultSet rs, PreparedStatement pst) throws Exception {//������
		conn1=conn;
		stmt1=stmt;
		rs1=rs;
		pst1=pst;
		frame=new JFrame("##������ ���##");//���� ����
		frame.setPreferredSize(new Dimension(640, 400));
		frame.setLocation(600, 400);
		contentPane = frame.getContentPane();
		model = new DefaultTableModel(productArr, 0);
		table=new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		panel=new JPanel();
		panel.add(new JLabel("ī�װ�"));
		//�⺻ ������ ���
		ArrayList<Goods>goods=new ArrayList<Goods>();
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("select*from goodsinfo");//������ �������� (info)���̺���
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
				label1.setText("������ ��� �Ϸ�");
				System.out.println("������ ��� �Ϸ�");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//�޺��ڽ�
		comboBox=new JComboBox(productCate);
		comboBox.addActionListener(this);panel.add(comboBox);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {//�޺��ڽ� �˻�
				//String search="select*from info where category like'"+comboBox+"';";
				try {
					stmt1=conn.createStatement();
					rs1=stmt1.executeQuery("select*from goodsinfo category like'"+comboBox+"';");//comboBox�� ī�װ� ���
					if(rs1.next()) {
						do {
							Goods newGoods1=new Goods(rs1.getString("code"),
									rs1.getString("category"),
									rs1.getString("name"),
									rs1.getInt("price"),
									rs1.getInt("stock"));
							goods1.add(newGoods1);
						}while(rs1.next());
						System.out.println("������ ��� �Ϸ�"); 
						//DefaultTableModel dfm = new DefaultTableModel(newGoods1, productArr);
						//table.setModel(dfm);
					}
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		//���� ���� �Է��ϴ� �ʵ�
		buy_stock = new JTextField(6);//���� ����
		panel.add(new JLabel("���� ����")); panel.add(buy_stock);
		//���� ��ư
		buy_button=new JButton("����");panel.add(buy_button);//���� ��ư �߰�
		buy_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int row=table.getSelectedRow();//���õ� ���̺� ��ȣ �ޱ�
				try {//���� ��ư ������ ��->���� �˷��ְ�, �� ���� �˷��ְ�->����
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
					JOptionPane.showMessageDialog(null,"���� ����:���� �ݾ��� "+total+"�Դϴ�.");
					Goods goods=new Goods();
					if(stock>=goods.getStock()) {
						String sql="delete from goodsinfo where code=?";//���� ��ɹ�
						pst1=conn1.prepareStatement(sql);
						pst1.setInt(1, row);
						pst1.execute();
					}
				}catch(IndexOutOfBoundsException ex) {
					String msg = ex.getMessage();
					System.out.println(msg);
				}catch(Exception ex){
					System.out.println("��ǰ ���� �����Դϴ�.");
				}System.out.println("���ſϷ�");
			}
		});
		label1=new JLabel();panel.add(label1);label1.setForeground(Color.red);
		contentPane.add(panel, BorderLayout.SOUTH);//contentpane�� �߰��ϱ�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x��ư ������ �� ������ ��������
		frame.pack();frame.setVisible(true);//�������� ���÷���
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

