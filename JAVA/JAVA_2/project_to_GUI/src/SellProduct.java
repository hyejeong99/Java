import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
public class SellProduct extends JFrame implements  ActionListener, MouseListener{
	//private JButton load=null;//�����ϱ�
	private JPanel panel=new JPanel();
	private Container contentPane;private JFrame frame;private JTable table;private JLabel label1;
	private JTextField buy_stock;private JButton buy_button,back_button,exit_button;
	private JComboBox comboBox;
	String productArr[]={"ī�װ�","��ǰ��","����","���","�ڵ�"};
	String productCate[]= {"���","���ö�","�ﰢ���","����","ź������","�ֽ�","����","��","����","���ݸ�"};
	private Object[][] data;//�޺��ڽ��� ���� ������ ���
	private Object buyName, buyPrice;	private int buyNum;
	Management manage;	DefaultTableModel model;	ObjectInputStream in=null;
	Object[] newArr=null;
	//������ �ݴ� �Լ�
	public void closeFrame() {
		this.setVisible(false);
	}
	public SellProduct() throws Exception {//������
		/*try {
			in=new ObjectInputStream(new FileInputStream("information.data"));
		}catch(Exception e) {
			System.out.println("���� �б⿡ �����߽��ϴ�.");
		}*/
		frame=new JFrame("##������ ���##");//���� ����
		frame.setPreferredSize(new Dimension(640, 400));
		frame.setLocation(600, 400);
		contentPane = frame.getContentPane();
		model = new DefaultTableModel(productArr, 0);
		table=new JTable(model);
		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
		panel=new JPanel();
		panel.add(new JLabel("ī�װ�"));
		//�޺��ڽ�
		comboBox=new JComboBox(productCate);
		comboBox.addActionListener(this);panel.add(comboBox);
		comboBox.addActionListener(new ActionListener(){
			Goods[] list=null;
			public void actionPerformed(ActionEvent e) {
				String cate=comboBox.getSelectedItem().toString();
				try {
					list=manage.findGoodsCategory(cate);//ī�װ� ������ �迭 ��ȯ����
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
					label1.setText("         ��ǰ ����� ������ϴ�.");label1.setForeground(Color.red);
				}
				DefaultTableModel dfm = new DefaultTableModel(data, productArr);
				table.setModel(dfm);
			}
		});
		//���� ���� �Է��ϴ� �ʵ�
		buy_stock = new JTextField(6);//���� ����
		panel.add(new JLabel("���� ����")); panel.add(buy_stock);
		//���� ��ư
		buy_button=new JButton("����");panel.add(buy_button);//���� ��ư �߰�
		buy_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//���̾�α� ����ֱ�
				int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.OK_CANCEL_OPTION);
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
						label1.setText("    ���� ����:���� �ݾ��� "+total+"�Դϴ�.");label1.setForeground(Color.red);
						//System.out.println("���� �ݾ��� "+total+"�Դϴ�.");
					}catch(IndexOutOfBoundsException ex) {
						String msg = ex.getMessage();
						System.out.println(msg);
					}catch(Exception ex){
						System.out.println("��ǰ ���� �����Դϴ�.");
					}System.out.println("���ſϷ�");
				}
			}
		});
		label1=new JLabel();panel.add(label1);label1.setForeground(Color.red);
		contentPane.add(panel, BorderLayout.SOUTH);//contentpane�� �߰��ϱ�
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x��ư ������ �� ������ ��������
		frame.pack();frame.setVisible(true);//�������� ���÷���
		//model= new DefaultTableModel(in, productArr);
		//table.setModel(model);
	}public void mouseClicked(MouseEvent e) {
		if(e.getSource()==table) {//���̺� ����
			int row=table.getSelectedRow();//�� ����
			buyName=table.getValueAt(row, 1);
			buyPrice=table.getValueAt(row, 2);
			buyNum=Integer.parseInt(buy_stock.getText());//���� ����
			label1.setText("    ���� ����:"+buyName+"�� ������"+buyPrice+"�Դϴ�.");
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

