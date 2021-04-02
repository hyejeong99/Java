import java.io.*;
import java.util.*;
import java.util.LinkedList;
//������ Ŭ���� ���� Management class
public class Management{
	private LinkedList<Goods>goods=new LinkedList<Goods>();//�迭�� LinkedList��
	private int manageSize=goods.size();//�迭�� ����ִ� ��ü ����
	private int totalSales=0;
	
	Management(ObjectInputStream in) throws Exception{//������ �Լ�
		try {
			in=new ObjectInputStream(new FileInputStream("information.data"));
			//����Ǿ��ִ� �����͸� �ҷ��� ���α׷��� ����
			readFile(in);
			System.out.println("�����͸� �о�Խ��ϴ�.");
		}
		catch(FileNotFoundException e) {	//������ �������� �ʴ� ���
			System.out.println("�������� �ʴ� �����Դϴ�.");
		}
		catch(IOException e) {	//�о�� �� ���� ������ ���
			System.out.println("���Ϸ� ����� �� �����ϴ�.");
		}
		catch(Exception e) {
			throw new Exception("������ �о���� �� ������ �߻��߽��ϴ�.");
		}
		finally {
			try {
				in.close();
				}
			catch (Exception e){
				
			}
		}
	}
	public Goods goodsArr(int i) throws Exception{
		return goods.get(i);
	}
	public void insertGoods(Goods newGoods) throws Exception { // ��ǰ ����Ʈ�� ��ǰ ��ü ����	
		try{
			goods.add(newGoods);
		}
		catch(IndexOutOfBoundsException e) {//����ó��
			System.out.println("��ǰ ���� ���� ������ �߻��߽��ϴ�.");
		}
	}
	int getTotalSales() {	//�� ������� ��ȯ���ִ� �޼ҵ�
		return totalSales;
	}
	public void changeGoods(Goods newGoods,int change_num) throws Exception{//��ǰ ����
		try{
			goods.set(change_num, newGoods);
		}
		catch(IndexOutOfBoundsException e) {//�ε��� ����� ��
			System.out.println("��ǰ ���� ���� ������ �߻��߽��ϴ�.");
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


	public void deleteGoods(int index) throws Exception{//��ǰ ����
		try{
			goods.remove(index);
		}
		catch(IndexOutOfBoundsException e) {//�ε��� ���� ����� ��
			System.out.println("���� ������ �ε��� ������ ������ϴ�.");
		}
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
	void saveFile(ObjectOutputStream out) throws Exception{	//�����͸� ���Ϸ� ����ϴ� �޼ҵ�
		try{
			out.writeInt(totalSales);	//���� �� ���Ⱚ ���
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
	}

	void readFile(ObjectInputStream in) throws Exception{	//object���Ͽ��� �����͸� �о���� �޼ҵ�
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
		 catch (ClassNotFoundException e) {//Ŭ������ �������� ���� ��
			 System.out.println("�ش� Ŭ������ �������� �ʽ��ϴ�.");
		 }
		catch(Exception e) { //����ó��
			throw new Exception("���� �б� ����");
		}
	}
}

