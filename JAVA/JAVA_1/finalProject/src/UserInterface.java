/*
 * ���α׷�:������ ���� ���α׷�
 * �ۼ���:������
 * �ۼ���:2019.06.01
 */
/*
 * �ΰ� �̻� ����ϸ� ����� �ȵ�
 * 1�� ���->����->���->�ڵ��ȣ 2�� ��
 * */
import java.util.*;

public class UserInterface{
	public static void main(String args[])throws Exception{
		Scanner scan=new Scanner(System.in);
		Management manager=new Management();//�Ŵ��� ��ü ����
		while(true){
			Menu.mainMenu();//���� �޴��� �����ش�
			try {
				
				int mainMenu = scan.nextInt();scan.nextLine();//��ȣ�� �Է¹޴´�.
				switch(mainMenu) {//��ȣ�� ���� ����
				case 1://����� ���
					if(manager.getManageSize()==0) {//��ϵ� ��ǰ�� ���� ��
						System.out.println("----��ǰ�� �ϳ��� �����ϴ�.----");
						System.out.println("��ǰ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.");
					}
					else {
						System.out.println("---����� ���---");
						System.out.println("��ǰ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.");
						System.out.println("--------��ü ��ǰ----------");
						for(int i=0;i<manager.getManageSize();i++) {//���� ������ ��ȣ �˷��ֱ�
							System.out.println(manager.printGoods(i));//��ü ��ǰ ���
						}
						System.out.printf("��ǰ �̸�:");
						String goodsName=scan.nextLine();
						try {// ī�װ��� ��ǰ ã��
	    					int index=manager.findGoodsIndex(goodsName);//��ǰ�� �ε���
	    					
	    					System.out.printf("���� ������ �Է����ּ���:");
	    					int sellCount=scan.nextInt();scan.nextLine();
	    					manager.sellEstimate(index, sellCount);
	    					System.out.println("��ǰ�� �����Ͻðڽ��ϱ�?");
	    					try {
	    						System.out.printf("�����ϸ�1, ���ž��ϸ�0�� �Է����ּ���:");
	    						int buy=scan.nextInt();scan.nextLine();
	    						if(buy==1) {//������ ��
	    							manager.sell(index, sellCount);
	    						}
	    						else if(buy==0) {//���� ���� ��
	    							System.out.println("�������� �ʾ� ���α׷��� �����ϵ��� �ϰڽ��ϴ�.");
	    						}
	    					}
	    					catch(java.util.InputMismatchException e){//�߸� �Է����� ��
	    						System.out.println("�޴� ��ȣ�� �߸� �Է��߽��ϴ�.");
	    						scan.nextLine();
	    					}
	    					
						}
						catch (Exception e) { // ã�� ��ǰ��  ���� ���
	    					String msg = e.getMessage();
	    					System.out.println("ã�� ��ǰ�� �����ϴ�.");
	    				}
					}
				case 2://������ ���
					Menu.manageMenu();//������ �޴��� �����ش�.
							try {
								int manageMenu=scan.nextInt();//�޴��� �Է¹޴´�.
								scan.nextLine();
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
										System.out.println("----��ǰ�� �ϳ��� �����ϴ�.----");
										System.out.println("��ǰ ������ �����ϵ��� �ϰڽ��ϴ�.");
										break;
									}
									else {//��ǰ�� ���� ��
										System.out.println("��ǰ�� �����ϵ��� �ϰڽ��ϴ�.");
										System.out.println("--------��ü ��ǰ----------");
										for(int i=0;i<manager.getManageSize();i++) {//���� ������ ��ȣ �˷��ֱ�
											System.out.println(manager.printGoods(i));//��ü ��ǰ ���
										}
										{
											try {
												System.out.printf("������ ��ǰ �̸��� �Է��ϼ���:");
												String changeName=scan.next();
												int index=manager.findGoodsIndex(changeName);//�̸��� �ε��� ã���ֱ�
												{//��ǰ ���� ���
													System.out.println("-----������ ��ǰ ������-----");
													System.out.println(manager.printGoods(index));
												}
												//��ǰ ����
												System.out.println("-------------------------");
												System.out.println("��ǰ��ȣ:"+index+"\n");
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
													System.out.println(manager.printGoods(index));
											}
											catch (Exception e) { // ã�� ��ǰ��  ���� ���
						    					String msg = e.getMessage();
						    					System.out.println("ã�� ��ǰ�� �����ϴ�.");
						    				}
											break;

										}
									}
								case 3://���
									if(manager.getManageSize()==0)//��ǰ �ϳ��� ���� ��
				    				{
										System.out.println("----��ǰ�� �ϳ��� �����ϴ�.----");
										System.out.println("��ǰ ������ �����ϵ��� �ϰڽ��ϴ�.");
				    				}
					    			else {
					    				System.out.println("====��ǰ ���� ���====");
					    				for(int i=0;i<manager.getManageSize();i++) {
					    					System.out.println("["+(i+1)+"] "+manager.printGoods(i));
					    				}
					    			}
					    			break;

								case 4://����
									if(manager.getManageSize()==0||manager.getManageSize()<0) {//��ǰ�� ���� ��
										System.out.println("----��ǰ�� �ϳ��� �����ϴ�.----");
										System.out.println("��ǰ ������ �����ϵ��� �ϰڽ��ϴ�.");
										break;
									}
									else {//��ǰ ���� ��
											try {
												System.out.println("�ʿ� ���� ��ǰ�� �����Ͻðڽ��ϱ�?������1 �ƴϸ�0");
												int delete=scan.nextInt();
												scan.nextLine();
												if(delete==1) {//����
													System.out.println("��ǰ ������ �����ϵ��� �ϰڽ��ϴ�.");
													System.out.printf("������ ��ǰ�� �Է����ּ���:");
													String deleteName=scan.next();
													try {
														int deletIndex=manager.findGoodsIndex(deleteName);//������ ��ǰ�� �ε����� ã��
														manager.deleteGoods(deletIndex);//��ǰ ����
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
								case 5://��ǰ �˻�
									if(manager.getManageSize()==0||manager.getManageSize()<0) {//��ǰ�� ���� ��
										System.out.println("----��ǰ�� �ϳ��� �����ϴ�.----");
										System.out.println("��ǰ �˻��� �����ϵ��� �ϰڽ��ϴ�.");
										break;
									}
									else{
										System.out.println("��ǰ �˻��� �����ϵ��� �ϰڽ��ϴ�.");
										System.out.println("1:��ǰ ī�װ��� �˻�  2:��ǰ������ �˻�");
										int select=scan.nextInt();scan.nextLine();
										if(select==1){
											System.out.printf("��ǰ ī�װ��� �Է����ּ���:");
											String category=scan.next();
											try {
												System.out.println("-------ī�װ� "+category+"�� ���ϴ� ��ǰ-------");
												Goods findgoods[]=manager.findGoodsCategory(category);
												for(int i=0;i<manager.getManageSize();i++) {//���
													if(findgoods[i]!=null) {//��ǰ ���� ���
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
						    				break;
										}
										if(select==2) {
											System.out.printf("��ǰ���� �Է����ּ���:");
											String goodsName=scan.nextLine();
											try {
												int index = manager.findGoodsIndex(goodsName); // ��ǰ�� �ε���
						    					System.out.println(manager.printGoods(index)); // ��ǰ ���
											}
											catch (Exception e) { // ã�� ��ǰ��  ���� ���
						    					String msg = e.getMessage();
						    					System.out.println(msg);
						    				}
						    				break;
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
				System.out.println("�޴� ��ȣ�� �߸� �Է��߽��ϴ�.");
				scan.nextLine();
			}
	}

	}
	
}
