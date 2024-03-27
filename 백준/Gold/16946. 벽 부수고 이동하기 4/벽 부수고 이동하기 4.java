// 벽 부수고 이동하기 4

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 크기
	private static int n, m;

	private static boolean[][] isWall;
	private static int[][] visited;

	// 숫자의 개수
	private static int sectorIdx;

	private static List<Integer> sectorCnt;

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		isWall = new boolean[n][m];
		for (int r = 0; r < n; r++) {
			String input = br.readLine();
			for (int c = 0; c < m; c++) {
				isWall[r][c] = input.charAt(c) == '1';
			}
		}

		// 벽으로 나누어진 구역별 이동 가능 구역 수
		sectorCnt = new ArrayList<>();
		sectorCnt.add(0); // 1-indexed
		sectorIdx = 1;

		// 벽이 아닌 지점에 대해 갈 수 있는 구역 수 카운트하기
		visited = new int[n][m];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (isWall[r][c]) continue;

				if (visited[r][c] > 0) continue;

				bfs(r, c);
			}
		}


		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (isWall[r][c]) sb.append(solve(r, c));
				else sb.append(0);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] {sr, sc});
		visited[sr][sc] = sectorIdx;

		int visitCnt = 1; // 자기 자신 카운트

		while (!queue.isEmpty()){
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (outOfIndex(nr, nc)) continue;

				// 벽이면 패스
				if (isWall[nr][nc]) continue;

				// 방문했으면 패스
				if (visited[nr][nc] > 0) continue;

				// 새 이동 구역 입력
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = sectorIdx;
				visitCnt++;
			}
		}

		// 방문 가능한 지역 개수 입력
		sectorCnt.add(visitCnt % 10);
		sectorIdx++;
	}

	private static int solve(int sr, int sc) {
		// 벽을 둘러싸는 구역의 이름
		HashSet<Integer> set = new HashSet<>();
		int total = 0;

		for (int dt = 0; dt < 4; dt++) {
			int nr = sr + dr[dt];
			int nc = sc + dc[dt];

			if (outOfIndex(nr, nc)) continue;

			if (isWall[nr][nc]) continue;

			set.add(visited[nr][nc]);
		}

		// 자기 자신도 방문 가능하니까 입력하고
		total++;

		// 벽을 둘러싸는 지역의 크기 더하기
		for (int sdx : set) {
			total += sectorCnt.get(sdx);
		}

		return total % 10;
	}

	private static boolean outOfIndex(int r, int c) {
		return r >= n || c >= m || r < 0 || c < 0;
	}
}