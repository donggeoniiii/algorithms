import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 미로를 움직이는 주체, 좌표
	static class Move {
		private int r;
		private int c;
		private int name;

		public Move(int r, int c, int name) {
			this.r = r;
			this.c = c;
			this.name = name;
		}
	}

	// 미로의 행, 열
	static int R;
	static int C;

	// 미로 배열
	static char[][] maze;

	// 방문 배열
	static int[][][] visited;

	// bfs를 위한 queue - for 상근
	static Queue<Move> queueForJ;

	// bfs를 위한 queue - for 불
	static Queue<Move> queueForFire;

	// bfs를 위한 델타 탐색
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	// 불의 bfs
	static void findFastestEscapeTimeForFire() {
		// 더 탐색할 지점이 없을 때까지
		while (!queueForFire.isEmpty()) {
			// queue에서 탐색좌표 꺼내오기
			Move cur = queueForFire.poll();

			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cur.r + dr[dt];
				int nc = cur.c + dc[dt];

				// out of index
				if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
					continue;
				}

				// 벽이면 스킵
				if (maze[nr][nc] == '#')
					continue;

				// 이전에 더 빨리 도착한 경우가 있으면 스킵
				if (visited[nr][nc][1] > 0)
					continue;

				// 방문 체크
				visited[nr][nc][1] = visited[cur.r][cur.c][1] + 1;

				// 이어서 탐색
				queueForFire.offer(new Move(nr, nc, 1));
			}
		}
	}

	// 상근이의 bfs
	static int findFastestEscapeTimeForJ() {
		// 더 탐색할 지점이 없을 때까지
		while (!queueForJ.isEmpty()) {
			// queue에서 탐색좌표 꺼내오기
			Move cur = queueForJ.poll();

			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cur.r + dr[dt];
				int nc = cur.c + dc[dt];

				// out of index면 탈출한거니까
				if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
					// 시간 출력
					return visited[cur.r][cur.c][0];
				}

				// 벽이면 스킵
				if (maze[nr][nc] == '#')
					continue;

				// 이전에 방문한 지역은 스킵
				if (visited[nr][nc][0] > 0)
					continue;

				// 불이 해당 자리에 더 빨리 도착하는 경우 스킵
				if (visited[nr][nc][1] > 0 && visited[nr][nc][1] <= visited[cur.r][cur.c][0] + 1)
					continue;

				// 방문 체크
				visited[nr][nc][0] = visited[cur.r][cur.c][0] + 1;

				// 다음 탐색을 위해
				queueForJ.offer(new Move(nr, nc, 0));
			}
		}

		// 다 돌았는데 탈출을 못했으면 -1 반환
		return -1;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 개수
		int T = input.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			// 미로의 크기
			C = input.nextInt();
			R = input.nextInt();

			// 미로 배열, 방문배열 생성
			maze = new char[R][C];
			visited = new int[R][C][2];

			// bfs를 위한 queue
			queueForJ = new LinkedList<>();
			queueForFire = new LinkedList<>();

			// 배열에 정보 입력
			for (int r = 0; r < R; r++) {
				String line = input.next();
				for (int c = 0; c < C; c++) {
					maze[r][c] = line.charAt(c);

					// 만약 입력이 J면
					if (maze[r][c] == '@') {
						// queue에 지훈이 좌표 저장, 방문체크
						queueForJ.offer(new Move(r, c, 0));
						visited[r][c][0] = 1;
					}

					// 만약 입력이 F면
					if (maze[r][c] == '*') {
						// queue에 바로 불 좌표 저장, 방문체크
						queueForFire.offer(new Move(r, c, 1));
						visited[r][c][1] = 1;
					}
				}
			}

			// 탈출 가능한지 확인
			findFastestEscapeTimeForFire();
			int result = findFastestEscapeTimeForJ();

			// 결과 추가
			if (result == -1)
				sb.append("IMPOSSIBLE").append("\n");
			else
				sb.append(result).append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}

}