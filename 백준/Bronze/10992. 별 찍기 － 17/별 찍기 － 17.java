import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		/*
		 * N = 4일 때
		 * 첫줄. 빈칸: N-1개, 별: 1개
		 *...
		 * i줄. 빈칸: N-i개, 별: 1개, 빈칸: 2*(i-1)-1개
		 * 막줄. 별:2*i-1개
		 */
		// i = 1
		for (int i = 0; i < N-1; i++) System.out.print(" ");
		System.out.println("*");
		// i = 2 ~ N-1
		for (int i = 2; i < N; i++) {
			for (int j = 0; j < N-i; j++) System.out.print(" ");
			System.out.print("*");
			for (int j = 0; j < 2*(i-1)-1; j++) System.out.print(" ");
			System.out.print("*");
			System.out.println();
		}
		// i = N
		if (N != 1) {
			for (int i = 0; i <2*N-1; i++) System.out.print("*");	
		}
	}
}
