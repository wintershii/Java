package com.lzw;

class Parents{
	private final void doit() {
		System.out.println("����doit()");
	}
	final void doit2() {
		System.out.println("����doit2()");
	}
	public void doit3() {
		System.out.println("����doit3()");
	}
	
}

class Sub extends Parents{
	public final void doit() {
		System.out.println("����doit()");
	}
	
//	final void doit2() {				//final�������ɱ�����
//		System.out.println("����doit2()");
//	}
	
	public void doit3() {
		System.out.println("����doit3()");
	}
}

public class FinalMethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sub s = new Sub();			//ʵ��������
		s.doit();
		s.doit2();
		s.doit3();
		Parents p = s;			//����ת��
		//p.doit();    //���ܵ���private ����
		p.doit2();
		p.doit3();
		
		
	}

}
