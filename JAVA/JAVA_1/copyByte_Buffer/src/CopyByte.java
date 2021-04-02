import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
public class CopyByte {
	public static void main(String args[])throws Exception{//���� �ͼ���ó��
		Scanner scan=new Scanner(System.in);
		FileInputStream in=null;//���Ϸκ��� ����Ʈ ������ �����͸� �о��
		FileOutputStream out=null;//����Ʈ �����͸� ���Ͽ� ��
		System.out.println("====COPY BYTE====");
		System.out.println("���� ������ ���� �̸� �̸�:suzy.jpg");
		try {
			System.out.printf("������ ���� �̸�:");//���� �� ���� �̸� �Է¹ޱ�
			String myPicture=scan.next();
			try {
				System.out.printf("������� ���� �̸�:");//���� ���� ���� �̸� �Է¹ޱ�
				String copyMyPicture=scan.next();
				in=new FileInputStream(myPicture+".jpg");//�ҷ��� ����//������ ����.
				out=new FileOutputStream(copyMyPicture+".jpg");//���⿡ ���� ����
				int size=0;
				byte arr[]=new byte[16];//16����Ʈ�� ����
				long start=System.currentTimeMillis();//���� �ð��� ����
				while((size=in.read(arr))!=-1) {
					out.write(arr,0,size);
				}
				long end=System.currentTimeMillis();//���� �ð��� ����
				System.out.println("���� �ð�:"+(end-start));
			}
			catch(FileNotFoundException fnfe) {//���� �ʿ�
				System.out.println("������ �������� �ʽ��ϴ�.");
			}
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("���� �� ������ �������� �ʽ��ϴ�.");
		}
		catch (IOException ioe) { 
			System.out.println(args[0] + " ������ ���� �� �����ϴ�."); 
		}
		finally{
			try {
				in.close();
				out.close();
			}
			catch (Exception e) { } 
		}
	
		
	}

}
