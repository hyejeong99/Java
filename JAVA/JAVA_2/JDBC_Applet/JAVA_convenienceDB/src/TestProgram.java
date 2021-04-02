import java.awt.*;
import javax.swing.*;
public class TestProgram extends JApplet{
	/*public static void main(String[] args){
		 try {
			Main main=new Main();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 public void init() {
		Container contentPane = getContentPane();
		contentPane.add(new JLabel("Hello, Java", SwingConstants.CENTER));
	}*/
	public void init() {
		try {
			Main main=new Main();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 
