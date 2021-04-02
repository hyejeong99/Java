import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class Main extends JFrame{
	private JButton user_button, manager_button,exit_button;//사용자, 관리자, 종료 버튼
	private JPanel panel=new JPanel();
	private Container contentPane;
	private JFrame frame;
	private JLabel label;
	//private JPanel contentPane;
	Management manager=null;
	public Main() throws Exception {
		ObjectInputStream in=null;
		try {//읽어오기
			manager=new Management(in);//생성자 함수에서 파일 객체 받아오기
		}
		catch(FileNotFoundException e) {//파일이 존재하지 않는 경우
			String msg = e.getMessage();
			System.out.println(msg);
		}
		catch(IOException e) {	//읽어올 수 없는 파일인 경우
			String msg = e.getMessage();
			System.out.println(msg);;
		}
		catch(Exception e) {
			String msg = e.getMessage();
			System.out.println(msg);
		}
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
		user_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					label.setText("구매자 모드를 선택했습니다.");
					contentPane.setBackground(Color.pink);//색상 변경
					SellProduct sellproduct=new SellProduct();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		//관리자 버튼
		manager_button=new JButton("관리자");
		panel.add(manager_button);manager_button.setPreferredSize(new Dimension(150,60));//버튼 크기 조정
		manager_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				try {
					label.setText("관리자 모드를 선택했습니다.");
					contentPane.setBackground(Color.orange);//색상 변경
					ManageProduct manageproduct=new ManageProduct();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		//종료 버튼
		exit_button=new JButton("종료");
		panel.add(exit_button);exit_button.setPreferredSize(new Dimension(150,60));//버튼 크기 조정
		exit_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);//종료
			}
		});
		contentPane.add(panel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 누르면 종료
		frame.pack();frame.setVisible(true);
	}
}
