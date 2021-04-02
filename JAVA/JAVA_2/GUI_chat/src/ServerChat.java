import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
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
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;

public class ServerChat extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	DataOutputStream dos;DataInputStream dis;
	Socket socket = null;ServerSocket serverSocket = null; 
	int port;
	public ServerChat() {
		Main main=new Main(); 
		port=main.getPort();
		setTitle("\uC11C\uBC84 \uCC44\uD305 \uD504\uB85C\uADF8\uB7A8");
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
		ServerThread serverThread = new ServerThread();
		serverThread.setDaemon(true); //���� ������ ���� ����
		serverThread.start();
		
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
		lblNewLabel_1.setBounds(14, 3, 193, 21);
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
	}
class ServerThread extends Thread {
	public void run() {			
		try {
			serverSocket = new ServerSocket(port);
			textArea.append("�������� �غ� �Ϸ�.\n");
			socket = serverSocket.accept();//Ŭ���̾�Ʈ�� �����Ҷ����� Ŀ��(������)�� ���
			textArea.append(socket.getInetAddress().getHostAddress() + "���� �����ϼ̽��ϴ�.\n");
			//����� ���� ��Ʈ�� ����
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			while(true) {
				//������ ������ �����͸� �б�
				String msg = dis.readUTF();//������ ���������� ���
				textArea.append(" [Clinent] : " + msg + "\n");
				textArea.setCaretPosition(textArea.getText().length());
			}				
		}
		catch (IOException e) {
			textArea.append("Ŭ���̾�Ʈ�� �������ϴ�.\n");
		}

	}

}
void sendMessage() {//�޼��� ����
	String message=textField.getText();
	textField.setText("");//����ֱ�
	textArea.append("[SERVER]: "+message+"\n");
	textArea.setCaretPosition(textArea.getText().length());
	Thread thread = new Thread() {
		@Override
		public void run() {
			try { //UTF = �����ڵ��� �Ծ�(����), �ѱ� ������ �ʰ� ����
				dos.writeUTF(message);
				dos.flush(); //��� ä�� ���� close()�ϸ� �ȵ�				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
	thread.start();	
}
	
}


