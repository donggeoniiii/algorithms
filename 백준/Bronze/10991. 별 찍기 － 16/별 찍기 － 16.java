import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		/*
		 * N = 5개일 때
		 * 1. 빈칸 5-1개, 별 1개
		 * 2. 빈칸 5-2개, 별 2개
		 * .
		 * .
		 * .
		 * i. 빈칸 5-i개, 별 i개
		 * 1~5까지, i = 1; i < 5+1; i++;
		 * 빈칸 + 별 = N
		 */
		int N = input.nextInt();
		for (int i = 1; i < N+1; i++) { 
			for (int j = 0; j < N-i; j++) {
				System.out.print(" ");
			}
			for (int j = N-i; j < N; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}
}
