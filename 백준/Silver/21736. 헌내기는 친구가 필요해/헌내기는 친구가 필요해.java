// 헌내기는 친구가 필요해

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 캠퍼스의 크기
	private static int n, m;

	// 캠퍼스 정보
	private static char[][] campus;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int sr = 0;
		int sc = 0;

		campus = new char[n][m];
		for (int r = 0; r < n; r++) {
			String input = br.readLine();
			for (int c = 0; c < m; c++) {
				char ch = input.charAt(c);
				campus[r][c] = ch;
				if (ch == 'I') {
					sr = r;
					sc = c;
				}
			}
		}

		// bfs
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];

		queue.offer(new int[] {sr, sc});
		visited[sr][sc] = true;

		int peopleCnt = 0;
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (outOfIndex(nr, nc)) continue;

				// 벽이면 스킵
				if (campus[nr][nc] == 'X') continue;

				// 이미 방문했으면 스킵
				if (visited[nr][nc]) continue;

				// 혹시 사람이면 방문체크
				if (campus[nr][nc] == 'P') peopleCnt++;

				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}

		System.out.println(peopleCnt > 0 ? peopleCnt : "TT");
	}

	private static boolean outOfIndex(int r, int c) {
		return r < 0 || r >= n || c < 0 || c >= m;
	}
}