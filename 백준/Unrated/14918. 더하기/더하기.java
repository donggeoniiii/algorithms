// 더하기

import java.util.Scanner;

public class Main {
	// 두 정수
	static int a, b;

	public static void main(String[] args) {
		input();
		solution();
	}

	private static void input() {
		Scanner input = new Scanner(System.in);
		a = input.nextInt();
		b = input.nextInt();
	}

	private static void solution() {
		System.out.println(a+b);
	}

}