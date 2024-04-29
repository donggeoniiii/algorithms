// 암호코드

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();

		// 만약 맨 앞자리가 0이면 변환 불가
		if (input.charAt(0) == '0') {
			System.out.println(0);
			return;
		}

		// dp[i] : i번째 자리까지 읽는 방법의 수
		int n = input.length();
		int[] dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;

		if (n >= 2) {
			for (int i = 2; i <= n; i++) {
				int prev = input.charAt(i-2) - '0';
				int cur = input.charAt(i-1) - '0';

				// 본인이 0이 아니면 일단 읽을 수 있음, 바로 전까지 경우의 수 그대로 가져옴
				if (cur != 0) {
					dp[i] = (dp[i] + dp[i-1]) % 1000000;
				}

				// 뒤에가 0이면 더 볼 필요 없음
				if (prev == 0) continue;

				// 앞이랑 같이 읽히는 경우 카운트
				int num = prev * 10 + cur;
				if (10 <= num && num <= 26) {
					dp[i] = (dp[i] + dp[i-2]) % 1000000;
				}
			}
		}

		System.out.println(dp[n]);
	}
}