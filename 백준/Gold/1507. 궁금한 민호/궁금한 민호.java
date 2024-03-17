// 궁금한 민호

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 도시의 개수
	static int n;

	// 도시 사이에 이동하는데 걸리는 시간
	static int[][] time;

	// 최소값을 만드는 경로 찾기
	static boolean[][] isExist;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		time = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				time[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		isExist = new boolean[n+1][n+1];
	}

	private static void solution() {
		// 입력받은 최소 거리가 나오는 루트가 직행으로 유일해야만 최소 루트일 수 있음
		for (int src = 1; src <= n; src++) {
			for (int dest = 1; dest <= n; dest++) {
				boolean isUnique = true;
				for (int mid = 1; mid <= n; mid++) {
					if (mid == src || mid == dest) continue;

					int curTime = time[src][mid] + time[mid][dest];

					// 더 빠른 경로가 나오면 모순이므로 즉시 종료
					if (curTime < time[src][dest]) {
						System.out.println(-1);
						return;
					}

					// 경유해서 최소인 지점을 찾으면 길 수가 최소일 수 없으므로 스킵 표시하기
					if (curTime == time[src][dest]) {
						isUnique = false;
					}
				}

				// src -> dest로 경유해서 이동하는 루트가 없으면 길 표시
				if (isUnique) {
					isExist[src][dest] = true;
					isExist[dest][src] = true;
				}
 			}
		}

		// 길이 존재하는 지점마다 통과시간 합하기
		int totalTime = 0;
		for (int src = 1; src <= n; src++) {
			for (int dest = src + 1; dest <= n; dest++) {
				if (src == dest) continue;
				if (isExist[src][dest]) {
					totalTime += time[src][dest];
				}
			}
		}

		System.out.println(totalTime);
	}
}