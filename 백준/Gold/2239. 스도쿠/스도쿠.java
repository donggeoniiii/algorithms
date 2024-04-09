// 스도쿠

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	// 보드판
	private static int[][] board;

	// 빈칸 리스트
	private static List<int[]> blank;
	private static int blankCnt;

	//
	private static int[] selected;
	private static int[] remain;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new int[9][9];
		blank = new ArrayList<>();
		for (int r = 0; r < 9; r++) {
			String input = br.readLine();
			for (int c = 0; c < 9; c++) {
				int num = input.charAt(c) - '0';
				board[r][c] = num;

				// 만약 숫자가 0이면 리스트에 추가
				if (num == 0) {
					blank.add(new int[] {r, c});
				}
			}
		}
;
		blankCnt = blank.size();

		// 빈칸 채우는 경우의 수 따져보기
		selected = new int[blankCnt];
		fillTheBlank(0);

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				sb.append(board[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static boolean fillTheBlank(int cnt) {
		// base case: 빈칸을 다 채우면 종료
		if (cnt == blankCnt) return true;

		// recursive case: 이번 빈칸에 1부터 9까지 숫자 넣어보기
		int cr = blank.get(cnt)[0];
		int cc = blank.get(cnt)[1];
		for (int num = 1; num <= 9; num++) {
			// 이번에 안겹치는 숫자고 완성 가능하면 true 반환
			board[cr][cc] = num;
			if (valid(cr, cc, num) && fillTheBlank(cnt+1)) return true;
		}

		// 답이 안나오면 다시 원래대로 복구
		board[cr][cc] = 0;
		return false;
	}

	private static boolean valid(int cr, int cc, int num) {
		// 해당 숫자가 같은 열에 있으면 둘 수 없음
		for (int r = 0; r < 9; r++) {
			if (r == cr) continue;
			if (board[r][cc] == num) return false;
		}

		// 해당 숫자가 같은 행에 있으면 둘 수 없음
		for (int c = 0; c < 9; c++) {
			if (c == cc) continue;
			if (board[cr][c] == num) return false;
		}

		// 3x3 칸에 같은 숫자가 있으면 둘 수 없음
		int sr = 3 * (cr / 3);
		int sc = 3 * (cc / 3);
		for (int r = sr; r < sr + 3; r++) {
			for (int c = sc; c < sc + 3; c++) {
				if (r == cr && c == cc) continue;
				if (board[r][c] == num) return false;
			}
		}

		return true;
	}
}