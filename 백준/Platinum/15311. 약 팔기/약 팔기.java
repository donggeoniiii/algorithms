// 약 팔기

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 최대 요구량
		int N = input.nextInt();

		// 출력 준비
		StringBuilder sb = new StringBuilder();

		sb.append(2000).append("\n");
		for (int i = 0; i < 1000; i++) {
			sb.append(1).append(" ");
		}
		for (int i = 0; i < 1000; i++) {
			sb.append(1000).append(" ");
		}

		// 출력
		System.out.println(sb);
	}
}