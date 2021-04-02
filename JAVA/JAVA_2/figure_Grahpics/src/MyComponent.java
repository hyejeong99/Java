import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
public class MyComponent extends JComponent {
	public void paintComponent(Graphics g)
	{
		Font serif=new Font("Serif",Font.BOLD,12);
		Font bagic=new Font("맑은 고딕 Semilight",Font.PLAIN,12);
		g.setFont(bagic);
		//선
		g.drawLine(10, 80, 100, 10);//좌표(10,80)에서 좌표(100,10)까지 직선을 그린다
		g.drawString("drawLine()", 10, 100);//좌표(10,100)위치에 "drawLine()"을 그린다
		//사각형
		/*
		 * g.drawRect(110, 10, 110, 80);//좌표(110,10)에 가로110, 세로80의 크기를 갖는 사각형을 그린다
		 * g.drawString("drawRect()", 110, 100);//좌표(110,100)위치에 "drawRect()"을 그린다
		 */
		//색 바꾸기
		g.setColor(getForeground().RED);
		g.drawRect(110, 10, 110, 80);//좌표(110,10)에 가로110, 세로80의 크기를 갖는 사각형을 그린다
		g.setColor(getForeground().black);
		g.setFont(serif);
		g.drawString("drawRect()", 110, 100);//좌표(110,100)위치에 "drawRect()"을 그린다
		g.setFont(bagic);
		//입체 사각형
		g.setColor(getForeground().white);//선 색 흰색으로 바꾸기
		g.draw3DRect(230, 10, 110, 80, true);//좌표(230,10)에 가로110, 세로80의 크기를 갖는 "3D" 사각형을 그린다
		g.setColor(getForeground().black);//선 색 검정색으로 바꾸기
		g.drawString("draw3DRect()", 230, 100);//좌표(230,100)위치에 "draw3DRect()"을 그린다
		//둥근 사각형
		g.drawRoundRect(350, 10, 110, 80, 30, 30);//좌표(340,10)에 가로110, 세로80의 크기를 갖는 "라운드" 사각형을 그린다
		g.drawString("drawRoundRect()", 350, 100);//좌표(350,100)위치에  "drawRoundRect()"를 그린다
		//타원
		g.drawOval(470, 10, 110, 80);//좌표(470,10)에 폭110, 높이80의 사각형 안에 내접하는 타원을 그린다
		g.drawString("drawOval()", 480, 100);//좌표(480,100)위치에 "drawOval()"을 그린다
		//부채꼴
		g.drawArc(590, 10, 110, 80, 120, 180);//좌표(590,10)에 부채꼴을 그린다
		g.drawString("drawArc()", 590, 100);//좌표(590,100)위치에 "drawArc()"를 그린다
		//모래시계
		int x[] = { 670, 760, 715, 670, 760}; 
		int y[] = { 10, 10, 50, 90, 90}; 
		g.drawPolygon(x, y, 5);
		g.drawString("drawPolygon()", 670, 100);
		
		//채워진 도형
		//사각형
		/*
		 * g.fillRect(110, 150, 110, 80); g.drawString("fillRect()", 110, 240);
		 */
		//색 바꾸기
		g.setColor(getForeground().RED);
		g.fillRect(110, 150, 110, 80);
		g.setColor(getForeground().black);
		g.drawString("fillRect()", 110, 240);
		//입체 사각형
		g.setColor(getForeground().gray);
		g.fill3DRect(230, 150, 110, 80, true);
		g.setColor(getForeground().black);
		g.drawString("fill3DRect()", 230, 240);
		//둥근 사각형
		g.fillRoundRect(350, 150, 110, 80, 30, 30);
		g.drawString("fillRoundRect()", 350, 240);
		//타원
		g.fillOval(470, 150, 110, 80);
		g.drawString("fillOval()", 480, 240);
		//부채꼴
		g.fillArc(590, 150, 110, 80, 100, 100);
		g.drawString("fillArc()", 590, 240);
		//모래시계
		int y1[] = { 140, 140, 190, 230, 230}; 
		g.fillPolygon(x, y1, 5);
		g.drawString("fillPolygon()", 670, 240);
		
	}
}
