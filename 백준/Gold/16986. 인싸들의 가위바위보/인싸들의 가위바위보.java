// 인싸들의 가위바위보

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 인싸 가위바위보의 손동작 수, 필요 승수
	private static int n, k;

	// 상성표
	private static int[][] win;

	// 지우, 경희, 민호의 손동작
	private static int[][] pick;

	// 승수와 경기 수
	private static int[] wins;
	private static int[] rounds;

	// 지우의 승리 여부
	private static boolean jiwooWins;

	// 이미 낸 패 체크
	private static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		win = new int[n+1][n+1];
		for (int r = 1; r <= n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= n; c++) {
				win[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 경희와 민호의 20라운드치 패 순서
		pick = new int[3][20];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 20; i++) {
			pick[1][i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 20; i++) {
			pick[2][i] = Integer.parseInt(st.nextToken());
		}

		// 지우가 낼 수 있는 가능한 경우 모두 진행
		selected = new boolean[n+1];
		simulation(0);

		System.out.println(jiwooWins ? 1 : 0);
	}

	private static void simulation(int sel) {
		// pruning: 가능해진 경우가 나오면 종료
		if (jiwooWins) return;

		// base case: n개 낼 순서 고르면 게임 시뮬레이션 진행
		if (sel == n) {
			if (game()) jiwooWins = true;
		}

		// recursive case
		for (int i = 1; i <= n; i++) {
			if (selected[i]) continue;

			selected[i] = true;
			pick[0][sel] = i;
			simulation(sel + 1);
			selected[i] = false;
			pick[0][sel] = i;
		}
	}

	private static boolean game() {
		// 플레이어별 승수, 플레이한 라운드 수
		wins = new int[3];
		rounds = new int[3];

		// 지우, 경희부터 스타트
		int p1 = 0;
		int p2 = 1;

		while (true) {
			int pick1 = pick[p1][rounds[p1]++];
			int pick2 = pick[p2][rounds[p2]++];
			int result = win[pick1][pick2];

			if (result == 2) {
				wins[p1]++;
				p2 = 3 - p1 - p2;
			}
			// 비기면 뒷차례가 이김
			else if (result == 1) {
				int winner = Math.max(p1, p2);
				if (winner == p1) {
					p2 = 3 - p1 - p2;
				}
				else {
					p1 = 3 - p1 - p2;
				}
				wins[winner]++;
			}
			else {
				wins[p2]++;
				p1 = 3 - p1 - p2;
			}

			// 종료조건 체크
			if (wins[0] == k) return true;
			if (wins[1] == k || wins[2] == k) return false;
			if (rounds[0] == n) return false;
		}
	}
}