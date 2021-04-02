import java.io.*;
import java.util.Scanner;
public class CopyBuffer {
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		BufferedInputStream in=null;
		BufferedOutputStream out=null;
		System.out.println("====COPY BUFFER====");
		System.out.println("���� ������ ���� �̸� �̸�:suzy.jpg");
		try {
			System.out.printf("������ ���� �̸�:");//���� �� ���� �̸� �Է¹ޱ�
			String myPicture=scan.next();
			try {
				System.out.printf("������� ���� �̸�:");//���� ���� ���� �̸� �Է¹ޱ�
				String copyMyPicture=scan.next();
				in=new BufferedInputStream(new FileInputStream(myPicture+".jpg"));//�ҷ��� ����//������ ����.
				out=new BufferedOutputStream(new FileOutputStream(copyMyPicture+".jpg"));//���⿡ ���� ����
				int size=0;
				byte arr[]=new byte[1024];//1024����Ʈ�� ����
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

