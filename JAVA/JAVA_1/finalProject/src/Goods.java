//������ Ŭ���� ���� Goods class
public class Goods {
	
	private String category; //��з� �̸�
	private int code=0; //��ǰ��ȣ �ο�
	private String name; //��ǰ��
	private int price; //����
	private int stock;//��� ����
	
	
	public Goods(String cate, String n, int p, int s) {//������
		this.category=cate;
		this.name=n;
		this.price=p;
		this.stock=s;
		code++;//�ڵ� ����
	}
	//������ ������ �Լ�
	String getCategory() {
		return category;
	}
	void setCategory(String cate) {
		category=cate;
	}
	String getName() {
		return name;
	}
	void setName(String n) {
		name=n;
	}
	int getPrice() {
		return price;
	}
	void setPrice(int p) {
		price=p;
	}
	int getStock() {
		return stock;
	}
	void setStock(int s) {
		stock=s;
	}
	int getCode() {
		return code;
	}
	void setCode(int c) {
		code=c;
	}
	//��� ���ϰ� ���� ����� �ϴ� �Լ���
	void addStock(int s)  //��� ����
	{
		stock+=s;
	}
	void substractStock(int s) //��� ��
	{
		
		if(stock>=s)
		{
			stock-=s;
			System.out.println("��� �����ϴ�.");
		}
		else
		{//������ ��� �ִ� ����� ���� ��
			if(stock==0)
			{//��� ���� ��
				System.out.println("��� �����ϴ�.");
				
			}
			System.out.println("��� ���ڶ��ϴ�.");
			
		}
	}
	
}
