// 나이트 투어

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 좌표를 2차원 배열 형태로 바꿔 저장
		int[][] move = new int[36][2];
		for (int i = 0; i < 36; i++) {
			String cur = br.readLine();
			int cr = 6 - (cur.charAt(1) - '0');
			int cc = cur.charAt(0) - 'A';
			move[i][0] = cr;
			move[i][1] = cc;
		}

		// 딱 한번만 방문하는지 확인하기 위한 방문체크
		boolean[][] visited = new boolean[6][6];
		
		for (int i = 0; i < 36; i++) {
			int[] cur = move[i];
			int cr = cur[0];
			int cc = cur[1];

			// 다음 좌표로 유효하게 이어지는지 확인
			int[] target;
			if (i < 35) {
				target = move[i+1];
			} else {
				target = move[0];
			}

			int tr = target[0];
			int tc = target[1];

			// 다음 좌표로 이동 못하면 유효하지 않음
			if (notFound(cr, cc, tr, tc)) {
				System.out.println("Invalid");
				return;
			}

			// 원점으로 돌아가기 전에 다른 칸을 두번째 방문하면 유효하지 않음
			if (visited[tr][tc]) {
				System.out.println("Invalid");
				return;
			}

			// 방문 체크하고 다음으로
			visited[tr][tc] = true;
		}

		// 마지막까지 왔으면 Valid 출력
		System.out.println("Valid");
	}

	private static boolean notFound(int cr, int cc, int tr, int tc) {
		// 나이트의 이동
		int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
		int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

		for (int dt = 0; dt < 8; dt++) {
			int nr = cr + dr[dt];
			int nc = cc + dc[dt];

			// 발견하면 false
			if (nr == tr && nc == tc) return false;
		}

		// 발견하지 못하면 true
		return true;
	}
}