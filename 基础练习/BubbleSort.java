
public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {63,4,24,1,3,15};
		BubbleSort sorter = new BubbleSort();
		sorter.sort(array);
	}
	
	/**
	 * ð������
	 * 
	 * @param array
	 * 			Ҫ���������
	 */
	public void sort(int[] array) {
		for(int i = 1; i < array.length; i++) {
			for(int j = 0; j < array.length - 1; j++) {
				if(array[j] > array[j+1]) {
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
		showArray(array);
	}
	
	/**
	 * ��ʾ���������е�Ԫ��
	 * 
	 * @param array
	 */
	
	public void showArray(int[] array) {
		for(int x: array)
			System.out.print(x+" ");
	}
	
	
}
