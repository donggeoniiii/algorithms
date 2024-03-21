// 구간 합 구하기 4

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

		int[] pSum = new int[n+1]; // pSum[i] : 1 ~ i까지 누적합
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			pSum[i] = pSum[i-1] + num;
		}

		// i ~ j번째 수까지 합 == j까지 합 - (i-1)까지 합
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= m; tc++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());

			sb.append(pSum[j] - pSum[i-1]).append("\n");
		}
		System.out.println(sb);
	}
}