// 초콜릿 자르기

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 가로길이
		int N = input.nextInt();

		// 세로길이
		int M = input.nextInt();

		// 정답출력
		System.out.println(M * N - 1);
	}
}