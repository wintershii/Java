package test.com;

public class Tran {
	static int avg(int number1,int number2)throws MyException{
		if(number1 < 0 || number2 < 0) {
			throw new MyException("������ʹ�ø���");
		}
		if(number1 > 100 || number2 > 100) {
			throw new MyException("��ֵ̫����");
		}
		return (number1 + number2)/2;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int result = avg(-1,75);
			System.out.println(result);
		}
		catch(MyException e) {
			System.out.println(e);
		}
	}

}
