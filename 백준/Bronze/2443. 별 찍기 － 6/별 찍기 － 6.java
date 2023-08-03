// 별찍기 6

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 줄 개수
		int n = input.nextInt();

		// 별찍기
		for (int i = 0; i < 2 * n - 1; i++) {
			for (int j = 0; j < i; j++) {
				sb.append(" ");
			}
			for (int j = i; j < 2 * n - 1 - i; j++) {
				sb.append("*");
			}
			sb.append("\n");
		}

		// 출력
		System.out.println(sb);
	}
}