import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class Main extends JFrame{
	private JButton user_button, manager_button,exit_button;//�����, ������, ���� ��ư
	private JPanel panel=new JPanel();
	private Container contentPane;
	private JFrame frame;
	private JLabel label;
	//private JPanel contentPane;
	Management manager=null;
	public Main() throws Exception {
		ObjectInputStream in=null;
		try {//�о����
			manager=new Management(in);//������ �Լ����� ���� ��ü �޾ƿ���
		}
		catch(FileNotFoundException e) {//������ �������� �ʴ� ���
			String msg = e.getMessage();
			System.out.println(msg);
		}
		catch(IOException e) {	//�о�� �� ���� ������ ���
			String msg = e.getMessage();
			System.out.println(msg);;
		}
		catch(Exception e) {
			String msg = e.getMessage();
			System.out.println(msg);
		}
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
		user_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					label.setText("������ ��带 �����߽��ϴ�.");
					contentPane.setBackground(Color.pink);//���� ����
					SellProduct sellproduct=new SellProduct();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		//������ ��ư
		manager_button=new JButton("������");
		panel.add(manager_button);manager_button.setPreferredSize(new Dimension(150,60));//��ư ũ�� ����
		manager_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					label.setText("������ ��带 �����߽��ϴ�.");
					contentPane.setBackground(Color.orange);//���� ����
					ManageProduct manageproduct=new ManageProduct();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		//���� ��ư
		exit_button=new JButton("����");
		panel.add(exit_button);exit_button.setPreferredSize(new Dimension(150,60));//��ư ũ�� ����
		exit_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);//����
			}
		});
		contentPane.add(panel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x��ư ������ ����
		frame.pack();frame.setVisible(true);
	}
}
