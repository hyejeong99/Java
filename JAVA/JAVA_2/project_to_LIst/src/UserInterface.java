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
		Management manager=new Management();
		Scanner scan=new Scanner(System.in);
		try {
			//fIn�� information.txt���� ����
			FileInputStream fIn = new FileInputStream("information.txt");
			//in�� fIn����
			DataInputStream in = new DataInputStream(fIn);
			
			//����Ǿ��ִ� �����͸� �ҷ��� ���α׷��� ����
			manager.readFile(in);
			in.close();
		}
		catch(FileNotFoundException e) {	//������ �������� �ʴ� ���
			System.out.println("�������� �ʴ� �����Դϴ�.");
		}
		catch(IOException e) {	//�о�� �� ���� ������ ���
			System.out.println("���Ϸ� ����� �� �����ϴ�.");
		}
		catch(Exception e) {	//�о���� �������� ������ �߻��� ���
			System.out.println("�о���⿡ �����߽��ϴ�.");
		}
		int totalSales=0;//������ �� ����
		while(true){
			Menu.mainMenu();//���� �޴��� �����ش�
			try {
				int answer; //������� ������ ������ ���� answer(int��)
				String goodsName; //������� ������ ������ ���� goodsName(String��)
				char answerChar; //������� ������ ������ ���� answerChar(char��)
				Goods[] categoryItems=null; //���� ī�װ��� ��ǰ�� �����ϱ� ���� ��ü �迭
				try {
					answer=scan.nextInt();	//Ű���� ���۸� ���� ���� nextLine()
				}
				catch(java.util.InputMismatchException e){	//����ڰ� ������ �ƴ� �ٸ� ���� �Է��� ���
					System.out.print("���ڸ� �Է��ϼ���.");	//���ڸ� �Է��϶�� �ȳ� ���� ���
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
							answer = scan.nextInt();
							scan.nextLine();
						}catch(java.util.InputMismatchException e) {	//���ڰ� �ƴ� ���� �Է��� ���
							System.out.print("���ڸ� �Է��ϼ���.");
						}
						if(answer==1) {//��ǰ ���Ÿ� ������ ���
							System.out.println("��ǰ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.");
							System.out.println("--------��ü ��ǰ----------");
							for(int i=0;i<manager.getManageSize();i++) {//���� ������ ��ȣ �˷��ֱ�
								manager.printGoods(i);
							}
							try {
								System.out.printf("������ ��ǰ �̸�:");
								goodsName=scan.nextLine();
								int index=manager.findGoodsIndex(goodsName);//��ǰ�� �ε���
								System.out.println("((((((������ ��ǰ ����))))))");
								manager.printGoods(index);
								try {// ī�װ��� ��ǰ ã��
			    					System.out.println("��ǰ�� �����Ͻðڽ��ϱ�?");
			    					System.out.printf("�����ϸ�1, ���ž��ϸ�0�� �Է����ּ���:");
		    						int buy=scan.nextInt();scan.nextLine();
			    						if(buy==1) {//������ ��
			    							System.out.printf("���� ������ �Է����ּ���:");
					    					int sellCount=scan.nextInt();scan.nextLine();
					    					if(sellCount>manager.sellEstimate(index, sellCount)) {//���� ���ϴ� ����>�����ϴ� ���
					    						throw new Exception("��� �����մϴ�.");
					    						
					    					}
					    					else {
					    						System.out.println("���� �� ��ǰ ����:"+manager.sellEstimate(index, sellCount));
				    							System.out.println("���� ����:"+sellCount+" ��ŭ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.");
				    							int payment=manager.sell(index, sellCount);//payment=���ұݾ�
				    							totalSales+=payment;
				    							System.out.println("���� �ݾ�:"+payment);
				    							System.out.println("������ �� ����:"+totalSales);
				    							System.out.println("���� �Ϸ�!");
					    					}	
			    						}
			    						else if(buy==0) {//���� ���� ��
			    							System.out.println("�������� �ʾ� ���α׷��� �����ϵ��� �ϰڽ��ϴ�.");
			    						}

								}
								catch (java.util.InputMismatchException e) { //��ȣ�� �߸� �Է����� ��
									System.out.println("�߸��� �Է��Դϴ�.");
									scan.nextLine(); //Ű���� ���� �ذ�
			    				}
							}
							catch(Exception e) { // �ε����� ã�� ���� ���
		    					String msg = e.getMessage();
		    					System.out.println(msg);
		    				}
						}
					}
					break;
				case 2://������ ���
					Menu.manageMenu();//������ �޴��� �����ش�.
							try {
								int manageMenu=scan.nextInt();
								switch(manageMenu){//������ �޴�
								case 1://��ǰ ���
									System.out.println("��ǰ�� ����ϵ��� �ϰڽ��ϴ�.");
									System.out.println("----------------------");
									System.out.println("====��ϵ� ��ǰ����� �Է����ּ���====");//��ǰ ���							
									try {
										//����� ��ǰ ���� �Է�
										System.out.printf("��ǰ ī�װ��� �Է��ϼ���:");
										String cate=scan.next();
										System.out.printf("��ǰ �̸��� �Է��ϼ���:");
										String name=scan.next();
										System.out.printf("��ǰ ������ �Է��ϼ���:");
										int price=scan.nextInt();scan.nextLine();
										System.out.printf("��ǰ ������ �Է��ϼ���:");
										int stock=scan.nextInt();	
										Goods newGoods = new Goods(cate,name,price,stock); // Goods ��ü ����
										try {
											manager.insertGoods(newGoods); // ���ο� ��ǰ ����
						                	scan.nextLine();
						                }
						                catch (Exception e) { // ��ǰ ���� �ʰ�
						                	String msg = e.getMessage();
						                	System.out.println("��ǰ ������ �ʰ��߽��ϴ�.");
						                }
									}
								catch(java.util.InputMismatchException e) {
					    			System.out.println("�߸��� �Է��Դϴ�.");
					    			scan.nextLine();
					    		}
								break;
									
								case 2://����
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
											manager.printGoods(i);
										}
										{
											try {
												System.out.printf("������ ��ǰ �̸��� �Է��ϼ���:");
												String changeName=scan.next();
												int index=manager.findGoodsIndex(changeName);//�̸��� �ε��� ã���ֱ�
												{//��ǰ ���� ���
													System.out.println("-----������ ��ǰ ������-----");
													manager.printGoods(index);
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
												
												Goods change_newGoods=new Goods(change_cate,change_n,change_p,change_s);//��ǰ ����
												manager.changeGoods(change_newGoods,index);
												
												System.out.println("===������ ��ǰ ��Ȳ===");
												//������ �����͸� ����Ѵ�.
												manager.printGoods(index);
				
												
											}
											catch (Exception e) { // ã�� ��ǰ��  ���� ���
						    					String msg = e.getMessage();
						    					System.out.println("ã�� ��ǰ�� �����ϴ�.");
						    				}
											

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
					    					manager.printGoods(i);
					    				}
					    				
					    			}
									break;

								case 4://����
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
														manager.printGoods(i);
														
													}
													System.out.printf("������ ��ǰ�� �Է����ּ���:");
													String deleteName=scan.next();
													try {
														int deletIndex=manager.findGoodsIndex(deleteName);//������ ��ǰ�� �ε����� ã��
														System.out.println("��ǰ�� �����ϵ��� �ϰڽ��ϴ�.");
														manager.deleteGoods(deletIndex);//��ǰ ����
														System.out.println("��ǰ�� �����Ǿ����ϴ�.");
													}
													catch(Exception e) {//ã�� ��ǰ�� ���� ���
														String msg=e.getMessage();
														System.out.println("ã�� ��ǰ�� �����ϴ�.");
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
									if(manager.getManageSize()==0||manager.getManageSize()<0) {//��ǰ�� ���� ��
										System.out.println("�ع�ǰ�� �ϳ��� �����ϴ�.��");
										System.out.println("�ع�ǰ �˻��� �����ϵ��� �ϰڽ��ϴ�.��");
										break;
									}
									else{
										System.out.println("��ǰ �˻��� �����ϵ��� �ϰڽ��ϴ�.");
										System.out.println("1:��ǰ ī�װ��� �˻�  2:��ǰ������ �˻�");
										int select=scan.nextInt();scan.nextLine();
										if(select==1){///////////////////////////****�ڲ� ������
											for(int i=0;i<manager.getManageSize();i++) {//�˻� ������ ��ǰ �˷��ֱ�
												System.out.println("---�˻� ������ ��ǰ ���---");
												manager.printGoods(i);
											}
											System.out.printf("��ǰ ī�װ��� �Է����ּ���:");
											String category=scan.next();
											try {
												System.out.println("-------ī�װ� "+category+"�� ���ϴ� ��ǰ-------");
												Goods findgoods[]=manager.findGoodsCategory(category);
												for(int i=0;i<manager.getManageSize();i++) {//���
													if(manager.getManageSize()!=0) {//��ǰ ���� ���
														System.out.println("��ǰ��:"+findgoods[i].getName()); // ��ǰ ���
						    							System.out.println("��ǰ ����:"+findgoods[i].getPrice()); // ��ǰ ���
						    							System.out.println("��ǰ ���:"+findgoods[i].getStock()); // ��ǰ ���
													}
													else {
														System.out.println("��ǰ�� �������� �ʽ��ϴ�.");
													}
												}
											}
											catch(Exception e) { // ��з��� ã�� ���� ���
						    					String msg = e.getMessage();
						    					System.out.println("ã�� ��ǰ�� �����ϴ�.");
						    				}
						    				
										}
										if(select==2) {
											for(int i=0;i<manager.getManageSize();i++) {//�˻� ������ ��ǰ �˷��ֱ�
												System.out.println("---�˻� ������ ��ǰ ���---");
												manager.printGoods(i);
												
											}
											System.out.printf("��ǰ���� �Է����ּ���:");
											goodsName=scan.nextLine();
											try {
												int index = manager.findGoodsIndex(goodsName); // ��ǰ�� �ε���
												manager.printGoods(index);
											
											}
											catch (Exception e) { // ã�� ��ǰ��  ���� ���
						    					String msg = e.getMessage();
						    					System.out.println(msg);
						    				}
						    				
										}
									}
									break;
								case 6:
									try {
										//fOut�� information.txt���� ����
										FileOutputStream fOut = new FileOutputStream("information.txt");
										//out�� fOut����
										DataOutputStream out = new DataOutputStream(fOut);
										
										//out�� ��ü�� ���α׷� ���� ������ ����
										manager.saveFile(out);
										out.close();
									}
									catch(IOException e) {	//���Ϸ� ����� ����� �̷������ �ʴ� ���
										System.out.println("���Ϸ� ����� �� �����ϴ�.");
									}
									catch(Exception e) {	//����� �ϴ� �������� ������ �߻��� ���
										System.out.println("���忡 �����߽��ϴ�.");
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