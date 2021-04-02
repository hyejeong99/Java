import java.io.*;
import java.util.*;
import java.util.LinkedList;
//������ Ŭ���� ���� Management class
public class Management{
	LinkedList<Goods>goods=new LinkedList<Goods>();//�迭�� LinkedList��
	private int manageSize=goods.size();//�迭�� ����ִ� ��ü ����
	private int count=0; //insertGoods�Լ��� ȣ��� Ƚ���� ��Ÿ���� �������ʵ�
	private int totalSales=0;
	
	LinkedList<Goods> getGoodsList() {//goods�� ��ȯ�ϴ� �޼ҵ�
		return goods;
	}
	public void insertGoods(Goods newGoods) throws Exception { // ��ǰ ����Ʈ�� ��ǰ ��ü ����	
		goods.add(newGoods);
		if(manageSize==100) {//���� ��ǰ�� 100���� ���� ��
			throw new Exception("�ִ� ��ǰ ���� �ʰ��߽��ϴ�.");
		}
		count++;
	}
	int getcount() {	//insertGoods�Լ��� ����� Ƚ���� ��ȯ���ִ� �޼ҵ�
		return count;
	}
	
	int getTotalSales() {	//�� ������� ��ȯ���ִ� �޼ҵ�
		return totalSales;
	}
	public void changeGoods(Goods newGoods,int change_num) {//��ǰ ����
		goods.set(change_num, newGoods);
	}
	public void printGoods(int i) {//��ǰ ��� ���
		try {
			System.out.println("-----"+(i+1)+"-----");
			System.out.println("��ǰ ī�װ�:"+ goods.get(i).getCategory());
			System.out.println("��ǰ�� :"+goods.get(i).getName());
			System.out.println("��ǰ ���� :"+goods.get(i).getPrice());
			System.out.println("��ǰ ��� :"+goods.get(i).getStock());
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("������ ������ϴ�.");
		}
	}
	//manageSize������ ������
	int getManageSize() {//������
		return goods.size();
	}
	void setManageSize(int size) {//������
		manageSize=size;
	}
    public int findGoodsIndex(String goodsName) throws Exception{//������ �ε��� ã��
    		int goodsIndex=0;//�ε���
    		boolean find=false;//��ǰ ã�Ҵ���
    		for(int i=0;i<manageSize;i++) {
    			if(goodsName.equals(goods.get(i).getName())) {
    				goodsIndex=i;
    				find=true; //��ǰ ã��
    				break;
    			}
    		}
    		if(find==false) {
    			throw new Exception("ã�� ��ǰ�� �����ϴ�.");//ã�� ��ǰ�� ���� ��
    		}
    		return goodsIndex;
	}


	public void deleteGoods(int index) {//��ǰ ����
		goods.remove(index);
	}
	public Goods[] findGoodsCategory(String categoryName)throws Exception{//ī�װ� ã����
		boolean find = false; //��з� ã�Ҵ���
		Goods findGoods[]=new Goods[manageSize]; //��ȯ�� �迭
		for(int i=0;i<manageSize;i++) {
			if(categoryName.equals(goods.get(i).getCategory())) {//ã�� ��з�=��ǰ ��з�
				findGoods[i]=goods.get(i); //ã�°Ͱ� ����
				find=true;//ã����
			}
		}
		if(find==false) {//��ã���� ��
			throw new Exception("ã�� ī�װ��� �����ϴ�.");			
		}
		return findGoods; //�迭 ��ȯ
	}
	public int sellEstimate(int index, int sellCount) throws Exception{//���� ������ ���� ã���ִ� �Լ�
		if(sellCount>goods.get(index).getStock()) {
			throw new Exception("���� "+goods.get(index).getStock()+"���Դϴ�."+"��ǰ ���Ÿ� �����ϵ��� �ϰڽ��ϴ�.");
		}
		return goods.get(index).getStock();//���� ������ ��ǰ ���
	}
	
	public int sell(int index, int sellCount) throws Exception{//���Ÿ� ���ϴ� �Լ�
		int payment=0;//���� �ݾ�
		if(sellCount>goods.get(index).getStock()) {//��� ���� ��
			throw new Exception("��� �����ؼ� ���Ÿ� ������ �� �����ϴ�.");
		}
		else {
			payment=(goods.get(index).getPrice()*sellCount);//���� �ݾ�
			int subCount=goods.get(index).getStock()-sellCount;//���� �� ��ǰ ����
			goods.get(index).setStock(subCount);
			if(subCount==0) {//��� �� ���� ��
				deleteGoods(index);//��ǰ ����
			}
			//totalSales+=payment;//�� �ݾ� ȯ��
			return payment; //�� �ݾ� ��ȯ
		}
		
	}
	void saveFile(DataOutputStream out) throws Exception{	//�����͸� txt���Ϸ� ����ϴ� �޼ҵ�
		try{
			out.writeInt(totalSales);	//���� �� ���Ⱚ ���
			//out.writeInt(count);	//���� ��ǰ ��ȣ ���� ������ �ִ� count �� ���
			out.writeInt(manageSize);	//�迭�� ����� ��ü�� ���� ���� ������ �ִ� manageSize ���
		      
			for(int i=0; i<manageSize; i++) {	//��ü�� ������ �ִ� ������ ���
				try {
					goods.get(i).save(out);
				}
				catch(Exception e) {
					throw new Exception("���⸦ �����߽��ϴ�.");
				}
			}
		}catch(IOException ioe) {	//����� ����� �̷������ ���� ��� �ͼ��� ������
			throw new Exception("��� ����");
		}
	}

	void readFile(DataInputStream in) throws Exception{	//txt���Ͽ��� �����͸� �о���� �޼ҵ�
		try {
			totalSales = in.readInt();	//���� �� ���Ⱚ �о�� ����
			//count = in.readInt();	//���� ��ǰ ��ȣ ���� ������ �ִ� count �о�� ����
			manageSize = in.readInt();	//�迭�� ����� ��ü�� ���� ���� ������ �ִ� manageSize �о�� ����
			LinkedList <Goods> g=null;
			for(int i=0; i<manageSize; i++) {	//for���� ���鼭 ��ü�� ������ �ִ� ������ �о�� ����	
				LinkedList<Goods> goo=new LinkedList<Goods>();//read�� ������ ������ Goods ��ü
				//goo.get(i).read(in);
				try {//��ü g�� goods�迭�� ����ֱ�
					goo.get(i).read(in);
				}
				catch(Exception e) {
					throw new Exception("�Է��� �����߽��ϴ�.");
				}
				//LinkedList<Goods>goods=new LinkedList<Goods>();	//goodsList�� ���ο� ��ü�� ����� �Ҵ�
				//goods.get(i).read(in);	//���� ������� ��ü�� ���� ���Ϸκ��� �о�� ����
			}
		}catch(Exception e) {	//�Է��� ����� �̷������ ���� ��� �ͼ��� ������
			throw new Exception("�Է� ����");
		}
	}
}

