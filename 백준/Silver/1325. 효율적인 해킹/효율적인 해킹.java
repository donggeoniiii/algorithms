// 효율적인 해킹

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<Integer>[] adj = new ArrayList[n+1];
		for (int i = 1; i < n+1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// a가 b를 신뢰한다: a와 연결된 b가 a를 해킹할 수 있다
			adj[a].add(b);
		}

		// 해킹 가능한 컴퓨터 수
		int[] cpuCount = new int[n+1];

		for (int src = 1; src < n+1; src++) {
			Queue<Integer> queue = new LinkedList<>();
			boolean[] visited = new boolean[n+1];

			queue.add(src);
			visited[src] = true;

			while (!queue.isEmpty()) {
				int cur = queue.poll();

				for (int next : adj[cur]) {
					if (visited[next]) continue;

					queue.offer(next);
					visited[next] = true;
					
					// 연결되어 있는 컴퓨터는 기준이 되는 컴퓨터를 해킹 가능하므로 카운트 증가
					cpuCount[next]++;
				}
			}
		}

		// 최댓값 찾기
		int maxCount = -1;
		for (int i = 0; i < n+1; i++) {
			maxCount = Math.max(maxCount, cpuCount[i]);
		}

		List<Integer> answer = new ArrayList<>();
		for (int i = 0; i < n+1; i++) {
			if (cpuCount[i] == maxCount) answer.add(i);
		}

		answer.sort(null);
		StringBuilder sb = new StringBuilder();
		for (int ans : answer) {
			sb.append(ans).append(" ");
		}

		System.out.println(sb);
	}
}