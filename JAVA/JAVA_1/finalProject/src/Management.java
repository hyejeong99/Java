
//������ Ŭ���� ���� Management class
public class Management{
	private int manageSize=0;//�迭�� ����ִ� ��ü ����
	private Goods goods[]=new Goods[100];//��ǰ �迭 ����
	
	
	public void insertGoods(Goods newGoods) throws Exception { // ��ǰ ����Ʈ�� ��ǰ ��ü ����	
	//	if(manageSize==goods.length)//���� ������ ��ǰ �� �ʰ����� �� �ͼ���
	//		throw new Exception("�ִ� ��ǰ ���� �ʰ��߽��ϴ�.");
		goods[manageSize++]=newGoods;	// ��ü ��ǰ ���� �� ����
	}
	public void changeGoods(Goods newGoods,int change_num) {//��ǰ ����
		goods[change_num]=newGoods;
	}
	public String printGoods(int i) {//��ǰ ��� ���
		return "��ǰ ī�װ�: "+ goods[i].getCategory()+" ��ǰ�� : "+goods[i].getName()+" ��ǰ ���� : "+goods[i].getPrice()+" ��ǰ ��� : "+goods[i].getStock();

	}
	//manageSize������ ������
	int getManageSize() {//������
		return manageSize;
	}
	void setManageSize(int size) {//������
		manageSize=size;
	}
    public int findGoodsIndex(String goodsName) throws Exception{//������ �ε��� ã��
    		int goodsIndex=0;//�ε���
    		boolean find=false;//��ǰ ã�Ҵ���
    		for(int i=0;i<manageSize;i++) {
    			if(goodsName.equals(goods[i].getName())) {
    				goodsIndex=i;
    				find=true; //��ǰ ã��
    				System.out.println("��ǰ�� ã�ҽ��ϴ�!");
    				break;
    			}
    		}
    		if(find==false) {
    			throw new Exception("ã�� ��ǰ�� �����ϴ�.");//ã�� ��ǰ�� ���� ��
    		}
    		return goodsIndex;
	}


	public void  deleteGoods(int index) {//��ǰ ����
		System.out.println("��ǰ�� �����ϵ��� �ϰڽ��ϴ�.");
		for(int i=index;i<manageSize;i++) {
			goods[i]=goods[i+1];//��ĭ�� �б�
		}
		manageSize--;
		////�����ϱ�
		System.out.println("��ǰ ���� �Ϸ�!");
	}
	public Goods[] findGoodsCategory(String categoryName)throws Exception{//ī�װ� ã����
		boolean find = false; //��з� ã�Ҵ���
		Goods findGoods[]=new Goods[manageSize]; //��ȯ�� �迭
		for(int i=0;i<manageSize;i++) {
			if(categoryName.equals(goods[i].getCategory())) {//ã�� ��з�=��ǰ ��з�
				findGoods[i]=goods[i]; //ã�°Ͱ� ����
				find=true;//ã����
				System.out.println("ī�װ��� ã�ҽ��ϴ�!");
			}
		}
		if(find==false) {//��ã���� ��
			throw new Exception("ã�� ī�װ��� �����ϴ�.");			
		}
		return findGoods; //�迭 ��ȯ
	}
	int totalSales=0;//������ �� ����
	public int sellEstimate(int index, int sellCount) {//���� ������ ���� ã���ִ� �Լ�
		int payment=0;
		System.out.println("----���� ������ ��ǰ ����----");
		System.out.println("��ǰ ī�װ�:"+goods[index].getCategory());
		System.out.println("��ǰ��:"+goods[index].getName());
		System.out.println("��ǰ ����:"+goods[index].getPrice());
		System.out.println("��ǰ ���:"+goods[index].getStock());
		if(sellCount>goods[index].getStock()) {
			System.out.println("���� "+goods[index].getStock()+"���Դϴ�.");
			System.out.println("��ǰ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.");
		}
		else {
			payment=goods[index].getPrice()*sellCount;
			System.out.println("���� �ݾ�:"+payment);
		}
		return payment;
	}
	public int sell(int index, int sellCount) {//���Ÿ� ���ϴ� �Լ�
		
		
		if(goods[index].getStock()==0||sellCount>goods[index].getStock()) {//��� ���� ��
			System.out.println("��� �����ؼ� ���Ÿ� ������ �� �����ϴ�.");
		}
		else {
			
			//int payment=0;//�����ؾߵ� �ݾ�
			System.out.println("-----���� ����:"+sellCount+" ��ŭ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.----");
			int payment=(goods[index].getPrice()*sellCount);
			int subCount=goods[index].getStock()-sellCount;
			//sales+=payment;
			//int sales=payment;
			totalSales+=payment;//������ �� ����
			//totalSales+=sales;
			System.out.println("���� �� ��ǰ ����:"+subCount);
			goods[index].setStock(subCount);
			System.out.println("���� �ݾ�:"+payment);
			System.out.println("���� �Ϸ�!");
			System.out.println("������ �� ����:"+totalSales);
			if(subCount==0) {//�ִ� ��� ��� ��ǰ�� �������� ��
				System.out.println("��� �����Ǿ� ��ǰ�� �����ϵ��� �ϰڽ��ϴ�.");
				deleteGoods(index);//��ǰ ����
			}
		}
		return totalSales; 
	}
}

