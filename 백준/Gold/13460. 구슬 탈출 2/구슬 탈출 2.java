// 구슬탈출2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 구슬의 위치와 움직인 횟수를 저장하는 클래스
	static class Marble {
		int rr;
		int rc;
		int br;
		int bc;
		int move;

		Marble(int rr, int rc, int br, int bc, int move) {
			this.rr = rr;
			this.rc = rc;
			this.br = br;
			this.bc = bc;
			this.move = move;
		}
	}

	// 배열의 행, 열 길이
	static int n, m;

	// 구슬판 배열
	static char[][] map;

	// bfs를 위한 델타배열
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// bfs 알고리즘
	static int findFastestEta(int rsr, int rsc, int bsr, int bsc) {
		// bfs를 위한 queue
		Queue<Marble> queue = new LinkedList<>();

		// 방문배열, 시작지점 방문체크
		boolean[][][][] visited = new boolean[n][m][n][m];
		visited[rsr][rsc][bsr][bsc] = true;

		// queue에 시작점 넣고
		queue.offer(new Marble(rsr, rsc, bsr, bsc, 0));

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			// queue에서 좌표 빼기
			Marble cur = queue.poll();
			int redcr = cur.rr;
			int redcc = cur.rc;
			int bluecr = cur.br;
			int bluecc = cur.bc;

			// 델타 탐색
			for (int dt = 0; dt < 4; dt++) {
				int rednr = redcr;
				int rednc = redcc;
				int bluenr = bluecr;
				int bluenc = bluecc;

				// 벽을 만나기 전까지 각각 움직여본다
				// 그동안에 구멍을 만날 수도 있으니까 체크하는 변수 있어야 함
				boolean redDown = false;
				boolean blueDown = false;

				// 빨간 색 이동
				while (map[rednr + dr[dt]][rednc + dc[dt]] != '#') {
					// 한 칸 이동
					rednr += dr[dt];
					rednc += dc[dt];

					// 빠지면 종료
					if (map[rednr][rednc] == 'O') {
						redDown = true;
						break;
					}
				}

				while (map[bluenr + dr[dt]][bluenc + dc[dt]] != '#') {
					// 한 칸 더 이동
					bluenr += dr[dt];
					bluenc += dc[dt];

					// 빠지면 종료
					if (map[bluenr][bluenc] == 'O') {
						blueDown = true;
						break;
					}
				}

				// 파란 구슬이 빠지는 경우는 필요 없다
				if (blueDown)
					continue;

				// 빨간 구슬만 떨어졌으면 종료
				if (redDown)
					return cur.move + 1;

				// 이동 이후 두 구슬의 위치가 같으면, 시작지점 기준으로 조정
				if (rednr == bluenr && rednc == bluenc) {
					// 구슬 별 이동거리
					int redDist = Math.abs(redcr - rednr + redcc - rednc);
					int blueDist = Math.abs(bluecr - bluenr + bluecc - bluenc);
					// 이동거리가 더 긴 구슬이 한칸 뒤로 물러나기
					if (redDist > blueDist) {
						rednr -= dr[dt];
						rednc -= dc[dt];
					} else {
						bluenr -= dr[dt];
						bluenc -= dc[dt];
					}
				}

				// 벽을 만나서 종료되었으면 방문체크
				if (!visited[rednr][rednc][bluenr][bluenc]) {
					visited[rednr][rednc][bluenr][bluenc] = true;
					queue.offer(new Marble(rednr, rednc, bluenr, bluenc, cur.move + 1));
				}
			}
		}

		// 탈출 지점을 결국 못 찾고 탐색이 끝났으면 fail
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		// 배열의 행, 열 길이
		StringTokenizer st = new StringTokenizer(input.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 배열 초기화
		map = new char[n][m];

		// 배열 정보 입력
		int rsr = 0, rsc = 0, bsr = 0, bsc = 0;
		for (int r = 0; r < n; r++) {
			String line = input.readLine();
			for (int c = 0; c < m; c++) {
				map[r][c] = line.charAt(c);

				// 시작점 체크
				if (map[r][c] == 'R') {
					rsr = r;
					rsc = c;
				}

				if (map[r][c] == 'B') {
					bsr = r;
					bsc = c;
				}
			}
		}

		// 최단시간 찾기
		int answer = findFastestEta(rsr, rsc, bsr, bsc);

		// 정답 출력
		if (answer == -1 || answer > 10)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
}