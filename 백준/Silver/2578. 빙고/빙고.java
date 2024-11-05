// 빙고

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] bingo = new int[5][5];
		StringTokenizer st;
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				bingo[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] nums = new int[25];
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int idx = i * 5 + j;
				nums[idx] = Integer.parseInt(st.nextToken());
			}
		}

		for (int round = 1; round <= 25; round++) {
			int cur = nums[round-1];

			findNum(bingo, cur);

			if (isBingo(bingo)) {
				System.out.println(round);
				return;
			}
		}
	}

	private static boolean isBingo(int[][] bingo) {
		int bingoCount = 0;

		for (int r = 0; r < 5; r++) {
			boolean isClear = true;
			for (int c = 0; c < 5; c++) {
				if (bingo[r][c] > 0) {
					isClear = false;
					break;
				}
			}

			if (isClear) bingoCount++;
		}

		for (int c = 0; c < 5; c++) {
			boolean isClear = true;
			for (int r = 0; r < 5; r++) {
				if (bingo[r][c] > 0) {
					isClear = false;
					break;
				}
			}

			if (isClear) bingoCount++;
		}

		int[] dr = {1, -1};
		int[] dc = {1, 1};
		int[] sr = {0, 4};
		int[] sc = {0, 0};

		for (int dt = 0; dt < 2; dt++) {
			boolean isClear = true;

			int cr = sr[dt];
			int cc = sc[dt];

			for (int i = 0; i < 5; i++) {
				int nr = cr + i * dr[dt];
				int nc = cc + i * dc[dt];

				if (bingo[nr][nc] > 0) {
					isClear = false;
					break;
				}
			}

			if (isClear) bingoCount++;
		}

		return bingoCount >= 3;
	}

	private static void findNum(int[][] bingo, int cur) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (bingo[i][j] == cur) {
					bingo[i][j] = 0;
					return;
				}
			}
		}
	}
}