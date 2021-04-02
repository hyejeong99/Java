import java.io.*;
import java.util.Scanner;
public class CopyBuffer {
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		BufferedInputStream in=null;
		BufferedOutputStream out=null;
		System.out.println("====COPY BUFFER====");
		System.out.println("복사 가능한 사진 이름 이름:suzy.jpg");
		try {
			System.out.printf("복사할 사진 이름:");//복사 할 사진 이름 입력받기
			String myPicture=scan.next();
			try {
				System.out.printf("복사받을 사진 이름:");//복사 받을 사진 이름 입력받기
				String copyMyPicture=scan.next();
				in=new BufferedInputStream(new FileInputStream(myPicture+".jpg"));//불러올 사진//파일을 연다.
				out=new BufferedOutputStream(new FileOutputStream(copyMyPicture+".jpg"));//여기에 새로 저장
				int size=0;
				byte arr[]=new byte[1024];//1024바이트씩 읽음
				long start=System.currentTimeMillis();//현재 시간을 저장

				while((size=in.read(arr))!=-1) {
					out.write(arr,0,size);
				}
				long end=System.currentTimeMillis();//현재 시간을 저장
				System.out.println("수행 시간:"+(end-start));
			}
			catch(FileNotFoundException fnfe) {//수정 필요
				System.out.println("파일이 존재하지 않습니다.");
			}
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("복사 할 파일이 존재하지 않습니다.");
		}
		catch (IOException ioe) { 
			System.out.println(args[0] + " 파일을 읽을 수 없습니다."); 
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

