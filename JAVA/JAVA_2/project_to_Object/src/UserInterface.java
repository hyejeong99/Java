/*
 * ���α׷�:������ ���� ���α׷�
 * �ۼ���:������
 * �ۼ���:2019.06.01
 */
/*
 * �ΰ� �̻� ����ϸ� ����� �ȵ�
 * 1�� ���->����->���->�ڵ��ȣ 2�� ��
 * */
import java.util.Scanner;
import java.io.*;

public class UserInterface{
	public static void main(String args[])throws Exception{
		
		Scanner scan=new Scanner(System.in);
		ObjectInputStream in=null;
		Management manager = null;
		try {//�о����
			manager=new Management(in);//������ �Լ����� ���� ��ü �޾ƿ���
		}
		catch(FileNotFoundException e) {	//������ �������� �ʴ� ���
			System.out.println("�������� �ʴ� �����Դϴ�.");
		}
		catch(IOException e) {	//�о�� �� ���� ������ ���
			System.out.println("���Ϸ� ����� �� �����ϴ�.");
		}
		catch(Exception e) {	//�о���� �������� ������ �߻��� ���
			System.out.println("���� �ε忡�� ������ �߻��߽��ϴ�.");
		}
		int codeCount=0;//�ڵ�
		int totalSales=0;//������ �� ����
		while(true){
			Menu.mainMenu();//���� �޴��� �����ش�
			try {
				int answer; //������� ������ ������ ���� answer(int��)
				String goodsName; //������� ������ ������ ���� goodsName(String��)
				char answerChar; //������� ������ ������ ���� answerChar(char��)
				try {
					answer=scan.nextInt();	//Ű���� ���۸� ���� ���� nextLine()
				}
				catch(java.util.InputMismatchException e){	//����ڰ� ������ �ƴ� �ٸ� ���� �Է��� ���
					System.out.println("���ڸ� �Է��ϼ���.");	//���ڸ� �Է��϶�� �ȳ� ���� ���
					answer = 0;	//����ڰ� �Է��� ���� answer�� ������ �� �����Ƿ� answer�� 0���� �ʱ�ȭ
				}
				scan.nextLine();
				
				switch(answer) {//��ȣ�� ���� ����
				case 1://����� �޴�
					if(manager.getManageSize()==0) {//��ϵ� ��ǰ�� ���� ��
						System.out.println("�ع�ǰ�� �ϳ��� �����ϴ�.��");
						System.out.println("�ع�ǰ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.��");
						break;
					}
					else {//��ϵ� ��ǰ�� ���� ��
						Menu.userMenu(); //����� �޴� ������ ���
						try {
							answer = scan.nextInt();scan.nextLine();
							if(answer==1) {//��ǰ ���Ÿ� ������ ���
								int index=0;//���� ��ǰ �ε���
								int buy=0;//����(1)/����(0)
								int sellCount=0;//���� ����
								System.out.println("��ǰ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.");
								System.out.println("--------��ü ��ǰ----------");
								for(int i=0;i<manager.getManageSize();i++) {//���� ������ ��ȣ �˷��ֱ�
									System.out.println("-----"+(i+1)+"-----");
			                        System.out.println("��ǰ ī�װ�:"+ manager.goodsArr(i).getCategory());
			                        System.out.println("��ǰ�� :"+manager.goodsArr(i).getName());
			                        System.out.println("��ǰ ���� :"+manager.goodsArr(i).getPrice());
			                        System.out.println("��ǰ ��� :"+manager.goodsArr(i).getStock());
								}
								try {
									System.out.printf("������ ��ǰ �̸�:");
									goodsName=scan.next();
									index=manager.findGoodsIndex(goodsName);//��ǰ�� �ε���
								}
								catch(Exception e) { // �ε����� ã�� ���� ���
			    					String msg = e.getMessage();
			    					System.out.println(msg);
			    				}
									System.out.println("((((((������ ��ǰ ����))))))");
									System.out.println("-----"+(index+1)+"-----");
			                        System.out.println("��ǰ ī�װ�:"+ manager.goodsArr(index).getCategory());
			                        System.out.println("��ǰ�� :"+manager.goodsArr(index).getName());
			                        System.out.println("��ǰ ���� :"+manager.goodsArr(index).getPrice());
			                        System.out.println("��ǰ ��� :"+manager.goodsArr(index).getStock());
									try {// ī�װ��� ��ǰ ã��
				    					System.out.println("��ǰ�� �����Ͻðڽ��ϱ�?");
				    					System.out.printf("�����ϸ�1, ���ž��ϸ�0�� �Է����ּ���:");
			    						buy=scan.nextInt();scan.nextLine();
									}
									catch (java.util.InputMismatchException e) { //��ȣ�� �߸� �Է����� ��
										System.out.println("�߸��� �Է��Դϴ�.");
										scan.nextLine(); //Ű���� ���� �ذ�
				    				}
				    						if(buy==1) {//������ ��
				    							try {
				    								System.out.printf("���� ������ �Է����ּ���:");
							    					sellCount=scan.nextInt();scan.nextLine();
							    					System.out.println("���� �� ��ǰ ����:"+manager.sellEstimate(index, sellCount));
					    							System.out.println("���� ����:"+sellCount+" ��ŭ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.");
					    							int payment=manager.sell(index, sellCount);//payment=���ұݾ�
					    							totalSales+=payment;
					    							System.out.println("���� �ݾ�:"+payment);
					    							System.out.println("������ �� ����:"+totalSales);
					    							System.out.println("���� �Ϸ�!");
				    							}
				    							catch(java.lang.Exception e) {
				    								String msg = e.getMessage();
							    					System.out.println(msg);
				    							}
				    							
				    						}
				    						else if(buy==0) {//���� ���� ��
				    							System.out.println("�������� �ʾ� ���α׷��� �����ϵ��� �ϰڽ��ϴ�.");
				    						}

									
								
							}
						}catch(java.util.InputMismatchException e) {	//���ڰ� �ƴ� ���� �Է��� ���
							System.out.println("���ڸ� �Է��ϼ���.");
						}
						
					}
					break;
				case 2://������ ���
					Menu.manageMenu();//������ �޴��� �����ش�.
							try {
								int manageMenu=scan.nextInt();
								switch(manageMenu){//������ �޴�
								case 1://��ǰ ���
									
									String cate,name;//ī�װ�,�̸�
									int price, stock;//����,���
									System.out.println("��ǰ�� ����ϵ��� �ϰڽ��ϴ�.");
									System.out.println("----------------------");
									System.out.println("====��ϵ� ��ǰ����� �Է����ּ���====");//��ǰ ���							
									try {
										//����� ��ǰ ���� �Է�
										System.out.printf("��ǰ ī�װ��� �Է��ϼ���:");
										cate=scan.next();
										System.out.printf("��ǰ �̸��� �Է��ϼ���:");
										name=scan.next();
										System.out.printf("��ǰ ������ �Է��ϼ���:");
										price=scan.nextInt();scan.nextLine();
										System.out.printf("��ǰ ������ �Է��ϼ���:");
										stock=scan.nextInt();	
										Goods newGoods = new Goods(cate,name,price,stock,codeCount); // Goods ��ü ����
										try {
											manager.insertGoods(newGoods); // ���ο� ��ǰ ����
						                	scan.nextLine();
						                }
						                catch (Exception e) {
						                	String msg=e.getMessage();
					    					System.out.println(msg);
						                }
									}
								catch(java.util.InputMismatchException e) {
					    			System.out.println("�߸��� �Է��Դϴ�.");
					    			scan.nextLine();
					    		}
								break;	
								case 2://����
									String changeName=null;
									int index=0;
									//��ǰ ��� ����
									if(manager.getManageSize()==0) {//��ǰ�� �ϳ��� ���� ��
										System.out.println("�ع�ǰ�� �ϳ��� �����ϴ�.��");
										System.out.println("�ع�ǰ ������ �����ϵ��� �ϰڽ��ϴ�.��");
										break;
									}
									else {//��ǰ�� ���� ��
										System.out.println("��ǰ�� �����ϵ��� �ϰڽ��ϴ�.");
										System.out.println("--------��ü ��ǰ----------");
										for(int i=0;i<manager.getManageSize();i++) {//���� ������ ��ȣ �˷��ֱ�
											System.out.println("-----"+(i+1)+"-----");
					                        System.out.println("��ǰ ī�װ�:"+ manager.goodsArr(i).getCategory());
					                        System.out.println("��ǰ�� :"+manager.goodsArr(i).getName());
					                        System.out.println("��ǰ ���� :"+manager.goodsArr(i).getPrice());
					                        System.out.println("��ǰ ��� :"+manager.goodsArr(i).getStock());
										}
										{
											try {
												System.out.printf("������ ��ǰ �̸��� �Է��ϼ���:");
												changeName=scan.next();
												index=manager.findGoodsIndex(changeName);//�̸��� �ε��� ã���ֱ�
											}
											catch (Exception e) { // ã�� ��ǰ��  ���� ���
						    					String msg = e.getMessage();
						    					System.out.println(msg);
						    				}
												{//��ǰ ���� ���
													System.out.println("-----������ ��ǰ ������-----");
													System.out.println("-----"+(index+1)+"-----");
							                        System.out.println("��ǰ ī�װ�:"+ manager.goodsArr(index).getCategory());
							                        System.out.println("��ǰ�� :"+manager.goodsArr(index).getName());
							                        System.out.println("��ǰ ���� :"+manager.goodsArr(index).getPrice());
							                        System.out.println("��ǰ ��� :"+manager.goodsArr(index).getStock());
												}
												//��ǰ ����
												System.out.println("-------------------------");
												System.out.println("��ǰ��ȣ: ["+(index+1)+"]\n");
												//������ ��ǰ�� ���� ���� �Է�
												System.out.printf("������ ��ǰ ī�װ��� �Է��ϼ���:");
												String change_cate=scan.next();
												System.out.printf("������ ��ǰ �̸��� �Է��ϼ���:");
												String change_n=scan.next();
												System.out.printf("������ ��ǰ ������ �Է��ϼ���:");
												int change_p=scan.nextInt();
												scan.nextLine();
												System.out.printf("������ ��ǰ ������ �Է��ϼ���:");
												int change_s=scan.nextInt();
												scan.nextLine();
												
												Goods change_newGoods=new Goods(change_cate,change_n,change_p,change_s,codeCount);//��ǰ ����
												try{
													manager.changeGoods(change_newGoods,index);
												}
												catch(Exception e) {//��ǰ ���� ���� ����
													String msg=e.getMessage();
													System.out.println(msg);
												}
												
												System.out.println("===������ ��ǰ ��Ȳ===");
												//������ �����͸� ����Ѵ�.
												System.out.println("-----"+(index+1)+"-----");
						                        System.out.println("��ǰ ī�װ�:"+ manager.goodsArr(index).getCategory());
						                        System.out.println("��ǰ�� :"+manager.goodsArr(index).getName());
						                        System.out.println("��ǰ ���� :"+manager.goodsArr(index).getPrice());
						                        System.out.println("��ǰ ��� :"+manager.goodsArr(index).getStock());
										}
									}
									break;
								case 3://���
									if(manager.getManageSize()==0)//��ǰ �ϳ��� ���� ��
				    				{
										System.out.println("�ع�ǰ�� �ϳ��� �����ϴ�.��");
										System.out.println("�ع�ǰ ������ �����ϵ��� �ϰڽ��ϴ�.��");
										break;
				    				}
					    			else {
					    				for(int i=0;i<manager.getManageSize();i++) {
					    					System.out.println("====��ü ��ǰ====");
					    					System.out.println("-----"+(i+1)+"-----");
					                        System.out.println("��ǰ ī�װ�:"+ manager.goodsArr(i).getCategory());
					                        System.out.println("��ǰ�� :"+manager.goodsArr(i).getName());
					                        System.out.println("��ǰ ���� :"+manager.goodsArr(i).getPrice());
					                        System.out.println("��ǰ ��� :"+manager.goodsArr(i).getStock());
					    				}
					    				
					    			}
									break;

								case 4://����
									int deletIndex=0;//������ ��ǰ�� �ε���
									if(manager.getManageSize()==0||manager.getManageSize()<0) {//��ǰ�� ���� ��
										System.out.println("�ع�ǰ�� �ϳ��� �����ϴ�.��");
										System.out.println("�ع�ǰ ������ �����ϵ��� �ϰڽ��ϴ�.��");
										break;
									}
									else {//��ǰ ���� ��
											try {
												System.out.println("�ʿ� ���� ��ǰ�� �����Ͻðڽ��ϱ�?������1 �ƴϸ�0");
												int delete=scan.nextInt();
												scan.nextLine();
												if(delete==1) {//����
													System.out.println("��ǰ ������ �����ϵ��� �ϰڽ��ϴ�.");
													for(int i=0;i<manager.getManageSize();i++) {//�˻� ������ ��ǰ �˷��ֱ�
														System.out.println("---���� ������ ��ǰ ���---");
														System.out.println("-----"+(i+1)+"-----");
								                        System.out.println("��ǰ ī�װ�:"+ manager.goodsArr(i).getCategory());
								                        System.out.println("��ǰ�� :"+manager.goodsArr(i).getName());
								                        System.out.println("��ǰ ���� :"+manager.goodsArr(i).getPrice());
								                        System.out.println("��ǰ ��� :"+manager.goodsArr(i).getStock());
														
													}
													System.out.printf("������ ��ǰ�� �Է����ּ���:");
													String deleteName=scan.next();
													try {
														deletIndex=manager.findGoodsIndex(deleteName);//������ ��ǰ�� �ε����� ã��
														System.out.println("��ǰ�� �����ϵ��� �ϰڽ��ϴ�.");
														manager.deleteGoods(deletIndex);//��ǰ ����
														System.out.println("��ǰ�� �����Ǿ����ϴ�.");
													}
													catch(Exception e) {//ã�� ��ǰ�� ���� ���
														String msg=e.getMessage();
														System.out.println(msg);
													}
													break;
												
												}
												else if(delete==0) {//���� ����
													System.out.println("���α׷��� �����ϵ��� �ϰڽ��ϴ�.");break;
												}
											}
											catch(java.util.InputMismatchException e){ //��ǰ�� ������ ������ �߸� �Է����� ���
												System.out.println("�߸��� �Է��Դϴ�.");
												scan.nextLine(); //Ű���� ���� �ذ�
											}

									}
									break;
								case 5://��ǰ �˻�
									String selectGoodsName=null;
									int selectGoodsIndex=0;
									if(manager.getManageSize()==0||manager.getManageSize()<0) {//��ǰ�� ���� ��
										System.out.println("�ع�ǰ�� �ϳ��� �����ϴ�.��");
										System.out.println("�ع�ǰ �˻��� �����ϵ��� �ϰڽ��ϴ�.��");
										break;
									}
									else{
										System.out.println("��ǰ �˻��� �����ϵ��� �ϰڽ��ϴ�.");
										System.out.printf("��ǰ���� �Է����ּ���:");
										selectGoodsName=scan.next();
										try {
											selectGoodsIndex = manager.findGoodsIndex(selectGoodsName); // ��ǰ�� �ε���
											System.out.println("-----"+(selectGoodsIndex+1)+"-----");
					                        System.out.println("��ǰ ī�װ�:"+ manager.goodsArr(selectGoodsIndex).getCategory());
					                        System.out.println("��ǰ�� :"+manager.goodsArr(selectGoodsIndex).getName());
					                        System.out.println("��ǰ ���� :"+manager.goodsArr(selectGoodsIndex).getPrice());
					                        System.out.println("��ǰ ��� :"+manager.goodsArr(selectGoodsIndex).getStock());
										
										}
										catch (Exception e) { // ã�� ��ǰ��  ���� ���
					    					String msg = e.getMessage();
					    					System.out.println(msg);
					    				}
									}
									break;
								case 6://������ ����
									System.out.println("##������ �� ����##");
									System.out.println("������ �� ����:"+totalSales);
									break;
								case 7://����
									ObjectOutputStream out=null;
									try {
										//fOut�� information.data���� ����
										//FileOutputStream fOut = new FileOutputStream("information.data");
										//out�� fOut����
										out = new ObjectOutputStream(new FileOutputStream("information.data"));
										
										//out�� ��ü�� ���α׷� ���� ������ ����
										manager.saveFile(out);
										System.out.println("�����Ͱ� ����Ǿ����ϴ�.");
									}
									catch(IOException e) {	//���Ϸ� ����� ����� �̷������ �ʴ� ���
										System.out.println("���Ϸ� ����� �� �����ϴ�.");
									}
									catch(Exception e) {	//����� �ϴ� �������� ������ �߻��� ���
										System.out.println("���忡 �����߽��ϴ�.");
									}
									finally {
										try {
											out.close();
											}
										catch (Exception e){
											
										}

									}
								case 0://����
									System.out.println("���α׷��� �����ϵ��� �ϰڽ��ϴ�.");break;
								default:
									System.out.println("�ٽ� �Է��ϼ��� ");break;
								}
							}
							catch(java.util.InputMismatchException e){ //��ǰ�� ������ ������ �߸� �Է����� ���
								System.out.println("�߸��� �Է��Դϴ�.");
								scan.nextLine(); //Ű���� ���� �ذ�
							}
					}


			}
			catch(java.util.InputMismatchException e){//�߸� �Է����� ��
				System.out.println("�߸��� �Է��Դϴ�.");
				scan.nextLine(); //Ű���� ���� �ذ�
			}
	}

	}
	
}