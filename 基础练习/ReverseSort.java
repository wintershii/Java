
public class ReverseSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {10,20,30,40,50,60,70};
		ReverseSort sorter = new ReverseSort();
		sorter.sort(array);
	}
	
	/**
	 * 		��ת����
	 * @param array
	 * 			Ҫ��ת������
	 */			
	public void sort(int[] array) {
		System.out.println("��ת֮ǰ��");
			showArray(array);
			System.out.println("");
		int len = array.length;
		for(int i = 0; i < len / 2; i++) {
			int temp = array[i];
			array[i] = array[len - i - 1];
			array[len - i - 1] = temp;
		}
		System.out.println("��ת֮��");
		showArray(array);
	}
	
	/**
	 * 		��ʾ��ת�������
	 * @param array
	 */
	public void showArray(int[] array) {
		for(int x: array)
			System.out.print(x+" ");
	}
}
