// 돌게임

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		/*
		풀이 1
		각자 자기 차례에 홀수개가 남으면 무조건 이김(상대방이 한턴에 먹을 수 없기 때문)
		따라서 상근이가 먼저 시작하므로 돌이 홀수개 스타트면 상근, 짝수개 스타트면 상영이 이긴다
		 */
		// System.out.println((n % 2 == 0) ? "CY" : "SK");

		/*
		풀이 2
		해당 수일 때 무조건 승리한다: 최선의 수로 두면 무조건 저 수까지는 확보 가능
		boolean[] dp[k] : k개를 가지고 겨룰 때 상근이가 이긴다
		만약 dp[k-4]가 true면 dp[k]도 true (상영이가 1개를 먹으면 3개로, 3개를 먹으면 1개로 이김)
		 */
		boolean[] dp = new boolean[n+1];
		dp[1] = true;
		if (n >= 2) dp[2] = false;
		if (n >= 3) dp[3] = true;
		if (n >= 4) dp[4] = false;
		if (n >= 5) {
			for (int i = 5; i <= n; i++) {
				if (dp[i - 4])
					dp[i] = true;
			}
		}

		System.out.println((dp[n]) ? "SK" : "CY");
	}
}