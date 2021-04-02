import java.io.*;
import java.util.Scanner;
public class CopyFile {
	public static void main(String args[]) {
		FileWriter writer=null;//비워주기
		FileReader reader=null;//비워주기
		Scanner scan=new Scanner(System.in);
		System.out.println("복사 가능한 파일은  'hello.txt'가 있습니다.");//복사 가능한 파일 알려주기
		System.out.printf("복사할 파일 이름:");
		String copyFileName=scan.next();//어떤 파일 복사받을것인지 입력받기		
		System.out.printf("저장될 파일 이름:");
		String fileName=scan.next();//파일 이름
		try {
			reader=new FileReader(copyFileName+".txt");
			writer=new FileWriter(fileName+".txt");
			while(true) {
					int data=reader.read();
						if(data==-1) break;
						char ch=(char) data;
						writer.write(ch);
			}System.out.println("복사 완료!");
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("파일이 존재하지 않습니다.");
		}
		catch (IOException ioe) {
			System.out.println("오류입니다.");
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
