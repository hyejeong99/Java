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
		//대화 내용 전송 버튼
		JButton btnNewButton = new JButton("\uC804\uC1A1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}

		});
		//엔터키 눌렀을 때 전송 되도록
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			//엔터 입력받았는지 알아내기
			int key=e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_ENTER:
				sendMessage();
				break;
				}
			}
		});setVisible(true);textField.requestFocus();
		ServerThread serverThread = new ServerThread();
		serverThread.setDaemon(true); //메인 끝나면 같이 종료
		serverThread.start();
		
		btnNewButton.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 17));
		btnNewButton.setBounds(345, 226, 73, 27);
		contentPane.add(btnNewButton);
		//종료 버튼
		JButton btnNewButton_1 = new JButton("\uB098\uAC00\uAE30");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 17));
		btnNewButton_1.setBounds(325, 0, 93, 27);
		contentPane.add(btnNewButton_1);
		
		//PORT 라벨
		JLabel lblNewLabel_1 = new JLabel("PORT:"+port);
		lblNewLabel_1.setBounds(14, 3, 193, 21);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		
		//대화 넣을 부분
		textArea = new JTextArea();
		textArea.setBackground(SystemColor.activeCaption);
		textArea.setBounds(14, 30, 404, 185);
		contentPane.add(textArea);
		textArea.setEditable(false); //쓰기 금지
		//스크롤바
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(14, 30, 404, 194);
		contentPane.add(scrollPane);
		
		//Text Area
		contentPane.add(scrollPane, BorderLayout.CENTER);
		//수평 스크롤바 제거
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}
class ServerThread extends Thread {
	public void run() {			
		try {
			serverSocket = new ServerSocket(port);
			textArea.append("서버소켓 준비 완료.\n");
			socket = serverSocket.accept();//클라이언트가 접속할때까지 커서(스레드)가 대기
			textArea.append(socket.getInetAddress().getHostAddress() + "님이 접속하셨습니다.\n");
			//통신을 위한 스트림 생성
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			while(true) {
				//상대방이 보내온 데이터를 읽기
				String msg = dis.readUTF();//상대방이 보낼때까지 대기
				textArea.append(" [Clinent] : " + msg + "\n");
				textArea.setCaretPosition(textArea.getText().length());
			}				
		}
		catch (IOException e) {
			textArea.append("클라이언트가 나갔습니다.\n");
		}

	}

}
void sendMessage() {//메세지 전송
	String message=textField.getText();
	textField.setText("");//비워주기
	textArea.append("[SERVER]: "+message+"\n");
	textArea.setCaretPosition(textArea.getText().length());
	Thread thread = new Thread() {
		@Override
		public void run() {
			try { //UTF = 유니코드의 규약(포맷), 한글 깨지지 않게 해줌
				dos.writeUTF(message);
				dos.flush(); //계속 채팅 위해 close()하면 안됨				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
	thread.start();	
}
	
}


