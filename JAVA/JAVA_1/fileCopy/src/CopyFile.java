import java.io.*;
import java.util.Scanner;
public class CopyFile {
	public static void main(String args[]) {
		FileWriter writer=null;//����ֱ�
		FileReader reader=null;//����ֱ�
		Scanner scan=new Scanner(System.in);
		System.out.println("���� ������ ������  'hello.txt'�� �ֽ��ϴ�.");//���� ������ ���� �˷��ֱ�
		System.out.printf("������ ���� �̸�:");
		String copyFileName=scan.next();//� ���� ������������� �Է¹ޱ�		
		System.out.printf("����� ���� �̸�:");
		String fileName=scan.next();//���� �̸�
		try {
			reader=new FileReader(copyFileName+".txt");
			writer=new FileWriter(fileName+".txt");
			while(true) {
					int data=reader.read();
						if(data==-1) break;
						char ch=(char) data;
						writer.write(ch);
			}System.out.println("���� �Ϸ�!");
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		}
		catch (IOException ioe) {
			System.out.println("�����Դϴ�.");
		}
		finally {
			try {
				reader.close();
				writer.close();
			
			}
			catch (Exception e) {
			}
		}
	}
}
