// 동전

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= t; i++) {
			int n = Integer.parseInt(br.readLine());

			int[] coin = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				coin[j] = Integer.parseInt(st.nextToken());
			}

			int m = Integer.parseInt(br.readLine());

			// 동전을 가지고 만들 수 있는 방법의 수
			int[] dp = new int[m+1];
			dp[0] = 1; // 아무것도 안 하기

			for (int price : coin) {
				for (int j = price; j <= m; j++) {
					dp[j] += dp[j-price];
				}
			}

			sb.append(dp[m]).append("\n");
		}
		System.out.println(sb);
	}
}