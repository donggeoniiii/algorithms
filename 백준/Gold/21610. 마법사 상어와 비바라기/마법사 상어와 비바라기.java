// 마법사 상어와 비바라기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static class Cloud {
		int r;
		int c;

		Cloud (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	// 방향 (0은 안씀)
	private static final int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	private static final int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] a = new int[n][n];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				a[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 비바라기 시전 : 구름 생성
		List<Cloud> cloud = new LinkedList<>();
		cloud.add(new Cloud(n - 1, 0));
		cloud.add(new Cloud(n - 1, 1));
		cloud.add(new Cloud(n - 2, 0));
		cloud.add(new Cloud(n - 2, 1));

		for (int i = 0; i < m; i++) {
			// 이번에 이동할 구름의 위치
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			// 1. 구름이 이동
			for (Cloud c : cloud){
				c.r = c.r + s * dr[d];
				c.c = c.c + s * dc[d];

				c.r %= n;
				c.c %= n;

				if (c.r < 0) c.r += n;
				if (c.c < 0) c.c += n;
			}

			// 2. 구름이 있는 칸의 바구니에 물의 양 증가, 구름이 있었다고 표시
			boolean[][] clouded = new boolean[n][n];
			Queue<Cloud> rained = new LinkedList<>();
			for (Cloud c : cloud){
				a[c.r][c.c]++;

				clouded[c.r][c.c] = true;
				rained.offer(new Cloud(c.r, c.c));
			}

			// 3. 구름 사라짐
			cloud.clear();

			// 4. 구름이 증가한 칸에서 물복사 버그 시전
			while (!rained.isEmpty()) {
				Cloud c = rained.poll();
				int[] tdr = {-1, -1, 1, 1};
				int[] tdc = {-1, 1, -1, 1};

				for (int dt = 0; dt < 4; dt++) {
					int nr = c.r + tdr[dt];
					int nc = c.c + tdc[dt];

					if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

					// 대각선 자리에 물이 있으면 물의 양 증가
					if (a[nr][nc] > 0) a[c.r][c.c]++;
				}
			}

			// 5. 구름이 새로 생김
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (a[r][c] < 2) continue;
					if (clouded[r][c]) continue;

					cloud.add(new Cloud(r, c));
					a[r][c] -= 2;
				}
			}
		}

		// 물의 총합 구하기
		int total = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				total += a[r][c];
			}
		}

		System.out.println(total);
	}
}