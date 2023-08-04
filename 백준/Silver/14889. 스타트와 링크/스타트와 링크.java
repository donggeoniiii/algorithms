// 스타트와 링크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 사람 수
	static int n;

	// 능력치 저장 배열
	static int[][] stat;

	// 스타트 팀 멤버
	static int[] selected;

	// 전체 스탯 합
	static int statTotal;

	// 스타트팀 - 링크팀 최소 점수차
	static int shortestGap;

	// 황밸찾기 알고리즘
	static void findShortestStatGap(int cnt, int start) {
		// pruning: 이미 gap이 0인 경우가 나오면 종료
		if (shortestGap == 0)
			return;

		// base case: n/2명 스타트팀에서 다 뽑으면
		if (cnt == n / 2) {
			// 스타트 팀 점수 계산
			int score = 0;
			boolean[] isStartTeam = new boolean[n];
			for (int i = 0; i < n / 2; i++) {
				for (int j = i + 1; j < n / 2; j++) {
					int a = selected[i];
					int b = selected[j];

					// 링크팀도 누군지 알아야 하니까
					isStartTeam[a] = true;
					isStartTeam[b] = true;
					score += stat[a][b] + stat[b][a];
				}
			}

			// 링크 팀 점수 계산
			int[] notSelected = new int[n / 2];
			int idx = 0;
			int opposite = 0;
			for (int i = 0; i < n; i++) {
				if (!isStartTeam[i]) {
					notSelected[idx++] = i;
				}
			}
			for (int i = 0; i < n / 2; i++) {
				for (int j = i + 1; j < n / 2; j++) {
					int a = notSelected[i];
					int b = notSelected[j];

					opposite += stat[a][b] + stat[b][a];
				}
			}

			// 점수 차이 계산
			int curGap = Math.abs(opposite - score);

			// 최솟값 갱신
			shortestGap = Math.min(curGap, shortestGap);

			return;
		}

		// recursive case
		for (int i = start; i < n; i++) {
			// 선택
			selected[cnt] = i;

			// 다음 선택을 위해 이동
			findShortestStatGap(cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 사람 수
		n = Integer.parseInt(br.readLine());

		// 능력치표, 능력치 저장
		stat = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				stat[i][j] = Integer.parseInt(st.nextToken());
				statTotal += stat[i][j];
			}
		}

		// 탐색을 위한 변수, 배열 초기화
		selected = new int[n / 2];
		shortestGap = Integer.MAX_VALUE;

		// 최소차이 찾기
		findShortestStatGap(0, 0);

		// 출력
		System.out.println(shortestGap);
	}
}