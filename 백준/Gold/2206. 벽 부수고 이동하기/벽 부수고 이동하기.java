// 벽 부수고 이동하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 현재 좌표, 점프했는지 여부
	static class Point {
		private int r;
		private int c;
		private int hasJumped; // 0이면 no jump, 1이면 jump

		public Point(int r, int c, int hasJumped) {
			this.r = r;
			this.c = c;
			this.hasJumped = hasJumped;
		}
	}

	// 미로의 크기
	static int N;
	static int M;

	// 미로 배열
	static int[][] maze;

	// 방문 배열: [n][m][0] : no jump, [n][m][1] : jump
	static int[][][] visited;

	// 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// out of index 처리
	static boolean outOfIndex(int r, int c) {
		return r >= N || c >= M || r < 0 || c < 0;
	}

	// bfs
	static int findFastestETA() {

		// 시작점 queue에 추가
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, 0));

		// 방문 체크, 첫 칸부터 세야 하니까
		visited[0][0][0] = 1;
		visited[0][0][1] = 1;

		// 더 도착할 지점이 없을 때까지
		while (!queue.isEmpty()) {

			// 좌표 뽑아오기
			Point cur = queue.poll();
			int cr = cur.r;
			int cc = cur.c;
			int hasJumped = cur.hasJumped;
			
			// 방문한 지점이 [n-1][m-1]이면
			if (cr == N - 1 && cc == M - 1)
				// 결과 반환
				return visited[cr][cc][hasJumped];

			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				// out of index
				if (outOfIndex(nr, nc))
					continue;

				// 벽을 만나면
				if (maze[nr][nc] == 1) {
					// 점프가 남았나? -> 아니면 스킵
					if (hasJumped == 1)
						continue;

					// 방문체크 갱신, 점프표시
					visited[nr][nc][1] = visited[cr][cc][0] + 1;

					// queue에 추가
					queue.offer(new Point(nr, nc, 1));
				}

				// 처음 보는 땅이면
				if (maze[nr][nc] == 0 && visited[nr][nc][hasJumped] == 0) {
					// 방문체크
					visited[nr][nc][hasJumped] = visited[cr][cc][hasJumped] + 1;

					// queue에 추가
					queue.offer(new Point(nr, nc, hasJumped));
				}
			}
		}

		// 도착 지점에 가지 못 했으면 안 되는 거니까
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 땅 크기
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 땅 정보 입력
		maze = new int[N][M];
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				maze[r][c] = line.charAt(c) - '0';
			}
		}

		// bfs를 위한 방문배열
		visited = new int[N][M][2];

		// bfs on
		int eta = findFastestETA();

		// 출력
		System.out.println(eta);
	}
}