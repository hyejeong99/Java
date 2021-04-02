import javax.swing.JFrame;

public class Test{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();//프레임 객체 생성
		frame.setSize(800, 300);//프레임 크기를 가로 800, 세로300으로 지정해준다.
		frame.setTitle("그리기");//타이틀 제목을 "그리기"로 설정해준다.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//윈도우창 종료 시 프로세스까지 닫기
		MyComponent component = new MyComponent();//컴포넌트 객체 생성
		frame.add(component);//프레임에 컴포넌트 추가
		frame.setVisible(true);//프레임을 화면에 나타낼 것인지 설정(true=나타냄, false=안나타냄)
	}
}