// 사다리 조작

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static int n, m, h;

	private static boolean[][] ladder;

	private static List<int[]> available;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		ladder = new boolean[h+1][n+1];
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			ladder[row][col] = true;
		}

		available = new LinkedList<>();
		for (int r = 1; r <= h; r++) {
			for (int c = 1; c <= n-1; c++) {

				// 이미 연결된 부분, 연결된 부분과 닿는 부분 제외
				if (ladder[r][c-1] || ladder[r][c] || ladder[r][c+1])
					continue;

				available.add(new int[] {r, c});
			}
		}

		// 일단 추가 안해도 제대로 타지는지 확인
		if (solve()) {
			System.out.println(0);
			return;
		}

		// 3칸 선택해서 출발
		int minCnt = 4;
		for (int[] cur1 : available) {
			ladder[cur1[0]][cur1[1]] = true;

			if (solve()) {
				minCnt = 1;
			}
			for (int[] cur2 : available) {
				if (Arrays.equals(cur1, cur2)) continue;
				ladder[cur2[0]][cur2[1]] = true;

				if (solve()) {
					minCnt = Math.min(minCnt, 2);
				}
				for (int[] cur3 : available) {
					if (Arrays.equals(cur1, cur3) || Arrays.equals(cur2, cur3)) continue;
					ladder[cur3[0]][cur3[1]] = true;

					if (solve()) {
						minCnt = Math.min(minCnt, 3);
					}

					// 다음 탐색을 위해 체크 해제
					ladder[cur3[0]][cur3[1]] = false;
				}
				ladder[cur2[0]][cur2[1]] = false;
			}
			ladder[cur1[0]][cur1[1]] = false;
		}

		// 안되는 경우는 -1 출력
		if (minCnt > 3) {
			minCnt = -1;
		}
		System.out.println(minCnt);
	}

	private static boolean solve() {
		// 세로열 기준으로 탐색
		for (int c = 1; c <= n; c++) {
			int curCol = c;
			for (int r = 1; r <= h; r++) {
				if (ladder[r][curCol-1]) {
					curCol--;
				}
				else if (ladder[r][curCol]) {
					curCol++;
				}
			}

			// 만약 이번 시행에서 제자리로 오는데 실패하면 false
			if (curCol != c) {
				return false;
			}
		}

		return true;
	}
}