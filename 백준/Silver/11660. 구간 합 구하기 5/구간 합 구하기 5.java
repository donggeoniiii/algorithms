// 구간합 구하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] num = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dp[i][j] : i, j까지 구간합
		int[][] dp = new int[n+1][n+1];
		dp[1][1] = num[1][1];
		for (int i = 2; i <= n; i++) {
			dp[i][1] = dp[i-1][1] + dp[i][0] - dp[i-1][0] + num[i][1];
			dp[1][i] = dp[1][i-1] + dp[0][i-1] - dp[0][i-1] + num[1][i];
		}

		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= n; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + num[i][j];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int preSum = dp[x2][y2] - dp[x1-1][y2] - dp[x2][y1-1] + dp[x1-1][y1-1];
			sb.append(preSum).append("\n");
		}

		System.out.println(sb);
	}
}