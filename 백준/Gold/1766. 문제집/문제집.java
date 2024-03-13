// 문제집

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 문제 수, 먼저 푸는 것이 좋은 걸 알려주는 정보 개수
	static int n, m;

	// 먼저 푸는 게 좋은 문제 수
	static int[] indegree;

	// 연결된 문제 리스트
	static List<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		indegree = new int[n+1];

		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());

			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());

			adj[prev].add(next);
			indegree[next]++;
		}
	}

	private static void solution() {
		// indegree가 0인 애들 중에서 쉬운 애들부터 풀어야 하니까, 우선순위 큐 사용
		PriorityQueue<Integer> queue = new PriorityQueue<>();

		// 먼저 풀 수 있는 문제들 추가
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}

		StringBuilder sb = new StringBuilder();

		// 위상탐색으로 푸는 순서 찾기
		while (!queue.isEmpty()) {
			// 이번에 풀 문제
			int cur = queue.poll();

			sb.append(cur).append(" ");

			for (int next : adj[cur]) {
				// 이어지는 문제들은 선행 문제 하나 감소
				indegree[next]--;

				// 이어지는 문제 중 더 이상 선행 문제가 없다면 다음에 풀 문제로 추가
				if (indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}

		System.out.println(sb);
	}
}