
public class TransferProperty {
	int i = 47;							//�����Ա����
	public void call() {				//�����Ա����
		System.out.println("����call()����");
		for(int i = 0; i < 3; i++) {
			System.out.print(i+" ");
			if(i == 2)
				System.out.print("\n");
		}
	}
	
	public TransferProperty() {				//���幹�췽��
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TransferProperty t1 = new TransferProperty();
		TransferProperty t2 = new TransferProperty();
		t2.i = 60;
		// ʹ�õ�һ��������ó�Ա����
		System.out.println("��һ��ʵ��������ñ���i�Ľ����"+t1.i++);
		t1.call();
		System.out.println(t1.i);
		// ʹ�õڶ���������ó�Ա����
		System.out.println("�ڶ���ʵ��������ñ���i�Ľ����"+t2.i);
		t2.call();
	}

}
