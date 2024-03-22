// 가장 큰 정사각형

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n+1][m+1];
		for (int r = 1; r <= n; r++) {
			String s = br.readLine();
			for (int c = 1; c <= m; c++) {
				map[r][c] = s.charAt(c-1) - '0';
			}
		}

		// dp[i][j] : (1, 1) ~ (i, j)까지 구간에서 나올 수 있는 최대 한 변의 길이
		int[][] dp = new int[n+1][m+1];

		int maxlength = 0;
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= m; c++) {
				if (map[r][c] == 0) continue;
				// 이전 세 부분의 부분합 중 가장 작은 값에 +1한 값이 최대 크기
				dp[r][c] = Math.min(dp[r-1][c], Math.min(dp[r][c-1], dp[r-1][c-1])) + 1;
				maxlength = Math.max(maxlength, dp[r][c]);
			}
		}

		System.out.println(maxlength * maxlength);
	}
}