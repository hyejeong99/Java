import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.ArrayList;
public class Main extends JFrame{
	private JButton user_button, manager_button,exit_button;//사용자, 관리자, 종료 버튼
	private JPanel panel=new JPanel();
	private Container contentPane;	private JFrame frame;	private JLabel label;
	Connection conn = null;	Statement stmt = null;	ResultSet rs=null;	PreparedStatement pst=null;
	public Main() throws Exception {
		try {//접속 시도
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/goodsinfo?serverTimezone=UTC", "root", "");
			System.out.println("연결되었습니다.");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
		}
		catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		Goods goods=new Goods();//goods객체 생성
		frame=new JFrame("혜정 편의점 프로그램");//제목 설정
		frame.setPreferredSize(new Dimension(500,300));//윈도우 크기 설정
		frame.setLocation(500,400);//윈도우 위치 설정
		contentPane=frame.getContentPane();//프레임에 종속된 content pane가져오기
		//라벨 생성
		label=new JLabel("원하는 메뉴 버튼을 선택해주세요",SwingConstants.CENTER);
		contentPane.add(label,BorderLayout.CENTER);//콘텐트팬에 라벨 추가
		//구매자 버튼
		user_button=new JButton("구매자");
		panel.add(user_button);user_button.setPreferredSize(new Dimension(150,60));//버튼 크기 조정
		user_button.addActionListener(new ActionListener(){//구매자 버튼 눌렀을 떄
			public void actionPerformed(ActionEvent e) {
				try {
					label.setText("구매자 모드를 선택했습니다.");
					contentPane.setBackground(Color.pink);//색상 변경
					SellProduct sellproduct=new SellProduct(conn,stmt,rs,pst);
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		//관리자 버튼
		manager_button=new JButton("관리자");
		panel.add(manager_button);manager_button.setPreferredSize(new Dimension(150,60));//버튼 크기 조정
		manager_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {//관리자 버튼 눌렀을 때
				try {
					label.setText("관리자 모드를 선택했습니다.");
					contentPane.setBackground(Color.orange);//색상 변경
					ManageProduct manageproduct=new ManageProduct(conn,stmt,rs,pst);
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		//종료 버튼
		exit_button=new JButton("종료");
		panel.add(exit_button);exit_button.setPreferredSize(new Dimension(150,60));//버튼 크기 조정
		exit_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {//종료 버튼 눌렀을 때
				try {
					JOptionPane.showMessageDialog(null,"프로그램을 종료하겠습니다.");
					if(conn!=null) {
						try {
							conn.close();
						}catch(SQLException e1) {}
					}
					System.exit(0);//종료
					}
				catch (Exception ignored) {
				}
			}
		});
		contentPane.add(panel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 누르면 종료
		frame.pack();frame.setVisible(true);
	}
}
