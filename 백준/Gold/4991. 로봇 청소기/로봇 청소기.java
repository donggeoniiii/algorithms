// 로봇 청소기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int w, h;

	private static char[][] room;

	private static int[][] dirt;
	private static int dnt; // dirtCnt

	// 점과 점 사이 최소거리
	private static int[][] dist;

	private static boolean[] cleansed;

	private static int minDist;

	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			w = Integer.parseInt(st.nextToken());
			if (w == 0) break;
			h = Integer.parseInt(st.nextToken());

			room = new char[h][w];
			dirt = new int[11][2]; // 시작점 + 최대 10개
			dnt = 1;
			for (int r = 0; r < h; r++) {
				String input = br.readLine();
				for (int c = 0; c < w; c++) {
					char status = input.charAt(c);
					room[r][c] = status;

					// 더러운 칸 및 시작점 체크
					if (status == '*') {
						dirt[dnt][0] = r;
						dirt[dnt++][1] = c;
					}
					if (status == 'o') {
						dirt[0][0] = r;
						dirt[0][1] = c;
					}
				}
			}

			// 시작점 + 각 더러운 점 사이 거리 구하기
			dist = new int[dnt][dnt];
			boolean impossible = false;
			for (int i = 0; i < dnt; i++) {
				for (int j = i + 1; j < dnt; j++) {
					int sr = dirt[i][0];
					int sc = dirt[i][1];
					int tr = dirt[j][0];
					int tc = dirt[j][1];

					int curDist = findDistance(sr, sc, tr, tc);

					if (curDist == 404) {
						impossible = true;
					}
					dist[i][j] = curDist;
					dist[j][i] = curDist;
				}
			}

			// 도달 안되는 지점이 있다면 깨끗해질 수 없는 점이 있으므로 -1 반환
			if (impossible) {
				sb.append(-1).append("\n");
				continue;
			}

			// 두 지점간 최소 거리 구하기
			cleansed = new boolean[dnt];
			minDist = 401;
			findShortcut(0, 0, 0);

			sb.append(minDist).append("\n");
		}
		System.out.println(sb);
	}

	private static int findDistance(int sr, int sc, int tr, int tc) {
		Queue<int[]> queue = new LinkedList<>();
		int[][] visited = new int[h][w];
		for (int r = 0; r < h; r++) {
			Arrays.fill(visited[r], -1);
		}

		queue.offer(new int[] {sr, sc});
		visited[sr][sc] = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (outOfIndex(nr, nc)) continue;

				if (visited[nr][nc] >= 0) continue;

				// 도착지점 찾으면 종료
				if (nr == tr && nc == tc) {
					return visited[cr][cc] + 1;
				}

				// 가구는 못지나가니까
				if (room[nr][nc] == 'x') continue;

				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = visited[cr][cc] + 1;
			}
		}

		// 지점에 도달 못하면 방문 못하는 경우 있음을 표시
		return 404;
	}

	private static boolean outOfIndex(int r, int c) {
		return r < 0 || r >= h || c < 0 || c >= w;
	}

	private static void findShortcut(int cnt, int total, int cdx) {
		// base case: 모든 지점 최소거리 구하기
		if (cnt == dnt - 1) {
			minDist = Math.min(minDist, total);
		}

		// recursive case
		for (int i = 1; i < dnt; i++) {
			if (cleansed[i]) continue;

			cleansed[i] = true;
			findShortcut(cnt+1, total + dist[cdx][i], i);
			cleansed[i] = false;
		}
	}

}