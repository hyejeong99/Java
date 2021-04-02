import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.ArrayList;
public class Main extends JFrame{
	private JButton user_button, manager_button,exit_button;//�����, ������, ���� ��ư
	private JPanel panel=new JPanel();
	private Container contentPane;	private JFrame frame;	private JLabel label;
	Connection conn = null;	Statement stmt = null;	ResultSet rs=null;	PreparedStatement pst=null;
	public Main() throws Exception {
		try {//���� �õ�
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/goodsinfo?serverTimezone=UTC", "root", "");
			System.out.println("����Ǿ����ϴ�.");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("�ش� Ŭ������ ã�� �� �����ϴ�." + cnfe.getMessage());
		}
		catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		Goods goods=new Goods();//goods��ü ����
		frame=new JFrame("���� ������ ���α׷�");//���� ����
		frame.setPreferredSize(new Dimension(500,300));//������ ũ�� ����
		frame.setLocation(500,400);//������ ��ġ ����
		contentPane=frame.getContentPane();//�����ӿ� ���ӵ� content pane��������
		//�� ����
		label=new JLabel("���ϴ� �޴� ��ư�� �������ּ���",SwingConstants.CENTER);
		contentPane.add(label,BorderLayout.CENTER);//����Ʈ�ҿ� �� �߰�
		//������ ��ư
		user_button=new JButton("������");
		panel.add(user_button);user_button.setPreferredSize(new Dimension(150,60));//��ư ũ�� ����
		user_button.addActionListener(new ActionListener(){//������ ��ư ������ ��
			public void actionPerformed(ActionEvent e) {
				try {
					label.setText("������ ��带 �����߽��ϴ�.");
					contentPane.setBackground(Color.pink);//���� ����
					SellProduct sellproduct=new SellProduct(conn,stmt,rs,pst);
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		//������ ��ư
		manager_button=new JButton("������");
		panel.add(manager_button);manager_button.setPreferredSize(new Dimension(150,60));//��ư ũ�� ����
		manager_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {//������ ��ư ������ ��
				try {
					label.setText("������ ��带 �����߽��ϴ�.");
					contentPane.setBackground(Color.orange);//���� ����
					ManageProduct manageproduct=new ManageProduct(conn,stmt,rs,pst);
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		//���� ��ư
		exit_button=new JButton("����");
		panel.add(exit_button);exit_button.setPreferredSize(new Dimension(150,60));//��ư ũ�� ����
		exit_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {//���� ��ư ������ ��
				try {
					JOptionPane.showMessageDialog(null,"���α׷��� �����ϰڽ��ϴ�.");
					if(conn!=null) {
						try {
							conn.close();
						}catch(SQLException e1) {}
					}
					System.exit(0);//����
					}
				catch (Exception ignored) {
				}
			}
		});
		contentPane.add(panel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x��ư ������ ����
		frame.pack();frame.setVisible(true);
	}
}
