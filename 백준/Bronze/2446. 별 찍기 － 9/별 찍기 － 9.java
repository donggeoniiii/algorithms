import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		/*
		 * N = 5일때
		 * 1: 공백 1-1칸, 별 2*(N+1-1)-1칸
		 * 2: 공백 2-1칸, 별 2*(N+1-2)-1칸
		 * 3: 공백 3-1칸, 별 2*(N+1-3)-1칸
		 * .
		 * .
		 * .
		 * 내려갈 때
		 * 1: 공백 (N-1)-1칸, 별 2*1+1칸
		 * 2: 공백 (N-1)-2칸, 별 2*2+1칸
		 * 3: 공백 (N-1)-3칸, 별 2*3+1칸
		 * 4: 공백 (N-1)-4칸, 별 2*4+1칸
		 */
		// 올라감
		for (int i = 1; i < N+1; i++) {
			for (int j = 0; j < i-1; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*(N+1-i)-1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		// 내려감
		for (int i = 2; i < N+1; i++) {
			for (int j = 0; j < N-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i-1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
