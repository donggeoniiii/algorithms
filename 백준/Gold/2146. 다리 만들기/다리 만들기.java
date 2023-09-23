// 다리만들기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 맵 크기
	static int n;

	// 맵 배열
	static int[][] map;

	// 방문배열
	static boolean[][] visited;

	// bfs를 위한 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// 1. 맵 체크
	static void checkRegion(int sr, int sc, int checkType) {
		// bfs를 위한 queue
		Queue<int[]> queue = new LinkedList<>();

		// 시작점 체크
		queue.offer(new int[] {sr, sc});
		map[sr][sc] = checkType;
		visited[sr][sc] = true;

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {

			// 다음 방문좌표
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				// out of index
				if (nr < 0 || nc < 0 || nr >= n || nc >= n)
					continue;

				// 물이면 스킵
				if (map[nr][nc] == 0)
					continue;

				// 처음 보는 땅이면
				if (!visited[nr][nc]) {
					// 방문체크하고 맵에 표시
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					map[nr][nc] = checkType;
				}
			}
		}
	}

	// 2. 해당 위치에서 가장 빠른 지점 찾기
	static int checkShortestBridge(int sr, int sc, int checkType) {
		// bfs를 위한 queue
		Queue<int[]> queue = new LinkedList<>();

		// 방문배열
		int[][] visitedTime = new int[n][n];
		for (int r = 0; r < n; r++) {
			Arrays.fill(visitedTime[r], -1);
		}

		// 시작점 체크
		queue.offer(new int[] {sr, sc});
		visitedTime[sr][sc] = 0;

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {

			// 다음 방문좌표
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				// out of index
				if (nr < 0 || nc < 0 || nr >= n || nc >= n)
					continue;

				// 같은 지역이면 스킵
				if (map[nr][nc] == checkType)
					continue;

				// 방문했으면 스킵
				if (visitedTime[nr][nc] == -1) {

					// 만약 좌표가 물이면 다음 탐색좌표에 추가
					if (map[nr][nc] == 0) {
						queue.offer(new int[] {nr, nc});
						visitedTime[nr][nc] = visitedTime[cr][cc] + 1;
					}
					// 만약 좌표가 다른 지역이면
					else {
						// 걸린 시간 반환
						return visitedTime[cr][cc];
					}
				}
			}
		}
		return 9999;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 크기
		n = Integer.parseInt(br.readLine());

		// 배열 선언
		map = new int[n][n];

		// 상태 입력
		for (int r = 0; r < n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 돌면서 지역 체크
		int regionType = 1;
		visited = new boolean[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (map[r][c] != 0 && !visited[r][c]) {

					checkRegion(r, c, regionType++);
				}
			}
		}
		
		// 이번엔 돌면서 제일 가까운 다리 길이 찾기
		int minLength = 9999;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (map[r][c] != 0) {
					int curLength = checkShortestBridge(r, c, map[r][c]);

					// 최솟값 갱신
					minLength = Math.min(minLength, curLength);
				}
			}
		}

		// 최솟값 출력
		System.out.println(minLength);
	}
}