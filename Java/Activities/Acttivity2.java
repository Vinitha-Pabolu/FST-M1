package activities;

public class Acttivity2 {
		public static void main(String[] args) {
			int[] numbers={10, 77, 10, 54, -11, 10};
			int sum=0;
			for(int num: numbers) {
				if (num==10) {
					sum +=num;
				}
			}
			if(sum==30) {
				System.out.println(true);
			
			}else {
					System.out.println(false);
				}
		}
}
