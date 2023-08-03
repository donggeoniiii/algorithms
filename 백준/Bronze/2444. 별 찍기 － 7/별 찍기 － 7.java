// 별찍기7

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 줄 개수
		int n = input.nextInt();

		// 별 찍기
		for (int i = 1; i < 2 * n; i++) {
			for (int j = 1; j <= Math.abs(n - i); j++) {
				sb.append(" ");
			}
			for (int j = 1; j <= ((i <= n) ? 2 * i - 1 : 2 * (2 * n - i) - 1); j++) {
				sb.append("*");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}