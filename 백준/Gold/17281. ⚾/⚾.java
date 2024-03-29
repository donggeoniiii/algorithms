// 유사야구

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 아무리 봐도 야구 같아 보이지만 9이닝이 아니라 n이닝이다
	private static int n;

	// 타자들의 이닝별 결과
	private static int[][] result;

	// 이번 타순
	private static int[] battingOrder;

	// 선택여부
	private static boolean[] selected;

	// 최고점수
	private static int total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		result = new int[n+1][9];
		for (int r = 1; r <= n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 9; c++) {
				result[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 타순 정하기
		battingOrder = new int[10];
		selected = new boolean[10];

		// 타순 결정하고 시뮬레이션 돌려보기
		total = 0;
		chooseBattingOrder(0);

		// 최고 결과 출력
		System.out.println(total);
	}

	private static void chooseBattingOrder(int order) {
		// base case: 모든 선수의 타순을 다 결정하면 시뮬레이션 on
		if (order == 9) {
			total = Math.max(total, playBall());
		}

		// 4번 타자는 1번으로 정해져있음
		if (order == 3) {
			battingOrder[order] = 0;
			chooseBattingOrder(order+1);
		}

		// recursive case
		for (int player = 1; player < 9; player++) {
			if (selected[player]) continue;

			battingOrder[order] = player;
			selected[player] = true;

			chooseBattingOrder(order + 1);

			selected[player] = false;
		}
	}

	private static int playBall() {
		// 경기 결과
		int totalPoint = 0;

		// 진행 중인 타순
		int curOrder = 0;

		// 이닝별 진행
		for (int inning = 1; inning <= n; inning++) {
			// 아웃 카운트 초기화, 루상 정보 초기화
			int outCnt = 0;
			boolean[] base = new boolean[4];

			// 쓰리아웃 전까지 진행
			while (outCnt < 3) {
				// 이번 타자 결과
				base[0] = true;
				int curBatter = battingOrder[curOrder++];
				int curResult = result[inning][curBatter];

				// 타순이 한바퀴 돌았으면 반영
				if (curOrder == 9) {
					curOrder = 0;
				}

				// 아웃이면 카운트 증가
				if (curResult == 0) {
					outCnt++;
					continue;
				}

				for (int i = 3; i >= 0; i--) {
					if (!base[i]) continue;

					// 들어올 수 있는 주자가 있으면 점수 증가
					if (i + curResult >= 4) totalPoint++;
					// 들어올 수 없으면 루상 위치 반영
					else {
						base[i + curResult] = true;
					}
					
					// 이동한 주자 처리
					base[i] = false;
				}
			}
		}

		return totalPoint;
	}
}