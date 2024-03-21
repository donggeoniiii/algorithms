// 확장 게임

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 맵 크기, 플레이어 수
	private static int n, m, p;

	// 플레이어별 이동 반경
	private static int[] s;

	// 플레이어별 성 위치, 이동 거리
	private static Queue<int[]>[] castle;

	// 게임판 상태
	private static int[][] map;

	// 이동을 위한 델타배열
	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};

	private static boolean outOfIndex(int r, int c) {
		return r >= n || c >= m || r < 0 || c < 0;
	}

	// 플레이어별 성 개수
	private static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());

		// 플레이어는 1번부터니까 1-indexed
		s = new int[p+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= p; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}

		// 플레이어별 성 위치
		castle = new LinkedList[p+1];
		for (int i = 1; i <= p; i++) {
			castle[i] = new LinkedList<>();
		}

		// 플레이어별 성 개수
		answer = new int[p+1];

		// 맵 상태
		map = new int[n][m];
		for (int r = 0; r < n; r++) {
			String s = br.readLine();
			for (int c = 0; c < m; c++) {
				if (s.charAt(c) == '#') {
					map[r][c] = -1;
				}
				else if (s.charAt(c) == '.') {
					map[r][c] = 0;
				}
				else {
					int player = s.charAt(c) - '0';
					map[r][c] = player;
					castle[player].offer(new int[]{r, c, 0});
					answer[player]++;
				}
			}
		}

		// 끝날 때까지 턴 진행
		while (true) {
			boolean gameOver = true;

			// 플레이어 1~p로 진행
			for (int player = 1; player <= p; player++) {

				// 다음 턴에 확인해볼 성 리스트
				Queue<int[]> newCastle = new LinkedList<>();

				while (!castle[player].isEmpty()) {
					int[] cur = castle[player].poll();
					int cr = cur[0];
					int cc = cur[1];
					int curRange = cur[2];

					// 더 갈 수는 있는데 이번 턴에 접근이 안되면 다음 턴에 확장 가능한 지 확인
					if (curRange >= s[player]) {
						newCastle.add(new int[] {cr, cc, 0});
						continue;
					}

					for (int dt = 0; dt < 4; dt++) {
						int nr = cr + dr[dt];
						int nc = cc + dc[dt];

						if (outOfIndex(nr, nc)) {
							continue;
						}

						// 다른 사람이 이미 먹었거나 벽이면 스킵
						if (map[nr][nc] != 0) {
							continue;
						}

						// 자신이 가지고 있는 성 확장하기
						map[nr][nc] = player;
						answer[player]++;

						// 이번턴에 누군가는 확장을 했으니까
						gameOver = false;

						// 해당 방향으로 한 칸 더 가보기
						castle[player].add(new int[] {nr, nc, curRange + 1});
					}
				}

				// 탐색을 마치면 성 리스트 업데이트
				castle[player] = newCastle;
			}

			// 아무도 게임 진행이 안됐으면 종료
			if (gameOver) break;
		}

		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (int player = 1; player <= p; player++) {
			sb.append(answer[player]).append(" ");
		}
		System.out.println(sb);
	}
}