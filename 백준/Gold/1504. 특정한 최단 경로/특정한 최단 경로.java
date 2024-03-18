// 특정한 최단 경로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 정점의 개수, 간선의 개수
	private static int n, e;

	// 인접 정보를 저장할 클래스, 인접리스트
	private static class Edge {
		int idx;
		long dist;

		Edge(int idx, long dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}
	private static List<Edge>[] adj;

	// 반드시 거쳐야 하는 두 정점
	private static int v1, v2;

	private static final int INF = 987987987;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= e; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			adj[l].add(new Edge(r, dist));
			adj[r].add(new Edge(l, dist));
		}

		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		// 꼭 v1, v2를 거쳐야 하므로 2가지 후보가 있다
		// 1. 1 -> v1 -> v2 -> n
		long answer1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, n);

		// 2. 1 -> v2 -> v1 -> n
		long answer2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, n);

		// 두 방법 중 더 최단인 경로 출력, 만약 둘다 불가능하면 -1
		long answer = Math.min(answer1, answer2);
		if (answer >= INF) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}

	private static long dijkstra(int src, int dest) {
		// 최단경로 초기화
		long[] dist = new long[n + 1];
		Arrays.fill(dist, INF);
		dist[src] = 0;

		PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o.dist));

		queue.offer(new Edge(src, dist[src]));

		while (!queue.isEmpty()) {
			Edge cur = queue.poll();

			if (cur.dist > dist[cur.idx]) continue;

			for (Edge next : adj[cur.idx]) {
				if (dist[cur.idx] + next.dist >= dist[next.idx]) continue;

				dist[next.idx] = dist[cur.idx] + next.dist;

				queue.offer(new Edge(next.idx, dist[next.idx]));
			}
		}

		return dist[dest];
	}
}