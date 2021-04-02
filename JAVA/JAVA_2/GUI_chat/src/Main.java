import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
   private JPanel contentPane;
   private JTextField portText;
   private JTextField ipText;
   private int port;
   private String ip;

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               Main frame = new Main();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   public Main() {
	   //JOptionPane.showMessageDialog(this, "채팅 프로그램을 시작하겠습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
	   setTitle("\uCC44\uD305 \uD504\uB85C\uADF8\uB7A8");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 300);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("\uCC44\uD305 \uD504\uB85C\uADF8\uB7A8");
      lblNewLabel.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 20));
      lblNewLabel.setBounds(148, 12, 139, 27);
      contentPane.add(lblNewLabel);
      
      // ip패널
      JPanel ipPanel = new JPanel();
      ipPanel.setBounds(14, 82, 404, 37);
      contentPane.add(ipPanel);
      ipPanel.setLayout(null);
      ipPanel.setVisible(false);
      
      // 라디오버튼
      ButtonGroup group=new ButtonGroup();
      //서버 라디오 버튼
      JRadioButton rdbtnServer = new JRadioButton("\uC11C\uBC84");
      rdbtnServer.setBounds(100, 47, 57, 27);
      group.add(rdbtnServer);
      rdbtnServer.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(rdbtnServer.isSelected())
               ipPanel.setVisible(false);
         }
      });
      contentPane.add(rdbtnServer);
      //클라이언트 라디오 버튼
      JRadioButton rdbtnClient = new JRadioButton("\uD074\uB77C\uC774\uC5B8\uD2B8");
      rdbtnClient.setBounds(225, 47, 99, 27);
      group.add(rdbtnClient);
      rdbtnClient.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(rdbtnClient.isSelected())
               ipPanel.setVisible(true);
         }
      });
      contentPane.add(rdbtnClient);
      
      //IP 라벨
      JLabel lblNewLabel_1 = new JLabel("IP\uC8FC\uC18C");
      lblNewLabel_1.setBounds(98, 12, 62, 18);
      ipPanel.add(lblNewLabel_1);
      //IP 텍스트
      ipText = new JTextField();
      ipText.setBounds(193, 9, 139, 24);
      ipPanel.add(ipText);
      ipText.setColumns(10);
      
      //PORT 라벨
      JLabel lblNewLabel_2 = new JLabel("\uD3EC\uD2B8");
      lblNewLabel_2.setBounds(118, 131, 37, 18);
      contentPane.add(lblNewLabel_2);
      //PORT 텍스트
      portText = new JTextField();
      portText.setBounds(208, 131, 139, 24);
      contentPane.add(portText);
      portText.setColumns(10);
     
      //연결 버튼
      JButton btnConnect = new JButton("\uC5F0\uACB0\uD558\uAE30");
      btnConnect.setBounds(100, 202, 105, 27);
      btnConnect.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
        	 try {
        		 ip=ipText.getText();port=Integer.parseInt(portText.getText());
        		//서버버튼을 선택했다면
                 if(rdbtnServer.isSelected()) {
                	 ServerChat server=new ServerChat();
                 }
                 // 클라이언트를 선택했다면
                 else{
                	 ClientChat client=new ClientChat();
                 }
        	 }catch(java.lang.NumberFormatException e) {
        		 JOptionPane.showMessageDialog(null,"IP와 PORT 모두 값을 입력해주세요","오류",JOptionPane.ERROR_MESSAGE);
        	 }
         }
         
      });
      contentPane.add(btnConnect);
      //종료 버튼
      JButton btnExit = new JButton("\uC885\uB8CC\uD558\uAE30");
      btnExit.setBounds(225, 202, 105, 27);
      btnExit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.exit(0);
         }
         
      });
      contentPane.add(btnExit);
   }

   public int getPort() {
	   return port;
   }
   public String getIp() {
	   return ip;
   }
}
