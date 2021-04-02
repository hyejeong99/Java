/*
 * 프로그램:편의점 관리 프로그램
 * 작성자:류혜정
 * 작성일:2019.06.01
 */
/*
 * 두개 이상 등록하면 제대로 안됨
 * 1개 등록->삭제->출력->코드번호 2로 뜸
 * */
import java.util.*;

public class UserInterface{
	public static void main(String args[])throws Exception{
		Scanner scan=new Scanner(System.in);
		Management manager=new Management();//매니저 객체 생성
		while(true){
			Menu.mainMenu();//메인 메뉴를 보여준다
			try {
				
				int mainMenu = scan.nextInt();scan.nextLine();//번호를 입력받는다.
				switch(mainMenu) {//번호에 따라 수행
				case 1://사용자 모드
					if(manager.getManageSize()==0) {//등록된 물품이 없을 때
						System.out.println("----물품이 하나도 없습니다.----");
						System.out.println("물품 구매를 종료하도록 하겠습니다.");
					}
					else {
						System.out.println("---사용자 모드---");
						System.out.println("물품 구매를 시작하도록 하겠습니다.");
						System.out.println("--------전체 물품----------");
						for(int i=0;i<manager.getManageSize();i++) {//수정 가능한 번호 알려주기
							System.out.println(manager.printGoods(i));//전체 물품 출력
						}
						System.out.printf("물품 이름:");
						String goodsName=scan.nextLine();
						try {// 카테고리로 물품 찾기
	    					int index=manager.findGoodsIndex(goodsName);//물품의 인덱스
	    					
	    					System.out.printf("구매 수량을 입력해주세요:");
	    					int sellCount=scan.nextInt();scan.nextLine();
	    					manager.sellEstimate(index, sellCount);
	    					System.out.println("물품을 구매하시겠습니까?");
	    					try {
	    						System.out.printf("구매하면1, 구매안하면0을 입력해주세요:");
	    						int buy=scan.nextInt();scan.nextLine();
	    						if(buy==1) {//구매할 때
	    							manager.sell(index, sellCount);
	    						}
	    						else if(buy==0) {//구매 안할 때
	    							System.out.println("구매하지 않아 프로그램을 종료하도록 하겠습니다.");
	    						}
	    					}
	    					catch(java.util.InputMismatchException e){//잘못 입력했을 때
	    						System.out.println("메뉴 번호를 잘못 입력했습니다.");
	    						scan.nextLine();
	    					}
	    					
						}
						catch (Exception e) { // 찾는 물품이  없을 경우
	    					String msg = e.getMessage();
	    					System.out.println("찾는 물품이 없습니다.");
	    				}
					}
				case 2://관리자 모드
					Menu.manageMenu();//관리자 메뉴를 보여준다.
							try {
								int manageMenu=scan.nextInt();//메뉴를 입력받는다.
								scan.nextLine();
								switch(manageMenu){//관리자 메뉴
								case 1://물품 등록
									System.out.println("물품을 등록하도록 하겠습니다.");
									System.out.println("----------------------");
									System.out.println("====등록될 상품목록을 입력해주세요====");//물품 등록							
									try {
										//등록할 물품 정보 입력
										System.out.printf("물품 카테고리를 입력하세요:");
										String cate=scan.next();
										System.out.printf("물품 이름을 입력하세요:");
										String name=scan.next();
										System.out.printf("물품 가격을 입력하세요:");
										int price=scan.nextInt();scan.nextLine();
										System.out.printf("물품 개수를 입력하세요:");
										int stock=scan.nextInt();	
										Goods newGoods = new Goods(cate,name,price,stock); // Goods 객체 생성
										try {
						                	manager.insertGoods(newGoods); // 새로운 물품 삽입
						                	scan.nextLine();
						                }
						                catch (Exception e) { // 물품 갯수 초과
						                	String msg = e.getMessage();
						                	System.out.println("물품 개수를 초과했습니다.");
						                }
									}
								catch(java.util.InputMismatchException e) {
					    			System.out.println("잘못된 입력입니다.");
					    			scan.nextLine();
					    		}
								break;
									
								case 2://수정
									//물품 재고 수정
									if(manager.getManageSize()==0) {//물품이 하나도 없을 때
										System.out.println("----물품이 하나도 없습니다.----");
										System.out.println("물품 수정을 종료하도록 하겠습니다.");
										break;
									}
									else {//물품이 있을 때
										System.out.println("물품을 수정하도록 하겠습니다.");
										System.out.println("--------전체 물품----------");
										for(int i=0;i<manager.getManageSize();i++) {//수정 가능한 번호 알려주기
											System.out.println(manager.printGoods(i));//전체 물품 출력
										}
										{
											try {
												System.out.printf("수정할 물품 이름을 입력하세요:");
												String changeName=scan.next();
												int index=manager.findGoodsIndex(changeName);//이름의 인덱스 찾아주기
												{//물품 정보 출력
													System.out.println("-----수정할 물품 정보는-----");
													System.out.println(manager.printGoods(index));
												}
												//물품 수정
												System.out.println("-------------------------");
												System.out.println("물품번호:"+index+"\n");
												//수정할 물품에 대한 정보 입력
												System.out.printf("수정할 물품 카테고리를 입력하세요:");
												String change_cate=scan.next();
												System.out.printf("수정할 물품 이름을 입력하세요:");
												String change_n=scan.next();
												System.out.printf("수정할 물품 가격을 입력하세요:");
												int change_p=scan.nextInt();
												scan.nextLine();
												System.out.printf("수정할 물품 개수를 입력하세요:");
												int change_s=scan.nextInt();
												scan.nextLine();
												
												Goods change_newGoods=new Goods(change_cate,change_n,change_p,change_s);//물품 수정
												manager.changeGoods(change_newGoods,index);
												
												System.out.println("===수정된 물품 현황===");
												//수정된 데이터를 출력한다.
													System.out.println(manager.printGoods(index));
											}
											catch (Exception e) { // 찾는 물품이  없을 경우
						    					String msg = e.getMessage();
						    					System.out.println("찾는 물품이 없습니다.");
						    				}
											break;

										}
									}
								case 3://출력
									if(manager.getManageSize()==0)//물품 하나도 없을 때
				    				{
										System.out.println("----물품이 하나도 없습니다.----");
										System.out.println("물품 삭제를 종료하도록 하겠습니다.");
				    				}
					    			else {
					    				System.out.println("====물품 정보 출력====");
					    				for(int i=0;i<manager.getManageSize();i++) {
					    					System.out.println("["+(i+1)+"] "+manager.printGoods(i));
					    				}
					    			}
					    			break;

								case 4://삭제
									if(manager.getManageSize()==0||manager.getManageSize()<0) {//물품이 없을 때
										System.out.println("----물품이 하나도 없습니다.----");
										System.out.println("물품 삭제를 종료하도록 하겠습니다.");
										break;
									}
									else {//물품 있을 때
											try {
												System.out.println("필요 없는 물품을 삭제하시겠습니까?맞으면1 아니면0");
												int delete=scan.nextInt();
												scan.nextLine();
												if(delete==1) {//삭제
													System.out.println("물품 삭제를 진행하도록 하겠습니다.");
													System.out.printf("삭제할 물품명 입력해주세요:");
													String deleteName=scan.next();
													try {
														int deletIndex=manager.findGoodsIndex(deleteName);//삭제할 물품의 인덱스를 찾음
														manager.deleteGoods(deletIndex);//물품 삭제
													}
													catch(Exception e) {//찾는 물품이 없는 경우
														String msg=e.getMessage();
														System.out.println("찾는 물품이 없습니다.");
													}
													break;
												
												}
												else if(delete==0) {//삭제 안함
													System.out.println("프로그램을 종료하도록 하겠습니다.");break;
												}
											}
											catch(java.util.InputMismatchException e){ //물품의 정보의 형식을 잘못 입력했을 경우
												System.out.println("잘못된 입력입니다.");
												scan.nextLine(); //키보드 버퍼 해결
											}

									}
								case 5://물품 검색
									if(manager.getManageSize()==0||manager.getManageSize()<0) {//물품이 없을 때
										System.out.println("----물품이 하나도 없습니다.----");
										System.out.println("물품 검색을 종료하도록 하겠습니다.");
										break;
									}
									else{
										System.out.println("물품 검색을 시작하도록 하겠습니다.");
										System.out.println("1:물품 카테고리로 검색  2:물품명으로 검색");
										int select=scan.nextInt();scan.nextLine();
										if(select==1){
											System.out.printf("물품 카테고리를 입력해주세요:");
											String category=scan.next();
											try {
												System.out.println("-------카테고리 "+category+"에 속하는 물품-------");
												Goods findgoods[]=manager.findGoodsCategory(category);
												for(int i=0;i<manager.getManageSize();i++) {//출력
													if(findgoods[i]!=null) {//물품 정보 출력
														System.out.println("물품명:"+findgoods[i].getName()); // 물품 출력
						    							System.out.println("물품 가격:"+findgoods[i].getPrice()); // 물품 출력
						    							System.out.println("물품 재고:"+findgoods[i].getStock()); // 물품 출력
													}
													else {
														System.out.println("물품이 존재하지 않습니다.");
													}
												}
											}
											catch(Exception e) { // 대분류를 찾지 못한 경우
						    					String msg = e.getMessage();
						    					System.out.println("찾는 물품이 없습니다.");
						    				}
						    				break;
										}
										if(select==2) {
											System.out.printf("물품명을 입력해주세요:");
											String goodsName=scan.nextLine();
											try {
												int index = manager.findGoodsIndex(goodsName); // 물품의 인덱스
						    					System.out.println(manager.printGoods(index)); // 물품 출력
											}
											catch (Exception e) { // 찾는 물품이  없을 경우
						    					String msg = e.getMessage();
						    					System.out.println(msg);
						    				}
						    				break;
										}
									}
								case 0://종료
									System.out.println("프로그램을 종료하도록 하겠습니다.");break;
								default:
									System.out.println("다시 입력하세요 ");break;
								}
							}
							catch(java.util.InputMismatchException e){ //물품의 정보의 형식을 잘못 입력했을 경우
								System.out.println("잘못된 입력입니다.");
								scan.nextLine(); //키보드 버퍼 해결
							}
					}


			}
			catch(java.util.InputMismatchException e){//잘못 입력했을 때
				System.out.println("메뉴 번호를 잘못 입력했습니다.");
				scan.nextLine();
			}
	}

	}
	
}
