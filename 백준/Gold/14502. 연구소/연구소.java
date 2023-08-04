// 연구소

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 벽의 길이
	static int N;
	static int M;

	// map
	static int[][] map;

	// bfs를 위한 방문배열
	static boolean[][] visited;

	// 벽을 추가로 세울 세 점을 저장하는 배열
	static int[][] selected;

	// bfs를 위한 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static int maxCnt;

	// bfs 알고리즘
	static void findSecuritySpot(int[] src) {
		// bfs를 위한 queue
		Queue<int[]> queue = new LinkedList<>();

		// 시작점 추가
		queue.offer(src);
		visited[src[0]][src[1]] = true;

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {

			// 탐색좌표 꺼내오기
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				// out of index
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;

				// 벽 제외
				if (map[nr][nc] == 1)
					continue;

				// 방문했으면 제외
				if (!visited[nr][nc]) {

					// 방문체크하고 queue에 추가
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
	}

	// 벽 세울 곳을 고른 다음 bfs하는 알고리즘
	static void choosePlace(int cnt) {
		// base case: 3개의 지점을 다 선택했으면
		if (cnt >= 3) {

			// 방문배열 초기화
			visited = new boolean[N][M];

			// 돌면서 처음 보는 바이러스 찾기
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					// 만약 처음 보는 바이러스를 만나면 시작
					if (map[r][c] == 2 && !visited[r][c]) {

						// bfs on
						findSecuritySpot(new int[] {r, c});
					}
				}
			}

			// 다 돌고 나서, 바이러스가 퍼지지 않은 지점 숫자 세기
			int securitySpotCnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 0 && !visited[r][c])
						securitySpotCnt++;
				}
			}

			// 최댓값 갱신
			maxCnt = Math.max(maxCnt, securitySpotCnt);

			return;
		}

		// recursive case
		// 벽 세울 위치 찾기

		// 만약 찾은 위치가 그냥 땅이면
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					// 선택
					map[r][c] = 1;

					// 다음 선택으로 이동
					choosePlace(cnt + 1);

					// 선택 안하는 경우를 위해 바꾸고
					map[r][c] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 배열 크기 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 배열 선언
		map = new int[N][M];

		// 배열에 상황 입력
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 최댓값 초기화
		maxCnt = -1;

		// 선택 배열 초기화
		selected = new int[3][2];

		// 최댓값인 경우 찾기
		choosePlace(0);

		// 결과 출력
		System.out.println(maxCnt);
	}
}