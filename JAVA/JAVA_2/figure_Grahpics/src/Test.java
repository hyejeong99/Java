import javax.swing.JFrame;

public class Test{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();//������ ��ü ����
		frame.setSize(800, 300);//������ ũ�⸦ ���� 800, ����300���� �������ش�.
		frame.setTitle("�׸���");//Ÿ��Ʋ ������ "�׸���"�� �������ش�.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//������â ���� �� ���μ������� �ݱ�
		MyComponent component = new MyComponent();//������Ʈ ��ü ����
		frame.add(component);//�����ӿ� ������Ʈ �߰�
		frame.setVisible(true);//�������� ȭ�鿡 ��Ÿ�� ������ ����(true=��Ÿ��, false=�ȳ�Ÿ��)
	}
}