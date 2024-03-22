// 인구이동

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 땅 크기, 인구 차이
	private static int n, l, r;

	// 나라별 인구 수
	private static int[][] map;

	private static boolean[][] visited;

	// 이번 탐색에 연합하는 땅 리스트
	private static List<int[]> union;

	// 이동을 위한 델타배열
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	private static boolean outOfIndex(int cr, int cc) {
		return cr >= n || cc >= n || cr < 0 || cc < 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int dayCnt = 0;
		while (true) {
			boolean gameOver = true;
			visited = new boolean[n][n];

			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (visited[r][c]) continue;

					traverse(r, c);

					// 연합이 생겨서 인구가 이동 했으면 종료조건 off
					if (union.size() > 1) {
						gameOver = false;
					}
				}
			}

			if (gameOver) {
				break;
			}
			dayCnt++;
		}

		System.out.println(dayCnt);
	}

	private static void traverse(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		// 이번에 연합이 되는 애들
		union = new LinkedList<>();

		// 시작점 입력
		queue.offer(new int[]{sr, sc});
		union.add(new int[]{sr, sc});
		visited[sr][sc] = true;

		int total = map[sr][sc];
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int cp = map[cr][cc];

			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				if (outOfIndex(nr, nc)) continue;

				if (visited[nr][nc]) continue;

				// 1. 두 나라의 인구가 [l, r] 사이면 국경 오픈, 연합이라고 함
				int gap = Math.abs(cp - map[nr][nc]);
				if (gap < l || gap > r) continue;

				visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc});

				union.add(new int[] {nr, nc});
				total += map[nr][nc];
			}
		}
		// 2. 연합의 인구수는 인구 수 / 연합 수
		int unionCnt = union.size();

		for (int[] cur : union) {
			map[cur[0]][cur[1]] = total / unionCnt;
		}
	}
}