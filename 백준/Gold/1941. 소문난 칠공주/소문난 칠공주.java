// 소문난 칠공주

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static boolean[][] isDasom;

	private static int[] selected;

	// 조합 개수
	private static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		isDasom = new boolean[5][5];
		selected = new int[25];

		for (int r = 0; r < 5; r++) {
			String s = br.readLine();
			for (int c = 0; c < 5; c++) {
				isDasom[r][c] = s.charAt(c) == 'S';
			}
		}

		findPrincesses(0, 0, 0);

		System.out.println(answer);
	}

	// 백트래킹 알고리즘
	private static void findPrincesses(int start, int yCnt, int selCnt) {
		// pruning: 도연파가 4명 이상인 케이스는 이미 안 됨
		if (yCnt >= 4) {
			return;
		}

		// base case: 7공주를 다 찾으면 종료
		if (selCnt >= 7) {
			// 가져온 조합이 7공주가 가능한지 확인
			if (available()) {
				answer++;
			}
			return;
		}

		// recursive case
		for (int i = start; i < 25; i++) {
			// 선택하기
			selected[selCnt] = i;

			// 다음 선택을 위해 이동
			int r = i / 5;
			int c = i % 5;
			int newYCnt = yCnt + ((isDasom[r][c]) ? 0 : 1);

			findPrincesses(i + 1, newYCnt, selCnt + 1);
		}
	}

	private static boolean available() {
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		int sr = selected[0] / 5;
		int sc = selected[0] % 5;

		// 붙어있음이 확인된 공주 수
		int connectCnt = 0;

		// 시작점 추가
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {sr, sc});
		connectCnt++;

		// 방문체크
		boolean[] visited = new boolean[7];
		visited[0] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];

				if (nr >= 5 || nc >= 5 || nr < 0 || nc < 0)
					continue;

				int ni = nr * 5 + nc;
				for (int i = 0; i < 7; i++) {
					// 선택하지 않았으면 스킵
					if (selected[i] != ni)
						continue;

					// 방문했으면 스킵
					if (visited[i])
						continue;

					queue.offer(new int[] {nr, nc});
					visited[i] = true;
					connectCnt++;
				}
			}
		}

		return connectCnt == 7;
	}
}