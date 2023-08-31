// 치즈

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 전체 모눈종이 크기
	static int n, m;

	// 전체 모눈종이 배열
	static int[][] map;

	// 바깥 공기 체크하는 메소드
	static boolean[][] isOutside;

	// bfs를 위한 방문배열
	static boolean[][] visited;

	// 여러 조건 체크 메소드를 위한 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// 종료조건 체크하는 메소드
	static boolean allMelt() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (map[r][c] != 0)
					return false;
			}
		}
		return true;
	}

	// 바깥 공기 표시하는 메소드
	static void checkIfOutside() {
		// bfs를 위한 queue
		Queue<int[]> queue = new LinkedList<>();

		// 방문배열 초기화
		visited = new boolean[n][m];

		// 시작지점 on
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;

		// 더 이상 방문 지점이 없을 때까지
		while (!queue.isEmpty()) {
			// 이번에 방문할 좌표
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			// 델타탐색으로 주변 체크
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				// out of index
				if (nr < 0 || nc < 0 || nr >= n || nc >= m)
					continue;

				// 치즈면 제외
				if (map[nr][nc] > 0)
					continue;

				// 바깥 공기이면서 방문하지 않은 좌표라면
				if (!visited[nr][nc]) {
					// 바깥 공기 체크
					isOutside[nr][nc] = true;

					// 다음 탐색 좌표로 추가
					queue.offer(new int[] {nr, nc});

					// 방문 체크
					visited[nr][nc] = true;
				}
			}
		}
	}

	// 주변 바깥 공기수 체크하는 메소드
	static int countOutside(int cr, int cc) {
		// 바깥 공기 수
		int cnt = 0;

		// 델타탐색으로 주변 보기
		for (int dt = 0; dt < 4; dt++) {
			int nr = cr + dr[dt];
			int nc = cc + dc[dt];

			// out of index
			if (nr < 0 || nc < 0 || nr >= n || nc >= m)
				continue;

			// 바깥 공기면 카운트 증가
			if (isOutside[nr][nc])
				cnt++;
		}

		// 카운트 반환
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 모눈종이 크기
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 모눈종이
		map = new int[n][m];

		// 치즈 상태 입력
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 바깥공기인 지점 표시하는 배열, 시작 지점은 무조건 바깥공기
		isOutside = new boolean[n][m];
		isOutside[0][0] = true;

		// 다 녹을 때까지
		boolean terminated = false;

		// 걸리는 시간
		int answer = 0;

		// 체크
		while (!terminated) {
			// 모눈종이 위에 아무것도 없으면
			if (allMelt()) {
				terminated = true;
			} else {
				// 바깥공기 체크
				checkIfOutside();

				// 치즈 녹이기
				for (int r = 0; r < n; r++) {
					for (int c = 0; c < m; c++) {
						// 만약 치즈면
						if (map[r][c] > 0) {
							// 바깥공기 체크
							int outsideCnt = countOutside(r, c);

							// 만약 바깥 공기가 2칸 이상이면
							if (outsideCnt >= 2) {
								// 녹았다고 표시
								map[r][c] = 0;
							}
						}
					}
				}

				// 시간 1초 지남
				answer++;
			}
		}

		// 정답 출력
		System.out.println(answer);
	}
}