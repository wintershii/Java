package com.lzw;

public class OuterClass {
	innerClass in = new innerClass();		//���ⲿ��ʵ�����ڲ����������
	
	public void ouf() {					//���ⲿ���е����ڲ��෽��
		in.inf();
	}
	
	class innerClass{
		innerClass(){				//�ڲ��๹�췽��
			
		}
		public void inf() {		//�ڲ����Ա����
			
		}
		int y = 0;			//�����ڲ����Ա����
		
	}
	
	public innerClass doit() {			//�ⲿ�෽��������ֵΪ�ڲ�������
		in.y = 4;					
		
		return in;		//�����ڲ�������
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OuterClass out = new OuterClass();
		OuterClass.innerClass in = out.doit();
		OuterClass.innerClass in2 = out.new innerClass();
		System.out.println(in.y);
	}

}
