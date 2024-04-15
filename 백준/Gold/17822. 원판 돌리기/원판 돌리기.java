// 원판 돌리기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int[][] circle;

	private static boolean[][] deleted;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		// 원판
		circle = new int[n+1][m];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 지워진 숫자 표시할 배열
		deleted = new boolean[n+1][m];

		for (int turn = 1; turn <= t; turn++) {
			// 이번 회전 정보
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			// 번호가 x의 배수인 원판을 d 방향으로 k칸 회전
			if (d == 0) {
				for (int i = x; i <= n; i += x) {
					for (int move = 0; move < k; move++) {
						int temp1 = circle[i][m-1];
						boolean temp2 = deleted[i][m-1];
						for (int j = m-1; j > 0; j--) {
							circle[i][j] = circle[i][j-1];
							deleted[i][j] = deleted[i][j-1];
						}
						circle[i][0] = temp1;
						deleted[i][0] = temp2;
					}
				}
			}
			else {
				for (int i = x; i <= n; i += x) {
					for (int move = 0; move < k; move++) {
						int temp1 = circle[i][0];
						boolean temp2 = deleted[i][0];
						for (int j = 0; j < m-1; j++) {
							circle[i][j] = circle[i][j+1];
							deleted[i][j] = deleted[i][j+1];
						}
						circle[i][m-1] = temp1;
						deleted[i][m-1] = temp2;
					}
				}
			}

			// 원판에 수가 남아있지 않으면 더 해볼 필요 없음
			if (allDeleted()) break;

			// 남아 있으면 인접한 수 지우기
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};

			List<int[]> delList = new ArrayList<>();

			for (int cr = 1; cr <= n; cr++) {
				for (int cc = 0; cc < m; cc++) {
					if (deleted[cr][cc]) continue;

					boolean isEqual = false;

					for (int dt = 0; dt < 4; dt++) {
						int nr = cr + dr[dt];
						int nc = cc + dc[dt];

						if (nr > n || nr <= 0) continue;
						if (nc == m) nc = 0;
						if (nc < 0) nc = m-1;

						// 지워졌으면 패스
						if (deleted[nr][nc]) continue;

						// 인접한 수가 같다면 지운다
						if (circle[nr][nc] == circle[cr][cc]) {
							delList.add(new int[] {nr, nc});
							isEqual = true;
						}
					}

					if (isEqual) delList.add(new int[] {cr, cc});
				}
			}

			// 인접하는 애들이 있으면 이번에 인접한 애들 싹 다 지우기
			if (!delList.isEmpty()) {
				for (int[] cur : delList) {
					deleted[cur[0]][cur[1]] = true;
				}
			}
			else {
				// 없으면 평균 내서 해당 값보다 큰지 비교
				double avg = 0;
				int cnt = 0;
				for (int i = 1; i <= n; i++) {
					for (int j = 0; j < m; j++) {
						if (!deleted[i][j]) {
							avg += circle[i][j];
							cnt++;
						}
					}
				}

				avg /= cnt;

				for (int i = 1; i <= n; i++) {
					for (int j = 0; j < m; j++) {
						if (deleted[i][j]) continue;

						if (circle[i][j] > avg) circle[i][j]--;
						else if (circle[i][j] < avg) circle[i][j]++;
					}
				}
			}
		}

		// 총 합 구하기
		int total = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < m; j++) {
				if (deleted[i][j]) continue;
				total += circle[i][j];
			}
		}

		System.out.println(total);
	}

	private static boolean allDeleted() {
		for (int i = 1; i < circle.length; i++) {
			for (int j = 0; j < circle[i].length; j++) {
				if (!deleted[i][j]) return false;
			}
		}

		return true;
	}
}