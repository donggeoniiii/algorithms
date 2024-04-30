// 베르트랑 공준

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		while (true) {
			int n = Integer.parseInt(br.readLine());

			if (n == 0) break;

			sb.append(findPrimesSmallerThan(2*n)).append("\n");
		}

		System.out.println(sb);
	}

	private static int findPrimesSmallerThan(int end) {
		int start = end / 2;
		int primeCnt = 0;

		for (int i = start+1; i <= end; i++) {
			if (isPrime(i)) primeCnt++;
		}

		return primeCnt;
	}

	private static boolean isPrime(int num) {
		if (num <= 1) return false;

		// 제곱근 미만의 숫자에서 나누어지는 수가 있으면 소수 아님
		int end = (int) Math.sqrt(num);

		for (int i = 2; i <= end; i++) {
			if (num % i == 0) return false;
		}

		return true;
	}
}