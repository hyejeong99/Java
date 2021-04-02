import java.io.*;
import java.util.*;
public class Management {
	private int totalSale=0, goodsCount=0;//����,��ǰ ȣ�� Ƚ��/��ǰ��ȣ
	private LinkedList<Goods> goods=new LinkedList<Goods>();;//�迭�� LinkedList��
	private int manageSize=goods.size();//�迭�� ����ִ� ��ü ����
	Management(ObjectInputStream in)throws Exception{//������ �ҷ�����
		try {//���� �о����
			in=new ObjectInputStream(new FileInputStream("information.data"));
			//����Ǿ��ִ� �����͸� �ҷ��� ���α׷��� ����
			readFile(in);
		}
		catch(FileNotFoundException e) {	//������ �������� �ʴ� ���
			throw new Exception("�������� �ʴ� �����Դϴ�.");
		}
		catch(IOException e) {	//�о�� �� ���� ������ ���
			throw new Exception("���Ϸ� ����� �� �����ϴ�.");
		}
		catch(Exception e) {	//�о�� �� ���� ������ ���
			throw new Exception("������ �߻��߽��ϴ�.");
		}
		finally {
			try {
				in.close();
				}
			catch (Exception e){
			}
		}
	}
	Management(){//������ ���� ������ �Լ�
	}
	//������, ������ �Լ�
	public void setTotalSale(int totalSale) {
		this.totalSale=totalSale;
	}
	public int getTotalSale() {
		return totalSale;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount=goodsCount;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	int getManageSize() {//������
		return manageSize;
	}
	void setManageSize(int size) {//������
		manageSize=size;
	}
	public Goods goodsArr(int i) throws Exception{//����� ��ü ����
		return goods.get(i);
	}
	//��ǰ ���
	public void insertGoods(Goods newGoods) throws Exception { // ��ǰ ����Ʈ�� ��ǰ ��ü ����	
		try{
			goods.add(newGoods);
		}
		catch(java.lang.NumberFormatException e1) {
			throw new Exception("���ڷ� �Է����ּ���.");
		}
		catch(IndexOutOfBoundsException e) {//����ó��
			throw new Exception("��ǰ ���� ���� ������ �߻��߽��ϴ�.");
		}
	}
	//��ǰ ����
	public void deleteGoods(int index) throws Exception{//��ǰ ����
		try{
			goods.remove(index);
		}
		catch(IndexOutOfBoundsException e) {//�ε��� ���� ����� ��
			throw new Exception("���� ������ �ε��� ������ ������ϴ�.");
		}
	}
	//��ǰ �ε��� ��ȯ
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
	//ī�װ� ã��
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
		else return goods.get(index).getStock();//���� ������ ��ǰ ���
	}
	//����
	public int sell(int index, int sellCount) throws Exception{//���Ÿ� ���ϴ� �Լ�
		int payment=0;//���� �ݾ�
		try {
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
		}catch(IndexOutOfBoundsException e) {
			throw new Exception("�ε��� ����");
		}
		
	}//����
	void saveFile(ObjectOutputStream out) throws Exception{	//�����͸� ���Ϸ� ����ϴ� �޼ҵ�
		try{
			out.writeInt(totalSale);	//���� �� ���Ⱚ ���
			out.writeInt(getManageSize());	//�迭�� ����� ��ü�� ���� ���� ������ �ִ� manageSize ���
			
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
	}//�о����
	void readFile(ObjectInputStream in) throws Exception{	//object���Ͽ��� �����͸� �о���� �޼ҵ�
		try {
			totalSale = in.readInt();	//���� �� ���Ⱚ �о�� ����
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
		 catch (ClassNotFoundException e) {//Ŭ������ �������� ���� ��
			 System.out.println("�ش� Ŭ������ �������� �ʽ��ϴ�.");
		 }
		catch(Exception e) { //����ó��
			throw new Exception("���� �б� ����");
		}
	}
	
}
