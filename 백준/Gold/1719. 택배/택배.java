// 택배

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 집하장의 개수, 집하장 간 경로의 개수
	static int n, m;

	// 최단 시간
	static int[][] time;

	// 최단 시간으로 이동하기 위한 다음 경로
	static int[][] next;

	static final int IMPOSSIBLE = 987987987;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		time = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(time[i], IMPOSSIBLE);
			time[i][i] = 0;
		}
		next = new int[n+1][n+1];

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			time[l][r] = Math.min(time[l][r], cost);
			time[r][l] = Math.min(time[r][l], cost);
			next[l][r] = r;
			next[r][l] = l;
		}

	}

	private static void solution() {
		// 최단거리 찾기
		for (int mid = 1; mid <= n; mid++) {
			for (int src = 1; src <= n; src++) {
				for (int dest = 1; dest <= n; dest++) {
					int curTime = time[src][mid] + time[mid][dest];

					if (curTime < time[src][dest]) {
						time[src][dest] = curTime;
						next[src][dest] = next[src][mid];
					}
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int src = 1; src <= n; src++) {
			for (int dest = 1; dest <= n; dest++) {
				if (src == dest) {
					sb.append("-").append(" ");
				}
				else {
					sb.append(next[src][dest]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}