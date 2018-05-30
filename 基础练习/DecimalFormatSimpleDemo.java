import java.text.DecimalFormat;
public class DecimalFormatSimpleDemo {
	//ʹ��ʵ��������ʱ���ø�ʽ��ģʽ
	static public void SimgleFormat(String pattern,double value) {
		DecimalFormat myFormat = new DecimalFormat();
		String output = myFormat.format(value);
		System.out.println(value+""+pattern+""+output);
	}
	
	//ʹ��applyPattern()���������ֽ��и�ʽ��
	static public void UseApplyPatternMethodFormat(String pattern, double value) {
		DecimalFormat myFormat = new DecimalFormat();
		myFormat.applyPattern(pattern);		//����applyPattern()�������ø�ʽ��ģ��
		System.out.println(value+""+pattern+""+myFormat.format(value));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimgleFormat("###,###,###",123456.789);
		SimgleFormat("000000.###kg",123456.789);
		SimgleFormat("000000.000",123.78);
		
		UseApplyPatternMethodFormat("#.###%",0.789);
		UseApplyPatternMethodFormat("###.##%",123456.789);
		UseApplyPatternMethodFormat("0.00\u2030",0.789);
	}

}
