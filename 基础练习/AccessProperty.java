
public class AccessProperty {
	static int i = 47;				//�����Ա����
	
	public void call() {			//�����Ա����
		System.out.println("����call()����");
		for(i = 0; i < 3; i++) {
			System.out.print(i+" ");
			if(i == 2)
				System.out.print("\n");
		}
	}
	
	public AccessProperty() {				//���幹�췽��
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccessProperty t1 = new AccessProperty();		//��������
		AccessProperty t2 = new AccessProperty();
		t2.i = 60;
		
		//ʹ�õ�һ��������ó�Ա����
		System.out.println("��һ��ʵ��������ñ���i�Ľ����"+t1.i++);
		t1.call();
		
		//ʹ�õڶ����������ó�Ա����
		System.out.println("�ڶ���ʵ��������ñ���i�Ľ����"+t2.i++);
		t2.call();
	}

}
