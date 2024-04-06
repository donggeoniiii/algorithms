// 열쇠

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 지도의 높이와 너비
	private static int h, w;

	private static char[][] land;

	// 문을 열 수 있는지
	private static boolean[] isOpen;

	// 열쇠가 없어서 가지 못하는 문의 좌표 모음
	private static Queue<int[]>[] door;

	// 방문 배열
	private static boolean[][] visited;

	private static final int[] dr = {-1, 1, 0, 0};
	private static final int[] dc = {0, 0, -1, 1};
	private static int docsCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			// 쿠셔닝
			land = new char[h+2][w+2];
			for (int r = 0; r < h + 2; r++) {
				Arrays.fill(land[r], '.');
			}

			for (int r = 1; r <= h; r++) {
				String input = br.readLine();
				for (int c = 1; c <= w; c++) {
					land[r][c] = input.charAt(c-1);
				}
			}

			door = new Queue[26];
			for (int i = 0; i < 26; i++) {
				door[i] = new LinkedList<>();
			}

			// 키 입력
			isOpen = new boolean[26];
			char[] key = br.readLine().toCharArray();
			for (char k : key) {
				if (k == '0') break;
				isOpen[k - 'a'] = true;
			}

			// 문서 카운트 초기화
			docsCnt = 0;

			// 시작
			visited = new boolean[h+2][w+2];
			findDocs();

			sb.append(docsCnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void findDocs() {
		Queue<int[]> queue = new LinkedList<>();

		// 시작점 입력
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (outOfIndex(nr, nc)) continue;

				char next = land[nr][nc];

				// 벽이면 스킵
				if (next == '*') continue;

				// 방문했으면 스킵
				if (visited[nr][nc]) continue;

				// 문인데 못 열면 문 목록에 추가하고 스킵
				if ('A' <= next && next <= 'Z') {
					int ddx = next - 'A';
					if (!isOpen[ddx]) {
						door[ddx].offer(new int[] {nr, nc});
						continue;
					}
				}

				// 열쇠면 열 수 있는 문 탐색 queue에 추가
				if ('a' <= next && next <= 'z') {
					int ddx = next - 'a';

					// 다음에 만나는 해당 알파벳 문은 열 수 있다고 표시
					isOpen[ddx] = true;

					// 그전에 못 열었던 문들 열어서 탐색 queue에 추가
					while (!door[ddx].isEmpty()) {
						int[] cd = door[ddx].poll();
						queue.offer(cd);
						visited[cd[0]][cd[1]] = true;
					}
				}

				// 문서면 카운트 증가
				if (next == '$') docsCnt++;


				// 현재 좌표 탐색을 위해 queue에 추가
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}

		}
	}

	private static boolean outOfIndex(int r, int c) {
		return r < 0 || c < 0 || r >= h + 2 || c >= w + 2;
	}
}