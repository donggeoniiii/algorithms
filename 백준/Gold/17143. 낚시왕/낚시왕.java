// 낚시왕

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 격자판의 크기, 상어의 수
	private static int R, C, M;

	// 상어의 정보
	private static class Shark {
		int s; // speed
		int d; // direction
		int z; // size

		Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	private static List<Shark>[][] map;

	private static final int[] dr = {0, -1, 1, 0, 0};
	private static final int[] dc = {0, 0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new ArrayList[R+1][C+1];
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				map[r][c] = new ArrayList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c].add(new Shark(s, d, z));
		}

		// 낚시왕이 잡은 상어의 크기 합
		int totalSize = 0;

		// 낚시왕의 위치: 0번 열(1-indexed)
		int cdx = 0;
		while (cdx != C) {
			// 낚시왕 한 칸 이동
			cdx++;

			// 해당 열의 상어 중 가장 가까운 상어 잡기
			for (int r = 1; r <= R; r++) {
				if (map[r][cdx].size() == 1) {
					// 잡은 상어 무게 더하기
					Shark s = map[r][cdx].get(0);
					totalSize += s.z;

					// 잡았으니까 해당 칸 상어 정보 삭제
					map[r][cdx].clear();

					// 잡았으니까 이번 열에서 더 탐색할 필요 x
					break;
				}
			}

			// 상어 이동
			move();
		}

		System.out.println(totalSize);
	}

	private static void move() {
		List<Shark>[][] newMap = new ArrayList[R+1][C+1];
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				newMap[r][c] = new ArrayList<>();
			}
		}

		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (map[r][c].isEmpty()) continue;

				// 이동할 상어 정보
				Shark s = map[r][c].get(0);

				// 이동시키기
				int cr = r;
				int cc = c;
				int remain = s.s;
				while (remain-- > 0) {
					cr += dr[s.d];
					cc += dc[s.d];


					// 이동하다 위치가 맵을 벗어나면 방향 전환
					if (outOfIndex(cr, cc)) {
						// 일단 온 방향의 반대로 옮기고
						cr -= 2 * dr[s.d];
						cc -= 2 * dc[s.d];

						switch (s.d) {
							case 1:
								s.d = 2;
								break;
							case 2:
								s.d = 1;
								break;
							case 3:
								s.d = 4;
								break;
							case 4:
								s.d = 3;
								break;
						}
					}
				}

				// 이동한 곳에 상어 정보 저장
				newMap[cr][cc].add(new Shark(s.s, s.d, s.z));
			}
		}

		// 이동한 후 위치별로 상어가 2마리인 칸 있는지 확인
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (newMap[r][c].size() < 2) continue;

				Shark boss = newMap[r][c].get(0);

				for (Shark s : newMap[r][c]) {
					if (s.z > boss.z) {
						boss = s;
					}
				}

				// 다 죽이고 보스 상어만 남음
				newMap[r][c].clear();
				newMap[r][c].add(boss);
			}
		}

		// 상어들 이동 후로 갱신
		map = newMap;
	}

	private static boolean outOfIndex(int r, int c) {
		return r < 1 || c < 1 || r > R || c > C;
	}
}