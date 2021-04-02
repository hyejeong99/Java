import java.io.*;
import java.util.*;
import java.util.LinkedList;
//������ Ŭ���� ���� Management class
public class Management{
	private LinkedList<Goods>goods=new LinkedList<Goods>();//�迭�� LinkedList��
	private int manageSize=goods.size();//�迭�� ����ִ� ��ü ����
	private int count=0; //insertGoods�Լ��� ȣ��� Ƚ���� ��Ÿ���� �������ʵ�
	private int totalSales=0;
	
	public Goods goodsArr(int i) {
		return goods.get(i);
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
	//manageSize������ ������
	int getManageSize() {//������
		return goods.size();
	}
	void setManageSize(int size) {//������
		manageSize=size;
	}
    public int findGoodsIndex(String goodsName) throws Exception{//������ �ε��� ã��
    		int goodsIndex=-1;//�ε���
    		Iterator<Goods>iterator=goods.iterator();
    		while(iterator.hasNext()) {
    			Goods good=iterator.next();
    			goodsIndex++;//�ε��� ����
    			if(good.getName().equals(goodsName)) {//�Ķ���ͷ� ���� �̸��� ���� ��ǰ�̸�
    				return goodsIndex;
    			}
    		}
    		throw new Exception("ã�� ��ǰ�� �����ϴ�.");
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

		if(sellCount>goodsArr(index).getStock()) {
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
	void saveFile(DataOutputStream out) throws Exception{	//�����͸� ���Ϸ� ����ϴ� �޼ҵ�
		try{
			out.writeInt(totalSales);	//���� �� ���Ⱚ ���
			out.writeInt(manageSize);	//�迭�� ����� ��ü�� ���� ���� ������ �ִ� manageSize ���
			
			Iterator<Goods>iterator=goods.iterator();
			while(iterator.hasNext()) {
				Goods good=iterator.next();
				good.save(out);
			}
		}catch(IOException ioe) {	//����� ����� �̷������ ���� ��� �ͼ��� ������
			throw new Exception("���� ��� ����");
		}catch(Exception e) {//����
			throw new Exception("���� ���� ����");
		}
	}

	void readFile(DataInputStream in) throws Exception{	//data���Ͽ��� �����͸� �о���� �޼ҵ�
		try {
			totalSales = in.readInt();	//���� �� ���Ⱚ �о�� ����
			manageSize = in.readInt();	//�迭�� ����� ��ü�� ���� ���� ������ �ִ� manageSize �о�� ����
			for(int i=0; i<manageSize; i++) {	//for���� ���鼭 ��ü�� ������ �ִ� ������ �о�� ����	
				Goods newgoods=new Goods();
				newgoods.read(in);
				goods.add(newgoods);
			}
		}catch(EOFException fefo) { //������ ������ ���
			throw new Exception("���� ��");
		}
		catch(IOException ioe) { //������ ���� �� ���� ���
			throw new Exception("���� �б� ����");
		}
		catch(Exception e) { //����ó��
			throw new Exception("���� �б� ����");
		}
	}
}

