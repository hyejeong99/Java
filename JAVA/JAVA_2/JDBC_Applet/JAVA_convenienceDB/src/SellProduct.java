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
	Statement state = null;	ResultSet result=null;	PreparedStatement prepared=null; Connection connection=null;
	DefaultTableModel model;
	//Info info=new Info();//��ü ����
	Connection conn1 = null;
	ArrayList<Goods>goods1=new ArrayList<Goods>();
	//������ �ݴ� �Լ�
	public void closeFrame() {
		this.setVisible(false);
	}
	public SellProduct(Connection conn, Statement stmt, ResultSet rs, PreparedStatement pst) throws Exception {//������
		state=stmt;result=rs;prepared=pst;connection=conn;
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
			state=connection.createStatement();
			//rs=stmt.executeQuery("select*from goodsinfo");//������ �������� (info)���̺���
			result=state.executeQuery("select*from info");//������ �������� (info)���̺���
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
				try {
					model.setNumRows(0);//���̺� ����ֱ�
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
						if(row==null) label1.setText("ī�װ� ��ǰ ����");
						else label1.setText("������ ��� �Ϸ�");
					}
					System.out.println("������ ��� �Ϸ�");
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
				int total=0;
				try {
					if(table.getRowCount()!=0) {
						stock=Integer.parseInt(buy_stock.getText());
						total = goods1.get(row).getPrice()*stock;
						if(buy_stock.getText()==null) {//���� ���� �Է� ������ ��
							label1.setText("���� ���� �Է����ּ���");
						}else {	
							int selectStock=Integer.valueOf((String)table.getValueAt(row, 4));
							if(stock==selectStock) {//�� ��
								String sql="delete from goodsinfo where code=?";//���� ��ɹ�
								prepared=connection.prepareStatement(sql);
								prepared.setInt(1, Integer.valueOf((String)table.getValueAt(row, 0)));
								prepared.execute();
							}else if(stock>selectStock) {//����� ���� ����� �� ��
								label1.setText("��� �����մϴ�.");
							}else {//�Ǹ�
								JOptionPane.showMessageDialog(null,"���� ����:���� �ݾ��� "+total+"�Դϴ�.");
								String sql="update info set code=? where stock=?";//���� ��ɹ�
								prepared=connection.prepareStatement(sql);
								prepared.setInt(1, Integer.valueOf((String)table.getValueAt(row, 0)));
								prepared.setInt(1, stock);
								prepared.executeUpdate();
							}
						}
					}
					JOptionPane.showMessageDialog(null,"���� ����:���� �ݾ��� "+5000+"�Դϴ�.");
				}catch(IndexOutOfBoundsException ex) {
					String msg = ex.getMessage();
					System.out.println(msg);
				}catch(Exception ex){
					System.out.println("��ǰ ���� �����Դϴ�.");
				}
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
