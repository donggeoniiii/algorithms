// 알고스팟

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 미로의 크기
	private static int m, n;

	// 미로
	private static int[][] maze;

	// 벽을 부수는 횟수
	private static int[][] dist;

	private static final int INF = 987987987;

	// 델타배열
	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};

	private static boolean outOfIndex(int r, int c) {
		return r > n || c > m || r <= 0 || c <= 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		maze = new int[n+1][m+1];
		for (int r = 1; r <= n; r++) {
			String input = br.readLine();
			for (int c = 1; c <= m; c++) {
				maze[r][c] = input.charAt(c-1) - '0';
			}
		}

		dist = new int[n+1][m+1];
		for (int r = 1; r <= n; r++) {
			Arrays.fill(dist[r], INF);
		}
		dist[1][1] = 0;

		// 되도록 뚫린 길부터 탐사, 최단경로로 가면서 부숴야 하는 벽 개수 찾기
		System.out.println(solve());
	}

	private static int solve() {
		// 되도록 뚫린 길부터 탐사하기 위한 우선순위 큐
		PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));

		// 시작점 입력
		queue.offer(new int[] {1, 1, maze[1][1]});

		// 도착하거나 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int curCost = cur[2];

			// 이미 최소 벽깨기 개수보다 더 깼으면 볼 필요 x
			if (dist[cr][cc] < curCost) continue;

			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (outOfIndex(nr, nc)) continue;

				// 만약 이번 코스가 이전보다 더 많은 벽을 부숴야 하면 스킵
				int newCost = curCost + maze[nr][nc];
				if (newCost >= dist[nr][nc]) continue;

				dist[nr][nc] = newCost;

				queue.offer(new int[] {nr, nc, newCost});
			}
		}

		return dist[n][m];
	}
}