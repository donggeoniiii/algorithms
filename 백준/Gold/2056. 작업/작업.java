// 작업

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 작업 개수
	private static int n;

	// 작업별 후속작업 리스트
	private static List<Integer>[] adj;

	// 선행작업 개수
	private static int[] indegree;

	// 작업시간
	private static int[] time;

	// 사전 준비 시간
	private static int[] preTime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		time = new int[n+1];
		indegree = new int[n+1];

		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();

			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());

			int prevCnt = Integer.parseInt(st.nextToken());

			for (int j = 1; j <= prevCnt; j++) {
				int prev = Integer.parseInt(st.nextToken());
				adj[prev].add(i);
			}
			indegree[i] = prevCnt;
		}

		System.out.println(solve());
	}

	private static int solve() {
		preTime = new int[n+1];
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int next : adj[cur]) {
				preTime[next] = Math.max(preTime[next], preTime[cur] + time[cur]);
				indegree[next]--;

				if (indegree[next] == 0){
					queue.offer(next);
				}
			}
		}

		int maxTime = 0;
		for (int i = 1; i <= n; i++) {
			maxTime = Math.max(maxTime, preTime[i] + time[i]);
		}
		return maxTime;
	}
}