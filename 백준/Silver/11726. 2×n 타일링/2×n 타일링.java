// 2xn 타일링

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] dp = new int[n+1];

		if (n >= 1) dp[1] = 1;
		if (n >= 2) dp[2] = 2;

		if (n >= 3) {
			for (int i = 3; i <= n; i++) {
				// 1. n-2 =
				// 2. n-1 |
				dp[i] = dp[i-1] % 10007 + dp[i-2] % 10007;
			}
		}

		System.out.println(dp[n] % 10007);
	}
}