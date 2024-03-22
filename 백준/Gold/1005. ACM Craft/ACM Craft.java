// ACM Craft

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 건물 개수, 건설 순서 규칙 수
	private static int n, k;

	// 사전에 지어야 하는 건물 수
	private static int[] indegree;

	// 건물이 지어지는데 걸리는 시간
	private static int[] buildTime;

	// 연결리스트
	private static List<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트 케이스 개수
		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			indegree = new int[n+1];
			buildTime = new int[n+1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}

			adj = new ArrayList[n+1];
			for (int i = 1; i <= n; i++) {
				adj[i] = new ArrayList<>();
			}

			for (int rule = 1; rule <= k; rule++) {
				st = new StringTokenizer(br.readLine());
				int prev = Integer.parseInt(st.nextToken());
				int next = Integer.parseInt(st.nextToken());

				adj[prev].add(next);
				indegree[next]++;
			}

			// 짓는데 얼마나 걸리는지 궁금한 건물
			int w = Integer.parseInt(br.readLine());

			sb.append(solve(w)).append("\n");
		}
		System.out.println(sb);
	}

	private static int solve(int dest) {
		// 건물을 짓는 데까지 걸리는 준비시간: 이전 건물 총 시간
		int[] totalPrepareTime = new int[n+1];

		// 바로 지을 수 있는 건물부터 입력
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int next : adj[cur]) {
				totalPrepareTime[next] = Math.max(totalPrepareTime[next], totalPrepareTime[cur] + buildTime[cur]);
				indegree[next]--;

				// 짓기 가능한 건물부터 건설
				if (indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}

		return totalPrepareTime[dest] + buildTime[dest];
	}
}