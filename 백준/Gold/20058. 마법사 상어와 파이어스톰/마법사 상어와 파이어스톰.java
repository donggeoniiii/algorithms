// 마법사 상어와 파이어 스톰

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 전체 격자 크기, 파이어스톰 시전 횟수
	private static int n, q;

	// 작은 격자 크기
	private static int m;

	// 남아있는 얼음
	private static int[][] ice;

	// 파이어스톰 단계
	private static int[] firestorm;

	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};

	private static boolean[][] visited;

	private static int maxIceCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		int temp = 1;
		for (int i = 0; i < n; i++) {
			temp *= 2;
		}
		n = temp;

		ice = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		firestorm = new int[q];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			firestorm[i] = Integer.parseInt(st.nextToken());
		}

		for (int level = 0; level < q; level++) {
			m = 1;
			for (int i = 0; i < firestorm[level]; i++) {
				m *= 2;
			}
			// 격자 분할, 작은 격자 단위로 90도 회전
			for (int r = 0; r < n / m; r++) {
				for (int c = 0; c < n / m; c++) {
					int sr = r * m;
					int sc = c * m;
					rotate(sr, sc);
				}
			}

			// 녹는 칸이 있는지 전체 배열 체크
			melt();
		}

		// 남은 얼음의 합, 가장 큰 얼음 조각의 크기
		int iceCnt = 0;
		visited = new boolean[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				iceCnt += ice[r][c];

				if (ice[r][c] == 0) continue;

				if (visited[r][c]) continue;

				bfs(r, c);
			}
		}

		System.out.println(iceCnt + "\n" + maxIceCnt);
	}

	private static void rotate(int sr, int sc) {
		int[][] newIce = new int[m][m];
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < m; c++) {
				newIce[r][c] = ice[sr + (m-1) - c][sc + r];
			}
		}

		for (int r = 0; r < m; r++) {
			for (int c = 0; c < m; c++) {
				ice[sr + r][sc + c] = newIce[r][c];
			}
		}
	}

	private static void melt() {
		// 녹았는지 체크
		boolean[][] melt = new boolean[n][n];

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				int iceCnt = 0;
				for (int dt = 0; dt < 4; dt++) {
					int nr = r + dr[dt];
					int nc = c + dc[dt];

					// out of index == 얼음 없음
					if (outOfIndex(nr, nc)) continue;

					// 얼음이 남은 땅이면 얼음 카운트 증가
					if (ice[nr][nc] > 0) iceCnt++;
				}
				// 카운트가 3개 미만이면 이번 턴에 얼음이 녹았다고 표시
				if (iceCnt < 3) melt[r][c] = true;
			}
		}

		// 업데이트
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				ice[r][c] -= (melt[r][c]) ? 1 : 0;

				if (ice[r][c] < 0) ice[r][c] = 0;
			}
		}
	}

	private static void bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		int iceZoneCnt = 1; // 시작점 포함

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (outOfIndex(nr, nc)) continue;

				if (ice[nr][nc] == 0) continue;

				if (visited[nr][nc]) continue;

				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				iceZoneCnt++;
			}
		}

		// 이번에 찾을 수 있는 얼음 개수
		maxIceCnt = Math.max(maxIceCnt, iceZoneCnt);
	}

	private static boolean outOfIndex(int r, int c) {
		return r < 0 || r >= n || c < 0 || c >= n;
	}
}