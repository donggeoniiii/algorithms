// 아기상어

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 물고기의 위치 정보 및 상어까지 거리를 갖고 있는 클래스
	private static class Fish {
		private int r;
		private int c;
		private int dist;

		public Fish(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 공간의 크기
		int n = Integer.parseInt(br.readLine());

		// 공간을 나타내는 배열
		int[][] map = new int[n][n];

		// 시작점 체크
		int sr = -1;
		int sc = -1;

		// 정보 입력
		for (int r = 0; r < n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());

				// 만약 해당 자리값이 9면 시작점 표시
				if (map[r][c] == 9) {
					sr = r;
					sc = c;
					map[r][c] = 0;
				}
			}
		}

		// bfs를 위한 델타배열
		int[] dr = {-1, 0, 0, 1};
		int[] dc = {0, -1, 1, 0};

		// 종료조건: 더이상 먹을 수 있는 물고기가 없음
		boolean terminated = false;

		// 종료하기 까지 걸리는 시간
		int time = 0;

		// 현재 물고기의 몸집, 시작은 2
		int fishSize = 2;

		// 현재 몸집에서 먹은 물고기 수, 커질 때마다 초기화
		int eatCnt = 0;

		// 더 방문할 지점이 없을 때까지
		while (!terminated) {
			// bfs를 통해 가장 가까운 물고기 좌표 찾기
			// 거리순, 상하, 좌우 순으로 체크할 pqueue
			PriorityQueue<Fish> queue = new PriorityQueue<>((o1, o2) -> {
				if (o1.dist == o2.dist) {
					if (o1.r != o2.r) {
						return Integer.compare(o1.r, o2.r);
					} else {
						if (o1.c != o2.c) {
							return Integer.compare(o1.c, o2.c);
						}
					}
				}
				return Integer.compare(o1.dist, o2.dist);
			});

			// 이번 bfs에 먹을 수 있는 고기를 못 찾았는지 체크하는 변수
			boolean hasFish = false;

			// 방문 체크 배열
			boolean[][] visited = new boolean[n][n];

			// 시작점 체크
			visited[sr][sc] = true;
			queue.offer(new Fish(sr, sc, 0));

			// 더 방문할 지점이 없을 때까지
			while (!queue.isEmpty()) {

				// 이번에 방문할 좌표
				Fish cur = queue.poll();
				int cr = cur.r;
				int cc = cur.c;

				// 만약 방문좌표에 있는 물고기가 먹을 수 있는 크기면
				if (map[cr][cc] != 0 && fishSize > map[cr][cc]) {
					// 먹었다고 표시
					map[cr][cc] = 0;
					// 먹은 수 증가
					eatCnt++;
					// 이번 탐색에서 먹긴 했으니까
					hasFish = true;

					// 시간은 해당 지점까지 걸리는 거리만큼
					time += cur.dist;

					// 다음 탐색을 위해 시작점 변경
					sr = cr;
					sc = cc;

					// 이번 탐색은 종료
					break;
				}

				// 델타배열
				for (int dt = 0; dt < 4; dt++) {
					int nr = cr + dr[dt];
					int nc = cc + dc[dt];

					// index 벗어나면 스킵
					if (nr < 0 || nc < 0 || nr >= n || nc >= n)
						continue;

					// 이미 방문했으면 스킵
					if (visited[nr][nc])
						continue;

					// 갈 수 있는 지점을 찾으면
					if (fishSize >= map[nr][nc]) {

						// 먹지는 못하면 일단 다음 탐색 좌표에 추가
						visited[nr][nc] = true;
						queue.offer(new Fish(nr, nc, cur.dist + 1));
					}
				}
			}

			// 만약 이번에 먹고 사이즈가 커졌으면 반영
			if (eatCnt == fishSize) {
				eatCnt = 0;
				fishSize++;
			}

			// 만약 이번에 물고기를 못 먹고 끝났으면 종료
			if (!hasFish) {
				terminated = true;
			}
		}

		// 정답 출력
		System.out.println(time);
	}
}