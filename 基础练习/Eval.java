import java.util.Date;
public class Eval {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		String year = String.format("%tY", date);
		String month = String.format("%tB", date);
		String day = String.format("%td", date);
		System.out.println("�����ǣ�"+year+"��");
		System.out.println("�����ǣ�"+month);
		System.out.println("�����ǣ�"+day+"��");
	}

}
