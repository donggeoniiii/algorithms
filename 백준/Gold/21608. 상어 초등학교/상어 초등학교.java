// 상어초등학교

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 자리 수
		int n = Integer.parseInt(br.readLine());

		// 자리 배열
		int[][] room = new int[n][n];

		// 자리 배치 순서 저장할 배열
		int[] order = new int[n * n + 1];

		// 선호정보 저장할 배열
		int[][] like = new int[n * n + 1][4];

		// 선호정보 및 학생 순서 저장
		for (int i = 1; i <= n * n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 순서 저장
			int cur = Integer.parseInt(st.nextToken());
			order[i] = cur;

			// 선호정보 저장
			for (int j = 0; j < 4; j++) {
				like[cur][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 배치를 위해 필요한 델타배열
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		// 순서대로 자리에 배치
		for (int i = 1; i <= n * n; i++) {
			// 이번에 배치할 친구
			int cur = order[i];

			// 배열 돌면서 자리 찾기, 왼쪽 위에서 오른쪽 아래로
			// 현재까지 제일 조건 좋은 위치 정보 기억, r, c, 선호학생 수, 빈칸 수
			int[] bestSpot = new int[4];
			bestSpot[0] = -1;
			bestSpot[1] = -1;
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					// 이번칸의 빈칸수, 선호학생 수
					int curBlankCnt = 0;
					int curLikeCnt = 0;

					// 빈칸이 아니면 스킵
					if (room[r][c] != 0)
						continue;

					for (int dt = 0; dt < 4; dt++) {
						int nr = r + dr[dt];
						int nc = c + dc[dt];

						// out of index
						if (nr < 0 || nc < 0 || nr >= n || nc >= n)
							continue;

						// 해당 칸의 값
						int next = room[nr][nc];

						// 빈칸이면 빈칸 체크
						if (next == 0) {
							curBlankCnt++;
						} else {
							// 아니면 해당 학생이 선호학생 목록에 있는지 체크
							for (int j = 0; j < 4; j++) {
								if (next == like[cur][j])
									curLikeCnt++;
							}
						}
					}
					// 처음 만나는 빈칸을 일단 디폴트로 설정
					if (bestSpot[0] == -1) {
						bestSpot[0] = r;
						bestSpot[1] = c;
						bestSpot[2] = curLikeCnt;
						bestSpot[3] = curBlankCnt;
					} else {
						// 만약 선호학생 수가 기존 값보다 크거나 같으면
						if (curLikeCnt >= bestSpot[2]) {

							// 큰 경우는 그대로 갱신
							if (curLikeCnt > bestSpot[2]) {
								bestSpot[0] = r;
								bestSpot[1] = c;
								bestSpot[2] = curLikeCnt;
								bestSpot[3] = curBlankCnt;
							}
							// 같은 경우는 빈칸 수로 비교
							else {
								if (curBlankCnt > bestSpot[3]) {
									bestSpot[0] = r;
									bestSpot[1] = c;
									bestSpot[3] = curBlankCnt;
								}
							}
						}
					}

				}
			}

			// 탐색이 끝나면 찾은 최고의 자리에 값 입력
			room[bestSpot[0]][bestSpot[1]] = cur;
		}

		// 만족도 총합 구하기
		int totalScore = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {

				// 해당 자리에 있는 학생
				int cur = room[r][c];

				// 해당 자리에 있는 인원의 만족도
				int curScore = 0;

				// 델타탐색
				for (int dt = 0; dt < 4; dt++) {
					int nr = r + dr[dt];
					int nc = c + dc[dt];

					// out of index
					if (nr < 0 || nc < 0 || nr >= n || nc >= n)
						continue;

					// 해당 자리에 있는 학생이 선호학생 목록에 있으면 체크
					for (int i = 0; i < 4; i++) {
						if (room[nr][nc] == like[cur][i]) {
							curScore++;
						}
					}
				}

				// 값 계산
				if (curScore != 0)
					totalScore += (int)Math.pow(10, curScore - 1);
			}
		}

		// 결과 출력
		System.out.println(totalScore);
	}
}