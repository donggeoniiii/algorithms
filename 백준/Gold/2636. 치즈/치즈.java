// 치즈

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] cheese = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int time = 0;
		int cheeseCount = getCheeseCount(cheese, n, m);
		while (!allMelted(cheese, n, m)) {
			// 공기와 접촉하는 칸 치즈 녹이기
			melt(cheese);

			// 남은 치즈 세기
			int curCheeseCount = getCheeseCount(cheese, n, m);

			if (curCheeseCount > 0) cheeseCount = curCheeseCount;

			time++;
		}

		System.out.println(time);
		System.out.println(cheeseCount);
	}

	private static void melt(int[][] cheese) {
		int sr = 0;
		int sc = 0;

		Queue<int[]> meltingCheese = new ArrayDeque<>();

		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[cheese.length][cheese[0].length];

		queue.offer(new int[]{sr, sc});
		visited[sr][sc] = true;

		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dt = 0; dt < 4; dt++) {

				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (nr < 0 || nc < 0 || nr >= cheese.length || nc >= cheese[0].length) continue;

				if (visited[nr][nc]) continue;

				if (cheese[nr][nc] == 0) {
					queue.offer(new int[]{nr, nc});
				} else {
					meltingCheese.add(new int[]{nr, nc});
				}

				visited[nr][nc] = true;
			}
		}

		// 이번에 녹는 애들 업데이트
		while (!meltingCheese.isEmpty()) {
			int[] cur = meltingCheese.poll();
			int cr = cur[0];
			int cc = cur[1];

			cheese[cr][cc] = 0;
		}
	}

	private static int getCheeseCount(int[][] cheese, int n, int m) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (cheese[i][j] == 1) count++;
			}
		}
		return count;
	}

	private static boolean allMelted(int[][] cheese, int n, int m) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (cheese[i][j] == 1) return false;
			}
		}

		return true;
	}
}