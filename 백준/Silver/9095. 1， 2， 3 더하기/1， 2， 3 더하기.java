// 1, 2, 3 더하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테케 개수
		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= t; tc++) {
			// 1, 2, 3의 합으로 표현할 정수
			int n = Integer.parseInt(br.readLine());

			if (n == 0) {
				sb.append(0).append("\n");
				continue;
			}

			// 메모이제이션 배열
			int[] memo = new int[n+1];

			// 기본값
			if (n >= 1) memo[1] = 1;
			if (n >= 2) memo[2] = 2;
			if (n >= 3) memo[3] = 4;

			// n까지 채우기
			if (n >= 4) {
				for (int i = 4; i <= n; i++) {
					memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
				}
			}

			// 정답 입력
			sb.append(memo[n]).append("\n");
		}

		System.out.println(sb);
	}
}