package com.lzw;

interface OutInterface2{
	public void f();
}

public class OuterClass3 {
	
	public OutInterface2 doit (final String x) {	//doit()��������final����
		//��doit()�����ж���һ���ڲ���
		class InnerClass2 implements OutInterface2{
			InnerClass2(String s){
				s = x;
				System.out.println(s);
			}
			public void f() {
				System.out.println("���ʾֲ��ڲ����е�f()����");
			}
		}
		return new InnerClass2("doit");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			OuterClass3 out = new OuterClass3();
			OutInterface2 inter = out.doit("shi");
			inter.f();
	}

}
