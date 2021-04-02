import java.io.*;
import java.util.*;
import java.util.LinkedList;
//편의점 클래스 정의 Management class
public class Management{
	private LinkedList<Goods>goods=new LinkedList<Goods>();//배열을 LinkedList로
	private int manageSize=goods.size();//배열에 들어있는 객체 개수
	private int count=0; //insertGoods함수가 호출된 횟수를 나타내는 데이터필드
	private int totalSales=0;
	
	public Goods goodsArr(int i) {
		return goods.get(i);
	}
	public void insertGoods(Goods newGoods) throws Exception { // 물품 리스트에 물품 객체 삽입	
		goods.add(newGoods);
		if(manageSize==100) {//저장 물품이 100개가 됐을 때
			throw new Exception("최대 물품 수를 초과했습니다.");
		}
		count++;
	}
	int getcount() {	//insertGoods함수가 실행된 횟수를 반환해주는 메소드
		return count;
	}
	
	int getTotalSales() {	//총 매출액을 반환해주는 메소드
		return totalSales;
	}
	public void changeGoods(Goods newGoods,int change_num) {//물품 수정
		goods.set(change_num, newGoods);
	}
	//manageSize접근자 변경자
	int getManageSize() {//접근자
		return goods.size();
	}
	void setManageSize(int size) {//변경자
		manageSize=size;
	}
    public int findGoodsIndex(String goodsName) throws Exception{//삭제할 인덱스 찾기
    		int goodsIndex=-1;//인덱스
    		Iterator<Goods>iterator=goods.iterator();
    		while(iterator.hasNext()) {
    			Goods good=iterator.next();
    			goodsIndex++;//인덱스 증가
    			if(good.getName().equals(goodsName)) {//파라미터로 받은 이름과 같은 물품이면
    				return goodsIndex;
    			}
    		}
    		throw new Exception("찾는 물품이 없습니다.");
	}


	public void deleteGoods(int index) {//물품 삭제
		goods.remove(index);
	}
	public Goods[] findGoodsCategory(String categoryName)throws Exception{//카테고리 찾아줌
		boolean find = false; //대분류 찾았는지
		Goods findGoods[]=new Goods[manageSize]; //반환할 배열
		for(int i=0;i<manageSize;i++) {
			if(categoryName.equals(goods.get(i).getCategory())) {//찾는 대분류=물품 대분류
				findGoods[i]=goods.get(i); //찾는것과 같다
				find=true;//찾았음
			}
		}
		if(find==false) {//못찾았을 때
			throw new Exception("찾는 카테고리가 없습니다.");			
		}
		return findGoods; //배열 반환
	}
	public int sellEstimate(int index, int sellCount) throws Exception{//구매 이전의 값을 찾아주는 함수

		if(sellCount>goodsArr(index).getStock()) {
			throw new Exception("재고는 "+goods.get(index).getStock()+"뿐입니다."+"물품 구매를 종료하도록 하겠습니다.");
		}
		return goods.get(index).getStock();//구매 이전의 물품 재고
	}
	
	public int sell(int index, int sellCount) throws Exception{//구매를 행하는 함수
		int payment=0;//지불 금액
		if(sellCount>goods.get(index).getStock()) {//재고가 없을 때
			throw new Exception("재고가 부족해서 구매를 진행할 수 없습니다.");
		}
		else {
			payment=(goods.get(index).getPrice()*sellCount);//지불 금액
			int subCount=goods.get(index).getStock()-sellCount;//구매 후 물품 수량
			goods.get(index).setStock(subCount);
			if(subCount==0) {//재고 다 썼을 때
				deleteGoods(index);//물품 삭제
			}
			//totalSales+=payment;//총 금액 환산
			return payment; //총 금액 반환
		}
		
	}
	void saveFile(DataOutputStream out) throws Exception{	//데이터를 파일로 출력하는 메소드
		try{
			out.writeInt(totalSales);	//누적 총 매출값 출력
			out.writeInt(manageSize);	//배열에 저장된 객체의 개수 값을 가지고 있는 manageSize 출력
			
			Iterator<Goods>iterator=goods.iterator();
			while(iterator.hasNext()) {
				Goods good=iterator.next();
				good.save(out);
			}
		}catch(IOException ioe) {	//출력이 제대로 이루어지지 않은 경우 익셉션 던지기
			throw new Exception("파일 출력 오류");
		}catch(Exception e) {//예외
			throw new Exception("파일 저장 예외");
		}
	}

	void readFile(DataInputStream in) throws Exception{	//data파일에서 데이터를 읽어오는 메소드
		try {
			totalSales = in.readInt();	//누적 총 매출값 읽어와 저장
			manageSize = in.readInt();	//배열에 저장된 객체의 개수 값을 가지고 있는 manageSize 읽어와 저장
			for(int i=0; i<manageSize; i++) {	//for문을 돌면서 객체가 가지고 있는 데이터 읽어와 저장	
				Goods newgoods=new Goods();
				newgoods.read(in);
				goods.add(newgoods);
			}
		}catch(EOFException fefo) { //파일이 끝났을 경우
			throw new Exception("파일 끝");
		}
		catch(IOException ioe) { //파일을 읽을 수 없을 경우
			throw new Exception("파일 읽기 오류");
		}
		catch(Exception e) { //예외처리
			throw new Exception("파일 읽기 예외");
		}
	}
}

