// 벽 부수고 이동하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 이동하는 좌표, 벽 깬 개수, 이동거리
	static class Move {
		private int r;
		private int c;
		private int destroyCnt;
		private int moveCnt;

		public Move(int destroyCnt, int r, int c, int moveCnt) {
			this.destroyCnt = destroyCnt;
			this.r = r;
			this.c = c;
			this.moveCnt = moveCnt;
		}
	}

	// 맵 크기
	static int n;
	static int m;

	// 점프 횟수
	static int k;

	// 배열
	static int[][] maze;

	// 방문배열
	static boolean[][][] visited;

	// 방문을 위한 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// bfs 알고리즘
	static int findFastestETA() {

		// 탐색을 위한 queue
		Queue<Move> queue = new LinkedList<>();

		// 방문배열 선언
		visited = new boolean[k + 1][n][m];

		// 시작점 추가
		queue.offer(new Move(0, 0, 0, 1));

		// 시작점 방문체크
		visited[0][0][0] = true;

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			// 다음 좌표 가져오기
			Move cur = queue.poll();
			int cr = cur.r;
			int cc = cur.c;

			// 만약 방문지점에 도달하면 이동거리 반환 (+1)
			if (cr == n - 1 && cc == m - 1)
				return cur.moveCnt;

			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				// out of index 처리
				if (nr < 0 || nc < 0 || nr >= n || nc >= m)
					continue;

				// 벽을 만났는데
				if (maze[nr][nc] == 1) {
					// 아직 카운트가 남았고, 전에 깬적 없으면
					if (cur.destroyCnt < k && !visited[cur.destroyCnt + 1][nr][nc]) {
						// 깨고 방문체크
						visited[cur.destroyCnt + 1][nr][nc] = true;

						// 다음 방문좌표 추가
						queue.offer(new Move(cur.destroyCnt + 1, nr, nc, cur.moveCnt + 1));
					}
				} else {
					// 그냥 일반 땅이면 방문체크하고 추가
					if (!visited[cur.destroyCnt][nr][nc]) {
						visited[cur.destroyCnt][nr][nc] = true;
						queue.offer(new Move(cur.destroyCnt, nr, nc, cur.moveCnt + 1));
					}
				}
			}
		}

		// 결국 도착 못하면 -1 반환
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 배열의 크기, 벽 부수기 횟수
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// 맵 선언
		maze = new int[n][m];

		// 입력
		for (int r = 0; r < n; r++) {
			String line = br.readLine();
			for (int c = 0; c < m; c++) {
				maze[r][c] = line.charAt(c) - '0';
			}
		}

		// bfs on
		int eta = findFastestETA();

		// 출력
		System.out.println(eta);
	}
}