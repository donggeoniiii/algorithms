// 스티커

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 스티커의 개수: 2n개
	private static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 탐색을 위한 델타배열
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		// 테스트케이스 개수
		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());

			int[][] sticker = new int[2][n];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for (int j = 0; j < n; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// dp[i][j]: (i, j) 자리에 있는 스티커를 붙일 때, 현재 구간까지 점수 합
			int[][] dp = new int[2][n];

			// 초기값 입력
			dp[0][0] = sticker[0][0];
			dp[1][0] = sticker[1][0];

			if (n >= 2) {
				dp[0][1] = sticker[0][1] + sticker[1][0];
				dp[1][1] = sticker[1][1] + sticker[0][0];
				
				for (int c = 2; c < n; c++) {
					for (int r = 0; r < 2; r++) {
						// default: 이번 스티커는 붙이는 경우만 체크
						dp[r][c] = sticker[r][c];

						/*
						  o x  |  x o
						  x o  |  o x
						  ----------------
						  o x x  |  x x o
						  x x o  |	o x x
						 2가지 경우에 대해 최댓값 살펴봐야 함
						*/
						int preSum = Math.max(dp[1 - r][c - 1], dp[1 - r][c - 2]);

						dp[r][c] += preSum;
					}
				}
			}

			// 스티커 점수 최댓값 출력
			int max = Math.max(dp[0][n-1], dp[1][n-1]);
			sb.append(max).append("\n");
		}
		System.out.println(sb);
	}
}