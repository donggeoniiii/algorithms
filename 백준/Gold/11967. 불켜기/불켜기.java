// 불켜기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 방 개수, 스위치 정보 개수
	private static int n, m;

	private static boolean[][] visited;

	// 스위치 켜진 방 체크
	private static boolean[][] isOn;

	// 방 별로 켤 수 있는 방 스위치 목록
	private static List<int[]>[][] light;

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		light = new LinkedList[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				light[i][j] = new LinkedList<>();
			}
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			light[x][y].add(new int[] {a, b});
		}

		// 시작점부터 탐색
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[n+1][n+1];

		queue.offer(new int[] {1, 1});
		visited[1][1] = true;

		// 불 켜진 방 확인, 시작점은 불이 켜져 있음
		isOn = new boolean[n+1][n+1];
		isOn[1][1] = true;

		// 더 방문할 수 있는 방이 없을 때까지
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			// 연결된 방 불 다 켜기, 불 켠 방 카운트 증가
			for (int[] next : light[cr][cc]) {
				int nr = next[0];
				int nc = next[1];

				// 이미 갔다온 방이면
				if (visited[nr][nc]) continue;

				// 불 켜기
				isOn[next[0]][next[1]] = true;

				// 불 켰다고 그 방에 다 갈 수 있는 건 아니니까
				if (!available(nr, nc)) continue;

				visited[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}

			// 주변에 이동 가능한지 판단하기
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (outOfIndex(nr, nc)) continue;

				// 방문한 경우 제외
				if (visited[nr][nc]) continue;

				// 불이 꺼져 있으면 이동 불가
				if (!isOn[nr][nc]) continue;

				// 이동 가능한 방 입력하기
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}

		int roomCnt = 0;
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				if (isOn[r][c]) roomCnt++;
			}
		}
		System.out.println(roomCnt);
	}

	private static boolean available(int r, int c) {
		for (int dt = 0; dt < 4; dt++) {
			int nr = r + dr[dt];
			int nc = c + dc[dt];

			if (outOfIndex(nr, nc)) continue;

			if (visited[nr][nc]) {
				return true;
			}
		}

		// 주변에 방문한 방이 없으면 불은 켜지만 이동 queue에 추가는 x
		return false;
	}

	private static boolean outOfIndex(int r, int c) {
		return r > n || c > n || r <= 0 || c <= 0;
	}
}