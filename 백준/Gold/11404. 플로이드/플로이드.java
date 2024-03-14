// 플로이드

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 도시의 개수
	static int n;

	// 버스의 개수
	static int m;

	// 최단거리 저장할 배열
	static long[][] minDist;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		m = Integer.parseInt(br.readLine());

		minDist = new long[n+1][n+1];
		// 초기엔 불가능한 값으로 설정, 자기 자신과의 거리는 0
		for (int i = 1; i <= n; i++) {
			Arrays.fill(minDist[i], Integer.MAX_VALUE);
			minDist[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			// 버스 노선 중 가장 빠른 노선으로 저장
			minDist[l][r] = Math.min(minDist[l][r], cost);
		}
	}

	private static void solution() {
		// 경유지를 거쳐서 더 가까운 방식이 있다면 반영
		for (int mid = 1; mid <= n; mid++) {
			for (int src = 1; src <= n; src++) {
				for (int dest = 1; dest <= n; dest++) {
					if (mid == src || mid == dest) {
						continue;
					}
					long newDist = minDist[src][mid] + minDist[mid][dest];
					minDist[src][dest] = Math.min(minDist[src][dest], newDist);
				}
			}
		}

		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				if (minDist[r][c] == Integer.MAX_VALUE) {
					sb.append(0).append(" ");
				}
				else {
					sb.append(minDist[r][c]).append(" ");
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}