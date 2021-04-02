import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
public class ManageProduct extends JFrame implements ActionListener{
	//private JButton load=null;//�����ϱ�
	private JPanel panel=new JPanel();
	private Container contentPane;
	private JFrame frame;private JLabel label,label1;private JTable table;private JComboBox comboBox;
	private JButton add_button,del_button,save_button;
	private JTextField cate,name,sale,stock;
	String productArr[]={"ī�װ�","��ǰ��","����","���","�ڵ�"};
	String productCate[]= {"���","���ö�","�ﰢ���","����","ź������","�ֽ�","����","��","����","���ݸ�"};
	private Object[]newArr=null; int code=0;
	//private LinkedList<Goods>goods=new LinkedList<Goods>();//�迭�� LinkedList��
	//private int manageSize=goods.size();
	//private int totalSales=0;
	Management manager;
	DefaultTableModel model;
	ObjectInputStream in=null;ObjectOutputStream out=null;
	public void closeFrame() {
		this.setVisible(false);
	}
	public ManageProduct(){
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
		//��ǰ �߰� ��ư
		add_button=new JButton("�߰�");panel.add(add_button);
		add_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try{
					//�迭�� ���ڿ� �߰�
					newArr=new Object[5];//ī�װ�,��ǰ��
					newArr[0]=comboBox.getSelectedItem().toString();
					newArr[1]=name.getText();
					newArr[2]=sale.getText();
					newArr[3]=stock.getText();
					newArr[4]=++code;
					model.addRow(newArr);
					try {
						String cate=comboBox.getSelectedItem().toString();
						String name1=name.getText();
						int price=Integer.parseInt(sale.getText());//int����ȯ
						int stock1=Integer.parseInt(stock.getText());
						Goods newGoods=new Goods(cate,name1,price,stock1,code);
						manager.insertGoods(newGoods);
						System.out.println("��ǰ�� �߰��Ǿ����ϴ�.");
					} catch(java.lang.NumberFormatException e1) {
						String msg = e1.getMessage();
						System.out.println(msg);
					}
					catch(IndexOutOfBoundsException e2) {//����ó��
						String msg = e2.getMessage();
						System.out.println(msg);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						System.out.println("������ �߻��߽��ϴ�.");
					}
				}catch(java.lang.NumberFormatException e1) {
					System.out.println("�����Դϴ�.");
				}
				
			}
		});
		//��ǰ ���� ��ư
		del_button=new JButton("����");panel.add(del_button);
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
				System.out.println("�����Ǿ����ϴ�.");
			}
		});
		//���� ��ư
		save_button=new JButton("����");panel.add(save_button);
		save_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//���� ��ư
				try {
					//out�� fOut����
					out = new ObjectOutputStream(new FileOutputStream("information.data"));
					//out�� ��ü�� ���α׷� ���� ������ ����
					manager.saveFile(out);
					System.out.println("�����Ͱ� ����Ǿ����ϴ�.");
				}
				catch(IOException e1) {	//���Ϸ� ����� ����� �̷������ �ʴ� ���
					System.out.println("���Ϸ� ����� �� �����ϴ�.");
				}
				catch(Exception e1) {	//����� �ϴ� �������� ������ �߻��� ���
					System.out.println("���忡 �����߽��ϴ�.");
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x��ư ������ �� ������ ��������
		frame.pack();frame.setVisible(true);//�������� ���÷���
		//���� ���� ���̺� �������ֱ�
		/////////////////////////////////////���� �ؾߵ�
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}