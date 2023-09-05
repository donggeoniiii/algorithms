// 연구소

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

	// 연구소 배열
	static int[][] lab;

	// 연구소 내 바이러스 투하가 가능한 지점 목록
	static ArrayList<int[]> src = new ArrayList<>();

	// 그 중 바이러스 투하할 지점의 수
	static int m;

	// 바이러스 투하할 지점의 index를 표시하는 배열
	static boolean[] selected;

	// bfs를 위한 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// 필요한 시간의 최솟값을 저장하는 변수
	static int minTime;

	// 백트래킹 알고리즘
	static void findFastestTime(int start, int cnt) {

		// base case: m개를 다 고르면
		if (cnt >= m) {

			// bfs를 통해 최소시간 찾기
			Queue<int[]> queue = new LinkedList<>();

			// bfs를 위한 방문배열
			int[][] visited = new int[n][n];

			for (int r = 0; r < n; r++) {
				Arrays.fill(visited[r], -1);
			}

			// 시작점 방문체크, queue에 넣기
			for (int idx = 0; idx < src.size(); idx++) {
				if (selected[idx]) {
					int[] cur = src.get(idx);
					queue.offer(cur);
					visited[cur[0]][cur[1]] = 0;
				}
			}

			// 더 방문할 지점이 없을 때까지
			while (!queue.isEmpty()) {

				// 이번에 탐색할 좌표
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

					// 벽이면 못가니까
					if (lab[nr][nc] == 1)
						continue;

					// 처음보는 좌표면
					if (visited[nr][nc] == -1) {

						// 방문체크하고 queue에 추가
						visited[nr][nc] = visited[cr][cc] + 1;
						queue.offer(new int[] {nr, nc});
					}
				}

			}

			// 방문 불가능한 지점이 있는지 체크하면서 방문까지 걸리는 시간 체크
			int curTime = -1;
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					// 이번 방문의 최댓값 갱신
					curTime = Math.max(visited[r][c], curTime);

					// 만약 방문하지 않은 지점이 벽이 아니면
					if (visited[r][c] == -1 && lab[r][c] != 1)
						// 이번 탐색은 유효하지 않으므로 최솟값 갱신은 x
						return;
				}
			}

			// 최솟값 갱신
			minTime = Math.min(curTime, minTime);

			return;
		}

		// recursive case: 투하 장소 m개 선택
		for (int idx = start; idx < src.size(); idx++) {

			if (!selected[idx]) {
				// 선택한다
				selected[idx] = true;
				findFastestTime(start + 1, cnt + 1);
				// 선택하지 않는 경우를 위해 체크 해제
				selected[idx] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫줄: 연구소 크기, 바이러스 투하할 위치 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 연구소 배열
		lab = new int[n][n];

		// 연구소 상황 입력
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				lab[r][c] = Integer.parseInt(st.nextToken());

				// 만약 값이 2면 바이러스 투하 가능 위치이므로
				if (lab[r][c] == 2)
					src.add(new int[] {r, c});
			}
		}

		// 바이러스를 투하할 지점 표시할 boolean 배열
		selected = new boolean[src.size()];

		// 최소 시간
		minTime = 9999;

		// 최소시간 찾기
		findFastestTime(0, 0);

		// 정답 출력
		System.out.println(minTime == 9999 ? -1 : minTime);
	}
}