// 게리맨더링 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n;

	private static int[][] a;

	// 재현시 전체 인구수
	private static int total;

	// 최소 차이
	private static int minDiff;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		a = new int[n][n];
		total = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				total += a[i][j];
			}
		}

		minDiff = 40001;
		solve();

		System.out.println(minDiff);
	}

	private static void solve() {
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				for (int d1 = 1; d1 < n; d1++) {
					for (int d2 = 1; d2 < n; d2++) {
						if (x + d1 + d2 >= n) continue;
						if (y - d1 < 0 || y + d2 >= n) continue;

						findMinDiff(x, y, d1, d2);
					}
				}

			}
		}
	}

	private static void findMinDiff(int x, int y, int d1, int d2) {
		int[] area = new int[5];
		boolean[][] border = new boolean[n][n];

		for (int i = 0; i <= d1; i++) {
			border[x + i][y - i] = true;
			border[x + d2 + i][y + d2 - i] = true;
		}
		for (int i = 0; i <= d2; i++) {
			border[x + i][y + i] = true;
			border[x + d1 + i][y - d1 + i] = true;
		}

		for (int r = 0; r < x + d1; r++) {
			for (int c = 0; c <= y; c++) {
				if (border[r][c]) break;
				area[0] += a[r][c];
			}
		}
		for (int r = 0; r <= x + d2; r++) {
			for (int c = n-1; c > y; c--) {
				if (border[r][c]) break;
				area[1] += a[r][c];
			}
		}
		for (int r = n - 1; r >= x + d1; r--) {
			for (int c = 0; c < y - d1 + d2; c++) {
				if (border[r][c]) break;
				area[2] += a[r][c];
			}
		}
		for (int r = n-1; r > x + d2; r--) {
			for (int c = n-1; c >= y - d1 + d2; c--) {
				if (border[r][c]) break;
				area[3] += a[r][c];
			}
		}

		area[4] = total;
		for (int i = 0; i < 4; i++) {
			area[4] -= area[i];
		}

		Arrays.sort(area);

		minDiff = Math.min(minDiff, area[4] - area[0]);
	}
}