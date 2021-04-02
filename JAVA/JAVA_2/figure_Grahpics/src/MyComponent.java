import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
public class MyComponent extends JComponent {
	public void paintComponent(Graphics g)
	{
		Font serif=new Font("Serif",Font.BOLD,12);
		Font bagic=new Font("���� ��� Semilight",Font.PLAIN,12);
		g.setFont(bagic);
		//��
		g.drawLine(10, 80, 100, 10);//��ǥ(10,80)���� ��ǥ(100,10)���� ������ �׸���
		g.drawString("drawLine()", 10, 100);//��ǥ(10,100)��ġ�� "drawLine()"�� �׸���
		//�簢��
		/*
		 * g.drawRect(110, 10, 110, 80);//��ǥ(110,10)�� ����110, ����80�� ũ�⸦ ���� �簢���� �׸���
		 * g.drawString("drawRect()", 110, 100);//��ǥ(110,100)��ġ�� "drawRect()"�� �׸���
		 */
		//�� �ٲٱ�
		g.setColor(getForeground().RED);
		g.drawRect(110, 10, 110, 80);//��ǥ(110,10)�� ����110, ����80�� ũ�⸦ ���� �簢���� �׸���
		g.setColor(getForeground().black);
		g.setFont(serif);
		g.drawString("drawRect()", 110, 100);//��ǥ(110,100)��ġ�� "drawRect()"�� �׸���
		g.setFont(bagic);
		//��ü �簢��
		g.setColor(getForeground().white);//�� �� ������� �ٲٱ�
		g.draw3DRect(230, 10, 110, 80, true);//��ǥ(230,10)�� ����110, ����80�� ũ�⸦ ���� "3D" �簢���� �׸���
		g.setColor(getForeground().black);//�� �� ���������� �ٲٱ�
		g.drawString("draw3DRect()", 230, 100);//��ǥ(230,100)��ġ�� "draw3DRect()"�� �׸���
		//�ձ� �簢��
		g.drawRoundRect(350, 10, 110, 80, 30, 30);//��ǥ(340,10)�� ����110, ����80�� ũ�⸦ ���� "����" �簢���� �׸���
		g.drawString("drawRoundRect()", 350, 100);//��ǥ(350,100)��ġ��  "drawRoundRect()"�� �׸���
		//Ÿ��
		g.drawOval(470, 10, 110, 80);//��ǥ(470,10)�� ��110, ����80�� �簢�� �ȿ� �����ϴ� Ÿ���� �׸���
		g.drawString("drawOval()", 480, 100);//��ǥ(480,100)��ġ�� "drawOval()"�� �׸���
		//��ä��
		g.drawArc(590, 10, 110, 80, 120, 180);//��ǥ(590,10)�� ��ä���� �׸���
		g.drawString("drawArc()", 590, 100);//��ǥ(590,100)��ġ�� "drawArc()"�� �׸���
		//�𷡽ð�
		int x[] = { 670, 760, 715, 670, 760}; 
		int y[] = { 10, 10, 50, 90, 90}; 
		g.drawPolygon(x, y, 5);
		g.drawString("drawPolygon()", 670, 100);
		
		//ä���� ����
		//�簢��
		/*
		 * g.fillRect(110, 150, 110, 80); g.drawString("fillRect()", 110, 240);
		 */
		//�� �ٲٱ�
		g.setColor(getForeground().RED);
		g.fillRect(110, 150, 110, 80);
		g.setColor(getForeground().black);
		g.drawString("fillRect()", 110, 240);
		//��ü �簢��
		g.setColor(getForeground().gray);
		g.fill3DRect(230, 150, 110, 80, true);
		g.setColor(getForeground().black);
		g.drawString("fill3DRect()", 230, 240);
		//�ձ� �簢��
		g.fillRoundRect(350, 150, 110, 80, 30, 30);
		g.drawString("fillRoundRect()", 350, 240);
		//Ÿ��
		g.fillOval(470, 150, 110, 80);
		g.drawString("fillOval()", 480, 240);
		//��ä��
		g.fillArc(590, 150, 110, 80, 100, 100);
		g.drawString("fillArc()", 590, 240);
		//�𷡽ð�
		int y1[] = { 140, 140, 190, 230, 230}; 
		g.fillPolygon(x, y1, 5);
		g.drawString("fillPolygon()", 670, 240);
		
	}
}
