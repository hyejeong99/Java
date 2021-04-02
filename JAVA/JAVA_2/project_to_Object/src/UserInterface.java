/*
 * 프로그램:편의점 관리 프로그램
 * 작성자:류혜정
 * 작성일:2019.06.01
 */
/*
 * 두개 이상 등록하면 제대로 안됨
 * 1개 등록->삭제->출력->코드번호 2로 뜸
 * */
import java.util.Scanner;
import java.io.*;

public class UserInterface{
	public static void main(String args[])throws Exception{
		
		Scanner scan=new Scanner(System.in);
		ObjectInputStream in=null;
		Management manager = null;
		try {//읽어오기
			manager=new Management(in);//생성자 함수에서 파일 객체 받아오기
		}
		catch(FileNotFoundException e) {	//파일이 존재하지 않는 경우
			System.out.println("존재하지 않는 파일입니다.");
		}
		catch(IOException e) {	//읽어올 수 없는 파일인 경우
			System.out.println("파일로 출력할 수 없습니다.");
		}
		catch(Exception e) {	//읽어오는 과정에서 문제가 발생한 경우
			System.out.println("파일 로드에서 에러가 발생했습니다.");
		}
		int codeCount=0;//코드
		int totalSales=0;//오늘의 총 매출
		while(true){
			Menu.mainMenu();//메인 메뉴를 보여준다
			try {
				int answer; //사용자의 응답을 저장할 변수 answer(int형)
				String goodsName; //사용자의 응답을 저장할 변수 goodsName(String형)
				char answerChar; //사용자의 응답을 저장할 변수 answerChar(char형)
				try {
					answer=scan.nextInt();	//키보드 버퍼를 비우기 위한 nextLine()
				}
				catch(java.util.InputMismatchException e){	//사용자가 정수가 아닌 다른 값을 입력한 경우
					System.out.println("숫자만 입력하세요.");	//숫자만 입력하라는 안내 문구 출력
					answer = 0;	//사용자가 입력한 값을 answer에 저장할 수 없으므로 answer을 0으로 초기화
				}
				scan.nextLine();
				
				switch(answer) {//번호에 따라 수행
				case 1://사용자 메뉴
					if(manager.getManageSize()==0) {//등록된 물품이 없을 때
						System.out.println("※물품이 하나도 없습니다.※");
						System.out.println("※물품 구매를 종료하도록 하겠습니다.※");
						break;
					}
					else {//등록된 물품이 있을 때
						Menu.userMenu(); //사용자 메뉴 선택지 출력
						try {
							answer = scan.nextInt();scan.nextLine();
							if(answer==1) {//상품 구매를 선택한 경우
								int index=0;//구매 물품 인덱스
								int buy=0;//살지(1)/말지(0)
								int sellCount=0;//구매 수량
								System.out.println("물품 구매를 시작하도록 하겠습니다.");
								System.out.println("--------전체 물품----------");
								for(int i=0;i<manager.getManageSize();i++) {//수정 가능한 번호 알려주기
									System.out.println("-----"+(i+1)+"-----");
			                        System.out.println("물품 카테고리:"+ manager.goodsArr(i).getCategory());
			                        System.out.println("물품명 :"+manager.goodsArr(i).getName());
			                        System.out.println("물품 가격 :"+manager.goodsArr(i).getPrice());
			                        System.out.println("물품 재고 :"+manager.goodsArr(i).getStock());
								}
								try {
									System.out.printf("구매할 물품 이름:");
									goodsName=scan.next();
									index=manager.findGoodsIndex(goodsName);//물품의 인덱스
								}
								catch(Exception e) { // 인덱스를 찾지 못한 경우
			    					String msg = e.getMessage();
			    					System.out.println(msg);
			    				}
									System.out.println("((((((구매할 물품 정보))))))");
									System.out.println("-----"+(index+1)+"-----");
			                        System.out.println("물품 카테고리:"+ manager.goodsArr(index).getCategory());
			                        System.out.println("물품명 :"+manager.goodsArr(index).getName());
			                        System.out.println("물품 가격 :"+manager.goodsArr(index).getPrice());
			                        System.out.println("물품 재고 :"+manager.goodsArr(index).getStock());
									try {// 카테고리로 물품 찾기
				    					System.out.println("물품을 구매하시겠습니까?");
				    					System.out.printf("구매하면1, 구매안하면0을 입력해주세요:");
			    						buy=scan.nextInt();scan.nextLine();
									}
									catch (java.util.InputMismatchException e) { //번호를 잘못 입력했을 때
										System.out.println("잘못된 입력입니다.");
										scan.nextLine(); //키보드 버퍼 해결
				    				}
				    						if(buy==1) {//구매할 때
				    							try {
				    								System.out.printf("구매 수량을 입력해주세요:");
							    					sellCount=scan.nextInt();scan.nextLine();
							    					System.out.println("구매 전 물품 수량:"+manager.sellEstimate(index, sellCount));
					    							System.out.println("구매 수량:"+sellCount+" 만큼 구매를 진행하도록 하겠습니다.");
					    							int payment=manager.sell(index, sellCount);//payment=지불금액
					    							totalSales+=payment;
					    							System.out.println("지불 금액:"+payment);
					    							System.out.println("오늘의 총 매출:"+totalSales);
					    							System.out.println("구매 완료!");
				    							}
				    							catch(java.lang.Exception e) {
				    								String msg = e.getMessage();
							    					System.out.println(msg);
				    							}
				    							
				    						}
				    						else if(buy==0) {//구매 안할 때
				    							System.out.println("구매하지 않아 프로그램을 종료하도록 하겠습니다.");
				    						}

									
								
							}
						}catch(java.util.InputMismatchException e) {	//숫자가 아닌 값을 입력한 경우
							System.out.println("숫자만 입력하세요.");
						}
						
					}
					break;
				case 2://관리자 모드
					Menu.manageMenu();//관리자 메뉴를 보여준다.
							try {
								int manageMenu=scan.nextInt();
								switch(manageMenu){//관리자 메뉴
								case 1://물품 등록
									
									String cate,name;//카테고리,이름
									int price, stock;//가격,재고
									System.out.println("물품을 등록하도록 하겠습니다.");
									System.out.println("----------------------");
									System.out.println("====등록될 상품목록을 입력해주세요====");//물품 등록							
									try {
										//등록할 물품 정보 입력
										System.out.printf("물품 카테고리를 입력하세요:");
										cate=scan.next();
										System.out.printf("물품 이름을 입력하세요:");
										name=scan.next();
										System.out.printf("물품 가격을 입력하세요:");
										price=scan.nextInt();scan.nextLine();
										System.out.printf("물품 개수를 입력하세요:");
										stock=scan.nextInt();	
										Goods newGoods = new Goods(cate,name,price,stock,codeCount); // Goods 객체 생성
										try {
											manager.insertGoods(newGoods); // 새로운 물품 삽입
						                	scan.nextLine();
						                }
						                catch (Exception e) {
						                	String msg=e.getMessage();
					    					System.out.println(msg);
						                }
									}
								catch(java.util.InputMismatchException e) {
					    			System.out.println("잘못된 입력입니다.");
					    			scan.nextLine();
					    		}
								break;	
								case 2://수정
									String changeName=null;
									int index=0;
									//물품 재고 수정
									if(manager.getManageSize()==0) {//물품이 하나도 없을 때
										System.out.println("※물품이 하나도 없습니다.※");
										System.out.println("※물품 수정을 종료하도록 하겠습니다.※");
										break;
									}
									else {//물품이 있을 때
										System.out.println("물품을 수정하도록 하겠습니다.");
										System.out.println("--------전체 물품----------");
										for(int i=0;i<manager.getManageSize();i++) {//수정 가능한 번호 알려주기
											System.out.println("-----"+(i+1)+"-----");
					                        System.out.println("물품 카테고리:"+ manager.goodsArr(i).getCategory());
					                        System.out.println("물품명 :"+manager.goodsArr(i).getName());
					                        System.out.println("물품 가격 :"+manager.goodsArr(i).getPrice());
					                        System.out.println("물품 재고 :"+manager.goodsArr(i).getStock());
										}
										{
											try {
												System.out.printf("수정할 물품 이름을 입력하세요:");
												changeName=scan.next();
												index=manager.findGoodsIndex(changeName);//이름의 인덱스 찾아주기
											}
											catch (Exception e) { // 찾는 물품이  없을 경우
						    					String msg = e.getMessage();
						    					System.out.println(msg);
						    				}
												{//물품 정보 출력
													System.out.println("-----수정할 물품 정보는-----");
													System.out.println("-----"+(index+1)+"-----");
							                        System.out.println("물품 카테고리:"+ manager.goodsArr(index).getCategory());
							                        System.out.println("물품명 :"+manager.goodsArr(index).getName());
							                        System.out.println("물품 가격 :"+manager.goodsArr(index).getPrice());
							                        System.out.println("물품 재고 :"+manager.goodsArr(index).getStock());
												}
												//물품 수정
												System.out.println("-------------------------");
												System.out.println("물품번호: ["+(index+1)+"]\n");
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
												
												Goods change_newGoods=new Goods(change_cate,change_n,change_p,change_s,codeCount);//물품 수정
												try{
													manager.changeGoods(change_newGoods,index);
												}
												catch(Exception e) {//물품 수정 도중 오류
													String msg=e.getMessage();
													System.out.println(msg);
												}
												
												System.out.println("===수정된 물품 현황===");
												//수정된 데이터를 출력한다.
												System.out.println("-----"+(index+1)+"-----");
						                        System.out.println("물품 카테고리:"+ manager.goodsArr(index).getCategory());
						                        System.out.println("물품명 :"+manager.goodsArr(index).getName());
						                        System.out.println("물품 가격 :"+manager.goodsArr(index).getPrice());
						                        System.out.println("물품 재고 :"+manager.goodsArr(index).getStock());
										}
									}
									break;
								case 3://출력
									if(manager.getManageSize()==0)//물품 하나도 없을 때
				    				{
										System.out.println("※물품이 하나도 없습니다.※");
										System.out.println("※물품 삭제를 종료하도록 하겠습니다.※");
										break;
				    				}
					    			else {
					    				for(int i=0;i<manager.getManageSize();i++) {
					    					System.out.println("====전체 물품====");
					    					System.out.println("-----"+(i+1)+"-----");
					                        System.out.println("물품 카테고리:"+ manager.goodsArr(i).getCategory());
					                        System.out.println("물품명 :"+manager.goodsArr(i).getName());
					                        System.out.println("물품 가격 :"+manager.goodsArr(i).getPrice());
					                        System.out.println("물품 재고 :"+manager.goodsArr(i).getStock());
					    				}
					    				
					    			}
									break;

								case 4://삭제
									int deletIndex=0;//삭제할 물품의 인덱스
									if(manager.getManageSize()==0||manager.getManageSize()<0) {//물품이 없을 때
										System.out.println("※물품이 하나도 없습니다.※");
										System.out.println("※물품 삭제를 종료하도록 하겠습니다.※");
										break;
									}
									else {//물품 있을 때
											try {
												System.out.println("필요 없는 물품을 삭제하시겠습니까?맞으면1 아니면0");
												int delete=scan.nextInt();
												scan.nextLine();
												if(delete==1) {//삭제
													System.out.println("물품 삭제를 진행하도록 하겠습니다.");
													for(int i=0;i<manager.getManageSize();i++) {//검색 가능한 물품 알려주기
														System.out.println("---삭제 가능한 물품 목록---");
														System.out.println("-----"+(i+1)+"-----");
								                        System.out.println("물품 카테고리:"+ manager.goodsArr(i).getCategory());
								                        System.out.println("물품명 :"+manager.goodsArr(i).getName());
								                        System.out.println("물품 가격 :"+manager.goodsArr(i).getPrice());
								                        System.out.println("물품 재고 :"+manager.goodsArr(i).getStock());
														
													}
													System.out.printf("삭제할 물품명 입력해주세요:");
													String deleteName=scan.next();
													try {
														deletIndex=manager.findGoodsIndex(deleteName);//삭제할 물품의 인덱스를 찾음
														System.out.println("물품을 삭제하도록 하겠습니다.");
														manager.deleteGoods(deletIndex);//물품 삭제
														System.out.println("물품이 삭제되었습니다.");
													}
													catch(Exception e) {//찾는 물품이 없는 경우
														String msg=e.getMessage();
														System.out.println(msg);
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
									break;
								case 5://물품 검색
									String selectGoodsName=null;
									int selectGoodsIndex=0;
									if(manager.getManageSize()==0||manager.getManageSize()<0) {//물품이 없을 때
										System.out.println("※물품이 하나도 없습니다.※");
										System.out.println("※물품 검색을 종료하도록 하겠습니다.※");
										break;
									}
									else{
										System.out.println("물품 검색을 시작하도록 하겠습니다.");
										System.out.printf("물품명을 입력해주세요:");
										selectGoodsName=scan.next();
										try {
											selectGoodsIndex = manager.findGoodsIndex(selectGoodsName); // 물품의 인덱스
											System.out.println("-----"+(selectGoodsIndex+1)+"-----");
					                        System.out.println("물품 카테고리:"+ manager.goodsArr(selectGoodsIndex).getCategory());
					                        System.out.println("물품명 :"+manager.goodsArr(selectGoodsIndex).getName());
					                        System.out.println("물품 가격 :"+manager.goodsArr(selectGoodsIndex).getPrice());
					                        System.out.println("물품 재고 :"+manager.goodsArr(selectGoodsIndex).getStock());
										
										}
										catch (Exception e) { // 찾는 물품이  없을 경우
					    					String msg = e.getMessage();
					    					System.out.println(msg);
					    				}
									}
									break;
								case 6://오늘의 매출
									System.out.println("##오늘의 총 매출##");
									System.out.println("오늘의 총 매출:"+totalSales);
									break;
								case 7://저장
									ObjectOutputStream out=null;
									try {
										//fOut에 information.data파일 저장
										//FileOutputStream fOut = new FileOutputStream("information.data");
										//out에 fOut저장
										out = new ObjectOutputStream(new FileOutputStream("information.data"));
										
										//out에 객체의 프로그램 실행 내용을 저장
										manager.saveFile(out);
										System.out.println("데이터가 저장되었습니다.");
									}
									catch(IOException e) {	//파일로 출력이 제대로 이루어지지 않는 경우
										System.out.println("파일로 출력할 수 없습니다.");
									}
									catch(Exception e) {	//출력을 하는 과정에서 에러가 발생한 경우
										System.out.println("저장에 실패했습니다.");
									}
									finally {
										try {
											out.close();
											}
										catch (Exception e){
											
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
				System.out.println("잘못된 입력입니다.");
				scan.nextLine(); //키보드 버퍼 해결
			}
	}

	}
	
}