import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.LinkedList;
public class ManageProduct extends JFrame implements ActionListener{//������ ���
	private JPanel panel=new JPanel();
	private Container contentPane;
	private JFrame frame;private JLabel label,label1;private JTable table;private JComboBox comboBox;
	private JButton add_button,del_button;//�߰�,���� ��ư
	private JTextField cate,name,sale,stock;
	String productArr[]={"�ڵ�","ī�װ�","��ǰ��","����","���"};//��ǰ ����
	String productCate[]= {"���","���ö�","�ﰢ���","����","ź������","�ֽ�","����","��","����","���ݸ�"};//�޺��ڽ� ī�װ�
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
		frame=new JFrame("##������ ���##");//���� ����
		frame.setPreferredSize(new Dimension(640, 400));
		frame.setLocation(500, 400);
		Container contentPane = frame.getContentPane(); 
		model = new DefaultTableModel(productArr, 0);
		table=new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		panel=new JPanel();
		comboBox = new JComboBox(productCate);//�޺��ڽ� ��� �߰�
		name = new JTextField(6);//��ǰ��
		sale = new JTextField(3);//����
		stock = new JTextField(3);//���
		panel.add(new JLabel("ī�װ�"));	panel.add(comboBox);
		panel.add(new JLabel("��ǰ��")); panel.add(name);
		panel.add(new JLabel("����")); panel.add(sale);
		panel.add(new JLabel("���")); panel.add(stock);
		//������ ���
		ArrayList<Goods>goods=new ArrayList<Goods>();
		try {
			stmt=conn.createStatement();
			//rs=stmt.executeQuery("select*from goodsinfo");//������ �������� (info)���̺���
			rs=stmt.executeQuery("select*from info");//������ �������� (info)���̺���
			if(rs.next()) {
				do {//�����ͺ��̽����� ������ ��������
					newGoods=new Goods(rs.getInt("code"),
							rs.getString("category"),
							rs.getString("name"),
							rs.getInt("price"),
							rs.getInt("stock"));
					goods.add(newGoods);
					{//���̺� �߰��ϱ�
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
				System.out.println("������ ��� �Ϸ�");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//��ǰ �߰� ��ư
		add_button=new JButton("�߰�");panel.add(add_button);
		add_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					//String sql="insert into goodsinfo values(?,?,?,?,?)";//insert ���̺�� ������ ����
					String sql="insert into info values(?,?,?,?,?)";//insert ���̺�� ������ ����
					prepared=connection.prepareStatement(sql);//preparedStatement ��ü ����
					code1=newGoods.getCode();//goods��ü���� code�� ��������
					//1~5�� ����ǥ�� �� �����ϱ�
					prepared.setInt(1, code1);
					prepared.setString(2,(String)comboBox.getSelectedItem());
					prepared.setString(3,name.getText());
					prepared.setInt(4,Integer.parseInt(sale.getText()));
					prepared.setInt(5,Integer.parseInt(stock.getText()));
					prepared.executeUpdate();
					System.out.println("��ǰ�� ��ϵǾ����ϴ�.");
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
		//��ǰ ���� ��ư
		del_button=new JButton("����");panel.add(del_button);
		del_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					//���̺� ����
					int row = table.getSelectedRow();
					if(row == -1)return;
					model = (DefaultTableModel) table.getModel();
					model.removeRow(row);
					//�����ͺ��̽� ����
					JOptionPane.showMessageDialog(null,"������ ���� �Ϸ�");//�˸���
					System.out.println("��ǰ�� �����Ǿ����ϴ�.");
					int selectCode=Integer.valueOf((String)table.getValueAt(0, 0));
					String sql="delete from info where code=?";//���� ��ɹ�
					prepared=connection.prepareStatement(sql);
					prepared.setInt(1, selectCode);
					prepared.execute();
				}catch(Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		});
		contentPane.add(panel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x��ư ������ �� ������ ��������
		frame.pack();frame.setVisible(true);//�������� ���÷���
		
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