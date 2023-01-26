import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		/*
		 * N = 3일때
		 * 1: 공백 3-1칸, 별 1칸
		 * 2: 공백 3-2칸, 별 2칸
		 * 3. 공백 3-3칸, 별 3칸
		 */
		// 올라감
		for (int i = 1; i < N+1; i++) {
			for (int j = 0; j < N-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		// 내려감
		for (int i = N-1; i > 0; i--) {
			for (int j = 0; j < N-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}