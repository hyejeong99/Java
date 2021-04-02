
//편의점 클래스 정의 Management class
public class Management{
	private int manageSize=0;//배열에 들어있는 객체 개수
	private Goods goods[]=new Goods[100];//물품 배열 선언
	
	
	public void insertGoods(Goods newGoods) throws Exception { // 물품 리스트에 물품 객체 삽입	
	//	if(manageSize==goods.length)//저장 가능한 물품 수 초과했을 때 익셉션
	//		throw new Exception("최대 물품 수를 초과했습니다.");
		goods[manageSize++]=newGoods;	// 전체 물품 종류 수 증가
	}
	public void changeGoods(Goods newGoods,int change_num) {//물품 수정
		goods[change_num]=newGoods;
	}
	public String printGoods(int i) {//물품 목록 출력
		return "물품 카테고리: "+ goods[i].getCategory()+" 물품명 : "+goods[i].getName()+" 물품 가격 : "+goods[i].getPrice()+" 물품 재고 : "+goods[i].getStock();

	}
	//manageSize접근자 변경자
	int getManageSize() {//접근자
		return manageSize;
	}
	void setManageSize(int size) {//변경자
		manageSize=size;
	}
    public int findGoodsIndex(String goodsName) throws Exception{//삭제할 인덱스 찾기
    		int goodsIndex=0;//인덱스
    		boolean find=false;//물품 찾았는지
    		for(int i=0;i<manageSize;i++) {
    			if(goodsName.equals(goods[i].getName())) {
    				goodsIndex=i;
    				find=true; //물품 찾음
    				System.out.println("물품을 찾았습니다!");
    				break;
    			}
    		}
    		if(find==false) {
    			throw new Exception("찾는 물품이 없습니다.");//찾는 물품이 없을 때
    		}
    		return goodsIndex;
	}


	public void  deleteGoods(int index) {//물품 삭제
		System.out.println("물품을 삭제하도록 하겠습니다.");
		for(int i=index;i<manageSize;i++) {
			goods[i]=goods[i+1];//한칸씩 밀기
		}
		manageSize--;
		////삭제하기
		System.out.println("물품 삭제 완료!");
	}
	public Goods[] findGoodsCategory(String categoryName)throws Exception{//카테고리 찾아줌
		boolean find = false; //대분류 찾았는지
		Goods findGoods[]=new Goods[manageSize]; //반환할 배열
		for(int i=0;i<manageSize;i++) {
			if(categoryName.equals(goods[i].getCategory())) {//찾는 대분류=물품 대분류
				findGoods[i]=goods[i]; //찾는것과 같다
				find=true;//찾았음
				System.out.println("카테고리를 찾았습니다!");
			}
		}
		if(find==false) {//못찾았을 때
			throw new Exception("찾는 카테고리가 없습니다.");			
		}
		return findGoods; //배열 반환
	}
	int totalSales=0;//오늘의 총 매출
	public int sellEstimate(int index, int sellCount) {//구매 이전의 값을 찾아주는 함수
		int payment=0;
		System.out.println("----구매 이전의 물품 정보----");
		System.out.println("물품 카테고리:"+goods[index].getCategory());
		System.out.println("물품명:"+goods[index].getName());
		System.out.println("물품 가격:"+goods[index].getPrice());
		System.out.println("물품 재고:"+goods[index].getStock());
		if(sellCount>goods[index].getStock()) {
			System.out.println("재고는 "+goods[index].getStock()+"뿐입니다.");
			System.out.println("물품 구매를 종료하도록 하겠습니다.");
		}
		else {
			payment=goods[index].getPrice()*sellCount;
			System.out.println("지불 금액:"+payment);
		}
		return payment;
	}
	public int sell(int index, int sellCount) {//구매를 행하는 함수
		
		
		if(goods[index].getStock()==0||sellCount>goods[index].getStock()) {//재고가 없을 때
			System.out.println("재고가 부족해서 구매를 진행할 수 없습니다.");
		}
		else {
			
			//int payment=0;//지불해야될 금액
			System.out.println("-----구매 수량:"+sellCount+" 만큼 구매를 진행하도록 하겠습니다.----");
			int payment=(goods[index].getPrice()*sellCount);
			int subCount=goods[index].getStock()-sellCount;
			//sales+=payment;
			//int sales=payment;
			totalSales+=payment;//오늘의 총 매출
			//totalSales+=sales;
			System.out.println("구매 후 물품 수량:"+subCount);
			goods[index].setStock(subCount);
			System.out.println("지불 금액:"+payment);
			System.out.println("구매 완료!");
			System.out.println("오늘의 총 매출:"+totalSales);
			if(subCount==0) {//있는 재고 모두 물품을 구매했을 때
				System.out.println("재고가 소진되어 물품을 삭제하도록 하겠습니다.");
				deleteGoods(index);//물품 삭제
			}
		}
		return totalSales; 
	}
}

