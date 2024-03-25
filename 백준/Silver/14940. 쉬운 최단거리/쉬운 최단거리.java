// 쉬운 최단거리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 지도의 크기
	private static int n, m;

	// 지도: 갈 수 없으면 f, 있으면 t;
	private static boolean[][] avail;

	// 방문시간 기록할 배열
	private static int[][] visited;

	// 목표 좌표
	private static int tr, tc;

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		avail = new boolean[n][m];

		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				int cur = Integer.parseInt(st.nextToken());
				if (cur == 2) {
					tr = r;
					tc = c;
				}
				avail[r][c] = cur > 0;
			}
		}
	}

	private static void solve() {
		// 탐색을 위한 queue
		Queue<int[]> queue = new LinkedList<>();

		// 방문 시간 기록 배열
		visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], -1);
		}

		// 목표지점을 시작점으로 입력
		queue.offer(new int[] {tr, tc});
		visited[tr][tc] = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (outOfIndex(nr, nc)) continue;

				// 갈 수 없는 지점은 스킵
				if (!avail[nr][nc]) continue;

				// 이미 방문했으면 스킵
				if (visited[nr][nc] >= 0) continue;

				queue.offer(new int[] {nr, nc});

				// 방문시간 갱신
				visited[nr][nc] = visited[cr][cc] + 1;
			}
		}

		// 출력, 원래 갈 수 있는 부분인데 못 갔으면 -1 출력
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (!avail[r][c]) {
					sb.append(0).append(" ");
				}
				else {
					sb.append(visited[r][c]).append(" ");
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static boolean outOfIndex(int r, int c) {
		return r >= n || c >= m || r < 0 || c < 0;
	}
}