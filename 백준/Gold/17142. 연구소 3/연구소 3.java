// 연구소3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 연구소 크기
	static int n;

	// 배열
	static int[][] lab = new int[n][n];

	// 바이러스를 떨어트릴 칸 수
	static int m;

	// 전체 비활성/활성 바이러스 좌표
	static ArrayList<int[]> src = new ArrayList<>();

	// 선택한 바이러스 좌표 index
	static boolean[] selected;

	// 최소 방문시간
	static int minTime = 9999;

	// bfs를 위한 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// bfs를 위한 방문배열
	static int[][] visited;

	// bfs
	static int spreadVirus() {
		// bfs를 위한 queue
		Queue<int[]> queue = new LinkedList<>();

		// 이번 탐색에서 전부 덮는 데 걸리는 시간
		int curTime = 0;

		// 방문배열 초기화
		visited = new int[n][n];
		for (int r = 0; r < n; r++) {
			Arrays.fill(visited[r], -1);
		}

		// 시작점 방문체크
		for (int idx = 0; idx < src.size(); idx++) {
			if (selected[idx]) {
				int[] cur = src.get(idx);
				queue.offer(cur);
				visited[cur[0]][cur[1]] = 0;
			}
		}

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {

			// 이번에 방문할 좌표
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

				// 벽이면 스킵
				if (lab[nr][nc] == 1)
					continue;

				// 처음 보는 좌표면
				if (visited[nr][nc] == -1) {

					// 1. 일반 땅일 때
					if (lab[nr][nc] == 0) {
						// 방문체크, 방문 시간은 이전좌표 +1
						visited[nr][nc] = visited[cr][cc] + 1;

						// 최댓값 갱신
						curTime = Math.max(curTime, visited[nr][nc]);

						// queue에 추가
						queue.offer(new int[] {nr, nc});
					}
					// 2. 비활성 바이러스일 때
					else if (lab[nr][nc] == 2) {
						// 방문체크
						visited[nr][nc] = visited[cr][cc] + 1;
						// 뻗어나가긴 해야 하니까 queue에 추가
						queue.offer(new int[] {nr, nc});
					}
				}
			}
		}

		// 전체 탐색이 가능한 경우인지 찾기
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				// 만약 탐색하지 않은 지점이 남아있으면
				if (lab[r][c] == 0 && visited[r][c] == -1) {
					// 전체에 다 퍼지지 않은 경우니까 최솟값 갱신하지 않고 그대로 종료
					return -1;
				}
			}
		}

		// 전체 탐색이 되는 경우면 그대로 시간 반환
		return curTime;
	}

	// 백트래킹
	static void findFastestSpreadTime(int cnt, int start) {
		// pruning: 최소시간인 방법을 찾으면 종료
		if (minTime == 0)
			return;

		// base case: 만약 바이러스를 뿌릴 m개 좌표를 다 정하면
		if (cnt >= m) {

			// bfs on, 바이러스 확산 시간 계산
			int curTime = spreadVirus();

			// 최솟값 갱신
			if (curTime != -1)
				minTime = Math.min(minTime, curTime);

			return;
		}

		// recursive case
		for (int idx = start; idx < src.size(); idx++) {

			// 아직 선택하지 않은 값이면 이번에 선택
			if (!selected[idx]) {

				selected[idx] = true;

				// 다음 선택으로 이동
				findFastestSpreadTime(cnt + 1, idx + 1);

				// 다른 선택을 위해 선택 해제
				selected[idx] = false;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 연구소 크기, 바이러스 투하 위치 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 연구소 배열 초기화
		lab = new int[n][n];

		// 연구소 입력
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				lab[r][c] = Integer.parseInt(st.nextToken());

				// 만약 바이러스 투하 지점이면 리스트에 추가
				if (lab[r][c] == 2)
					src.add(new int[] {r, c});
			}
		}

		// 선택 가능한 바이러스 투하 지점의 선택여부
		selected = new boolean[src.size()];

		// 최소시간 찾기
		findFastestSpreadTime(0, 0);

		// 정답 출력
		if (minTime == 9999) {
			System.out.println(-1);
		} else {
			System.out.println(minTime);
		}
	}
}