// 팰린드롬 ?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		// dp[i][j] : i번째 수 ~ j번째 수까지 팰린드롬인지
		boolean[][] dp = new boolean[n + 1][n + 1];
		
		// 일단 자기 자신은 무조건 팰린드롬, 마주보는 두 수가 같으면 팰린드롬
		for (int i = 1; i <= n; i++) {
			dp[i][i] = true;
			if (num[i-1] == num[i]) dp[i-1][i] = true;
		}

		// 이번에 비교하는 두 수가 같고, 사이에 k개의 수가 팰린드롬이면 전체도 팰린드롬
		for (int diff = 2; diff <= n - 1; diff++) {
			for (int start = 1; start + diff <= n; start++) {
				int end = start + diff;

				// 맨 끝의 두 수가 애초에 다르면 볼 필요도 없음
				if (num[start] != num[end]) continue;

				if (dp[start+1][end-1]) dp[start][end] = true;
			}
		}

		// 질문
		int m = Integer.parseInt(br.readLine());
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			if (dp[s][e]) answer.append(1);
			else answer.append(0);
			answer.append("\n");
		}

		System.out.println(answer);
	}
}