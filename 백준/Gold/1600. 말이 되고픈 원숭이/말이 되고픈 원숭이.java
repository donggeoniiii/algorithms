// 말이 되고픈 원숭이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 움직이는 위치, 현재까지 점프 횟수를 저장하는 클래스
	static class Move {
		private int r;
		private int c;
		private int jumpCnt;
		private int moveCnt;

		public Move(int r, int c, int jumpCnt, int moveCnt) {
			this.r = r;
			this.c = c;
			this.jumpCnt = jumpCnt;
			this.moveCnt = moveCnt;
		}
	}

	// 배열의 크기
	static int h; // row
	static int w; // column

	// 점프 횟수
	static int k;

	// 미로? 배열
	static int[][] maze;

	// 방문배열, 값은 방문시간, 마지막 칸은 해당 칸에 도착하는 가장 빠른 시간
	static boolean[][][] visited;

	// bfs를 위한 델타배열: 0~3 상하좌우, 4~11 나이트의 이동
	// 뒤로 갔다가 한번에 넘어가는게 가장 빠른 방법일 수 있으니 뒤로 가는 경우도 빼지 않고 탐색
	static int[] dr = {1, 0, -1, 0, 2, 1, 2, 1, -1, -2, -1, -2};
	static int[] dc = {0, 1, 0, -1, 1, 2, -1, -2, 2, 1, -2, -1};

	// bfs 알고리즘
	static int findFastestETA() {
		// bfs를 위한 queue
		Queue<Move> queue = new LinkedList<>();

		// 방문배열 초기화
		visited = new boolean[h][w][k + 1];

		// 시작점 방문체크
		visited[0][0][0] = true;

		// queue에 추가
		queue.offer(new Move(0, 0, 0, 0));

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {

			// 다음 탐색지점
			Move cur = queue.poll();
			int cr = cur.r;
			int cc = cur.c;

			// 만약 도착점이면 그대로 종료
			if (cr == h - 1 && cc == w - 1)
				return cur.moveCnt;

			// 델타배열로 탐색
			// 원숭이의 이동
			for (int dt = 0; dt < 4; dt++) {

				// 다음 좌표
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				// out of index 제외
				if (nr >= h || nc >= w || nr < 0 || nc < 0)
					continue;

				// 벽이면 제외
				if (maze[nr][nc] == 1)
					continue;

				// 방문했으면 제외
				if (visited[nr][nc][cur.jumpCnt])
					continue;

				// 나머지 경우는 방문할 수 있으니까
				visited[nr][nc][cur.jumpCnt] = true;
				queue.offer(new Move(nr, nc, cur.jumpCnt, cur.moveCnt + 1));
			}

			// 말의 움직임
			if (cur.jumpCnt < k) {
				for (int dt = 4; dt < 12; dt++) {
					// 다음 좌표
					int nr = cr + dr[dt];
					int nc = cc + dc[dt];

					// out of index 제외
					if (nr >= h || nc >= w || nr < 0 || nc < 0)
						continue;

					// 벽이면 제외
					if (maze[nr][nc] == 1)
						continue;

					// 이전에 방문했으면 제외
					if (visited[nr][nc][cur.jumpCnt + 1])
						continue;

					// 처음 방문하는 거니까
					visited[nr][nc][cur.jumpCnt + 1] = visited[cr][cc][cur.jumpCnt];

					queue.offer(new Move(nr, nc, cur.jumpCnt + 1, cur.moveCnt + 1));
				}
			}
		}

		// 도착점을 방문하지 못하고 while문을 탈출하면 못 가는거니까
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 점프횟수
		k = Integer.parseInt(br.readLine());

		// 배열의 크기
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		// 배열 선언
		maze = new int[h][w];

		// 배열 입력
		for (int r = 0; r < h; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < w; c++) {
				maze[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// bfs on
		int eta = findFastestETA();

		// 정답 출력
		System.out.println(eta);
	}
}