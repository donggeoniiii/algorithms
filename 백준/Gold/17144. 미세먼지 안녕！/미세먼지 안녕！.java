// 미세먼지 안녕

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 방의 크기, 공기 청정기 가동 시간
	static int R;
	static int C;
	static int T;

	// 방 배열
	static int[][] room;

	// 공기청정기 자리 저장
	static int[][] airPurifier = new int[2][2];

	// 델타 탐색을 위한 델타 배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// 공기청정기 돌리기
	static void purify() {

		// 윗칸 시작점
		int sr = airPurifier[0][0];

		// 테두리 값 돌리기 - 위쪽
		for (int r = sr - 1; r > 0; r--) {
			room[r][0] = room[r - 1][0];
		}
		for (int c = 0; c < C - 1; c++) {
			room[0][c] = room[0][c + 1];
		}
		for (int r = 0; r < sr; r++) {
			room[r][C - 1] = room[r + 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) {
			room[sr][c] = room[sr][c - 1];
		}

		// 청정기에 들어간 미세먼지는 삭제
		room[sr][1] = 0;

		// 아랫칸 시작점
		sr = airPurifier[1][0];

		// 테두리 값 돌리기 - 아래쪽
		for (int r = sr + 1; r < R - 1; r++) {
			room[r][0] = room[r + 1][0];
		}
		for (int c = 0; c < C - 1; c++) {
			room[R - 1][c] = room[R - 1][c + 1];
		}
		for (int r = R - 1; r > sr; r--) {
			room[r][C - 1] = room[r - 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) {
			room[sr][c] = room[sr][c - 1];
		}

		// 청정기에 들어간 미세먼지는 삭제
		room[sr][1] = 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 방의 크기, 공기 청정기 가동 시간
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		// 방 배열 입력
		int diffIdx = 0;
		room = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());

				// -1면 공기청정기 자리 저장, 열은 1열(col 0) 고정이니까
				if (room[r][c] == -1) {
					airPurifier[diffIdx++][0] = r;
					room[r][c] = 0;
				}
			}
		}

		// t초동안 공기청정기 틀기
		for (int time = 0; time < T; time++) {
			// 이번 시점에서 확산 되는 미세먼지 상황을 저장할 배열
			int[][] temp = new int[R][C];

			// 확산되는 공기 체크
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {

					// 일단 몇 군데 미세먼지가 퍼지는지 확인
					int directionCnt = 0;

					// 미세먼지 퍼트리기
					for (int dt = 0; dt < 4; dt++) {
						int nr = r + dr[dt];
						int nc = c + dc[dt];

						// out of index면 스킵
						if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
							continue;
						}

						// index가 공기청정기 자리면 스킵
						if (nc == 0 && (nr == airPurifier[0][0] || nr == airPurifier[1][0])) {
							continue;
						}

						// 아니면 미세먼지 뿌리기, 뿌리는 칸수 확인
						temp[nr][nc] += room[r][c] / 5;
						directionCnt++;
					}

					// 확산되어 해당 칸에서 빠져 나간 미세먼지양 반영
					int minus = (room[r][c] / 5) * directionCnt;
					room[r][c] -= minus;
				}
			}
			// 확산된 공기 상황 반영
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					room[r][c] += temp[r][c];
				}
			}

			// 공기청정기 on
			purify();
		}

		// 남은 미세먼지 양 출력
		int answer = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				answer += room[r][c];
			}
		}

		// 정답 출력
		System.out.println(answer);
	}
}