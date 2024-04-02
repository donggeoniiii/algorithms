// 마인크래프트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 땅의 크기, 인벤토리에 있는 블록의 수
	private static int n, m, b;

	private static int[][] land;

	private static final int CEIL = 256;

	private static int minTarget;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		land = new int[n][m];

		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				land[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 높이에 따라 걸리는 시간 구하기
		int minTime = Integer.MAX_VALUE;
		for (int target = CEIL; target >= 0; target--) {
			int time = solve(target);
			if (time < minTime) {
				minTime = time;
				minTarget = target;
			}
		}

		System.out.println(minTime + " " + minTarget);
	}

	private static int solve(int target) {
		int overCnt = 0;
		int underCnt = 0;
		int inventory = b;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				int gap = land[r][c] - target;
				if (gap > 0) {
					overCnt += gap;
					inventory += gap;
				}
				else {
					underCnt += Math.abs(gap);
					inventory -= Math.abs(gap);
				}
			}
		}

		// 처음에 있는 인벤토리 크기를 고려해서 가능한지 확인
		if (inventory < 0) {
			return Integer.MAX_VALUE;
		}

		return overCnt * 2 + underCnt;
	}
}