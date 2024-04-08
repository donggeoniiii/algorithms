// 마법사 상어와 토네이도

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 격자의 크기
	private static int n;

	// 각 칸에 있는 모래
	private static int[][] sand;

	private static final int[] dr = {0, 1, 0, -1};
	private static final int[] dc = {-1, 0, 1, 0};
	private static final int[] range = {1, 1, 2, 2};

	private static final int[][] sdr = {{-1, 1, -2, 2, 0, -1, 1, -1, 1}, {-1, -1, 0, 0, 2, 0, 0, 1, 1},
										{1, -1, 2, -2, 0, 1, -1, 1, -1}, {1, 1, 0, 0, -2, 0, 0, -1, -1}};
	private static final int[][] sdc = {{1, 1, 0, 0, -2, 0, 0, -1, -1}, {-1, 1, -2, 2, 0, -1, 1, -1, 1},
										{-1, -1, 0, 0, 2, 0, 0, 1, 1}, {1, -1, 2, -2, 0, 1, -1, 1, -1}};
	private static final int[] per = {1, 1, 2, 2, 5, 7, 7, 10, 10};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		sand = new int[n][n];
		for (int r = 0; r < n; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				sand[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(tornado());
	}

	private static int tornado() {
		int cr = n / 2;
		int cc = n / 2;
		int sandCnt = 0; // 격자 밖으로 날아간 모래량

		while (true) {
			for (int dt = 0; dt < 4; dt++) {
				for (int r = 1; r <= range[dt]; r++) {
					// 다음에 이동할 토네이도 위치
					int nr = cr + dr[dt];
					int nc = cc + dc[dt];

					// 마지막 이동시 index를 벗어나며 종료됨
					if (outOfIndex(nr, nc)) {
						// 그동안 밖으로 나가진 모래량 반환
						return sandCnt;
					}

					int curSand = sand[nr][nc];

					// 주변으로 날아간 모래 양
					int blowTotal = 0;

					// 모래 날리기
					for (int sdt = 0; sdt < 9; sdt++) {
						int snr = nr + sdr[dt][sdt];
						int snc = nc + sdc[dt][sdt];
						int sp = (curSand * per[sdt]) / 100;

						// 격자 밖으로 나가면 나간 모래량 카운트
						if (outOfIndex(snr, snc)) {
							sandCnt += sp;
						}
						else {
							// 해당 위치에 모래량 더하기
							sand[snr][snc] += sp;
						}

						// 지금까지 날린 모래량
						blowTotal += sp;
					}

					// 알파 방향으로 모래 날리기
					int ar = nr + dr[dt];
					int ac = nc + dc[dt];
					int ap = curSand - blowTotal;

					// 격자 밖으로 나가면 나간 모래량 카운트
					if (outOfIndex(ar, ac)) {
						sandCnt += ap;
					}
					else {
						// 아니면 해당 위치에 모래량 더하기
						sand[ar][ac] += ap;
					}

					// 이전 위치에 있던 모래는 완전히 날아갔으므로
					sand[nr][nc] = 0;

					// 좌표 변경
					cr = nr;
					cc = nc;
				}
			}

			// 한 바퀴 순회 후 다음 이동을 위한 이동반경 수정
			for (int dir = 0; dir < 4; dir++) {
				range[dir] += 2;
			}
		}
	}

	private static boolean outOfIndex(int r, int c) {
		return r < 0 || c < 0 || r >= n || c >= n;
	}
}