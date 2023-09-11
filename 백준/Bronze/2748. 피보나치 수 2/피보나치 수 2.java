// 1로 만들기 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static long[] memo;

	static long memoization(int n) {
		if (memo[n] == -1)
			memo[n] = memoization(n - 1) + memoization(n - 2);
		return memo[n];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 구하고 싶은 피보나치 수의 순서
		int n = Integer.parseInt(br.readLine());

		// 메모이제이션
		memo = new long[n + 1];
		// -1로 초기화해서 처음 계산하는 값 분별
		Arrays.fill(memo, -1);

		// 0,1은 자명
		memo[0] = 0;
		memo[1] = 1;

		// n번째 피보나치 수 출력
		System.out.println(memoization(n));
	}
}