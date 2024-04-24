// 돌게임 3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		// 상근이가 이기려면 상영이의 마지막 턴 진행 후 돌이 1개 or 3개 or 4개 남아야 됨
		// 즉 1개, 3개, 4개 이전으로 게임 했을 때 상영이가 이겼으면 무조건 승리 가능
		// dp[i] : i개로 게임할 때 상근이가 이기면 true, 지면 false
		boolean[] dp = new boolean[n+1];
		dp[1] = true;
		if (n >= 2)	dp[2] = false;
		if (n >= 3) dp[3] = true;
		if (n >= 4) dp[4] = true;
		if (n >= 5) {
			for (int i = 5; i <= n; i++) {
				// 1개, 3개, 4개 전 상영이가 이겼으면 이번엔 상근 승
				if (!dp[i - 1] || !dp[i - 3] || !dp[i - 4])
					dp[i] = true;

			}
		}

		System.out.println(dp[n] ? "SK" : "CY");
	}
}