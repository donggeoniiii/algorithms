// 평범한 배낭

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		// 물품의 수, 버틸 수 있는 무게
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 물건 정보
		int[] weight = new int[n+1];
		int[] value = new int[n+1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());

			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			weight[i] = w;
			value[i] = v;
		}

		// dp[i] : ikg에서 봤을 때 최대 가치
		int[] dp = new int[k+1];

		// 물건 하나씩 돌면서 확인할 점: 이번 물건을 넣는 것과 넣지 않는 것 중에 뭐가 더 현재까지 이득인가
		for (int i = 1; i <= n; i++) {
			// 이번 물건을 넣는 범위 안에서 확인
			for (int total = k; total >= weight[i]; total--) {
				dp[total] = Math.max(dp[total], dp[total-weight[i]] + value[i]);
			}
		}

		System.out.println(dp[k]);
	}
}