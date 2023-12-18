import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 토마토 보관 격자 크기
	static int m, n;

	// 토마토 보관 격자
	static int[][] box;

	// bfs를 위한 방문 배열
	static int[][] visited;

	// 탐색을 위한 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		init();
		simulation();
		answer();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 2 <= m, n <= 1000
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		box = new int[n][m];
		visited = new int[n][m];

		// 토마토 격자 상태 입력, -1: 빈칸, 0: 안익음, 1: 익음
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < m; c++) {
				box[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static void simulation() {
		// bfs를 위한 queue
		Queue<int[]> queue = new LinkedList<>();

		// 탐색 중에 1(익음)을 찾으면 주변 탐색하기
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (box[r][c] == 1) {
					// 시작점 추가
					queue.offer(new int[] {r, c});
				}
			}
		}

		// bfs 진행
		bfs(queue);
	}

	private static void bfs(Queue<int[]> queue) {
		// 더 빈 지점이 없을 때까지
		while (!queue.isEmpty()) {
			// 탐색 지점
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			// 주변 탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				// out of index
				if (nr < 0 || nc < 0 || nr >= n || nc >= m) {
					continue;
				}

				// 빈칸이면 스킵
				if (box[nr][nc] == -1) {
					continue;
				}

				// 이미 익은 토마토면 스킵
				if (box[nr][nc] == 1) {
					continue;
				}

				// 이미 본 익은 토마토면 스킵
				if (visited[nr][nc] > 0) {
					continue;
				}

				// 안 익은 토마토면 익음 처리, 익은 날짜 표시
				visited[nr][nc] = visited[cr][cc] + 1;

				// queue에 추가
				queue.offer(new int[] {nr, nc});

			}

			// 방문상태 체크
			// print();
		}
	}

	private static void print() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				System.out.print(visited[r][c] + " ");
			}
			System.out.println();
		}
	}

	private static void answer() {
		int answer = -1;

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				// 아직 덜 익은 토마토가 남아있으면 그대로 출력하고 종료
				if (visited[r][c] == 0 && box[r][c] == 0) {
					System.out.println(-1);
					return;
				}

				// 최댓값 갱신
				if (answer < visited[r][c]) {
					answer = visited[r][c];
				}
			}
		}

		// 처음부터 다 익어 있었던 격자면 0, 아니면 최댓값 출력
		System.out.println((answer == -1) ? 0 : answer);
	}
}