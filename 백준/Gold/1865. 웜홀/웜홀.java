// 웜홀

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 지점의 수, 도로의 개수, 웜홀의 개수
	private static int n, m, w;

	// 연결정보 저장할 리스트: {다음 지점, 걸리는 시간}
	private static List<int[]> adj;

	private static final int INF = 25000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트케이스 개수
		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= t; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			adj = new ArrayList<>();

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				adj.add(new int[] {left, right, time});
				adj.add(new int[] {right, left, time});
			}

			for (int i = 0; i < w; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				// 웜홀은 단방향, 시간 줄어든다고 표시
				adj.add(new int[] {start, end, -time});
			}

			// 음수 시간이 가능하면 yes, 아니면 no
			if (solve()) sb.append("YES");
			else sb.append("NO");
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static boolean solve() {
		// 최단시간 배열 초기화
		int[] dist = new int[n+1];
		Arrays.fill(dist, INF);

		// 벨만-포드 알고리즘으로 최단거리 바꾸기
		for (int i = 1; i <= n; i++) {
			for (int[] edge : adj) {
				int s = edge[0];
				int e = edge[1];
				int cost = edge[2];

				// 최단시간 갱신
				if (dist[e] > dist[s] + cost) {
					dist[e] = dist[s] + cost;

					// 갱신이 추가 방문 시점에도 발생하면 음수 사이클이 있으므로 과거로 갈 수 있음
					if (i == n) return true;
				}
			}
		}

		return false;
	}
}