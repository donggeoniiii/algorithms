import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int N = input.nextInt();
		/*
		 * 5층 기준
		 * 5층(꼭대기): 빈칸 5-1개, 별 2*1-1개
		 * 4층 		: 빈칸 5-2개, 별 2*2-1개
		 * 3층		: 빈칸 5-3개, 별 2*3-1개
		 */
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 0; j < N-i; j++) {
				System.out.printf("%s", " ");
			}
			for (int j = 0; j < (2*i-1); j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
