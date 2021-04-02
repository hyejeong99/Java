import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class ClientChat extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	DataInputStream dis;DataOutputStream dos;
	Socket socket = null;
	private int port; private String ip;
	public ClientChat() {
		Main main=new Main();
		port=main.getPort();ip=main.getIp();
		setTitle("\uD074\uB77C\uC774\uC5B8\uD2B8 \uCC44\uD305 \uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(14, 227, 327, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		//��ȭ ���� ���� ��ư
		JButton btnNewButton = new JButton("\uC804\uC1A1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		//����Ű ������ �� ���� �ǵ���
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			//���� �Է¹޾Ҵ��� �˾Ƴ���
			int key=e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_ENTER:
				sendMessage();
				break;
				}
			}
		});setVisible(true);textField.requestFocus();
		//������ �����ϴ� ��Ʈ��ũ �۾� : ������ ��ü ���� �� ����
		ClientThread clientThread = new ClientThread();
		clientThread.setDaemon(true);
		clientThread.start();

		btnNewButton.setFont(new Font("���� ��� Semilight", Font.PLAIN, 17));
		btnNewButton.setBounds(345, 226, 73, 27);
		contentPane.add(btnNewButton);
		//���� ��ư
		JButton btnNewButton_1 = new JButton("\uB098\uAC00\uAE30");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("���� ��� Semilight", Font.PLAIN, 17));
		btnNewButton_1.setBounds(325, 0, 93, 27);
		contentPane.add(btnNewButton_1);
		
		//PORT ��
		JLabel lblNewLabel_1 = new JLabel("PORT:"+port);
		lblNewLabel_1.setBounds(194, 3, 127, 21);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		
		//��ȭ ���� �κ�
		textArea = new JTextArea();
		textArea.setBackground(SystemColor.activeCaption);
		textArea.setBounds(14, 30, 404, 185);
		contentPane.add(textArea);
		textArea.setEditable(false); //���� ����
		//��ũ�ѹ�
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(14, 30, 404, 194);
		contentPane.add(scrollPane);
		
		//Text Area
		contentPane.add(scrollPane, BorderLayout.CENTER);
		//���� ��ũ�ѹ� ����
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblNewLabel = new JLabel("IP:"+ip);
		lblNewLabel.setFont(new Font("���� ��� Semilight", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(14, 6, 62, 18);
		contentPane.add(lblNewLabel);

	}
	class ClientThread extends Thread {
		public void run() {			
			try {
				socket=new Socket(ip,port);
				textArea.append("������ ���ӵƽ��ϴ�.\n");
				//���� ��Ʈ��
				dis=new DataInputStream(socket.getInputStream());
				dos=new DataOutputStream(socket.getOutputStream());
				while(true) {
					//���� �޽��� �ޱ�
					String msg = dis.readUTF();
					textArea.append(" [SERVER] : " + msg + "\n");
					textArea.setCaretPosition(textArea.getText().length());
				}
			}
			catch (UnknownHostException e) {
				textArea.append("���� �ּҰ� �̻��մϴ�.\n");
			} catch (IOException e) {
				textArea.append("������ ������ ������ϴ�.\n");
			}
		}
	}
	void sendMessage() {//�޼��� ����
		String message=textField.getText();
		textField.setText("");//����ֱ�
		textArea.append("[CLIENT]: "+message+"\n");
		textArea.setCaretPosition(textArea.getText().length());
		Thread t = new Thread() {
			@Override
			public void run() {
				try { //UTF = �����ڵ��� �Ծ�(����), �ѱ� ������ �ʰ� ����
					dos.writeUTF(message);
					dos.flush(); //��� ä�� ���� close()�ϸ� �ȵ�				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();	
	}
}


