// 면접 보는 승범이네

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
	// 도시의 수, 도로의 수, 면접장의 수
	private static int n, m, k;

	private static final long INF = Long.MAX_VALUE;

	// 인접리스트
	private static class Edge {
		int idx;
		long cost;

		Edge (int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	private static List<Edge>[] adj;

	// 면접장 리스트
	private static List<Integer> dest;

	// 면접장들에서 각 도시에 도착하는 최소 시간
	private static long[] dist;

	private static PriorityQueue<Edge> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());

			// 면접장에서 도시로 향하는 길을 체크할 것이므로 인접 정보를 반대로 입력
			adj[r].add(new Edge(l, len));
		}

		dest = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= k; i++) {
			int cur = Integer.parseInt(st.nextToken());
			dest.add(cur);
		}

		// 최단거리 배열 초기화
		dist = new long[n + 1];
		Arrays.fill(dist, INF);

		queue = new PriorityQueue<>(Comparator.comparingLong(o->o.cost));

		for (int city : dest) {
			dist[city] = 0;
			queue.offer(new Edge(city, dist[city]));
		}

		// 면접장들에서 각 도시까지 거리 재기
		dijkstra();

		// 면접장들에서 출발해 가장 멀리 있는 도시 찾기
		int mdx = -1;
		long curMaxDist = -1;
		for (int city = 1; city <= n; city++) {
			if (dist[city] > curMaxDist) {
				curMaxDist = dist[city];
				mdx = city;
			}
		}

		// 정답 출력
		System.out.println(mdx + "\n" + curMaxDist);
	}

	private static void dijkstra() {
		while (!queue.isEmpty()) {
			Edge cur = queue.poll();
			int cdx = cur.idx;
			long curCost = cur.cost;

			if (curCost > dist[cdx]) continue;

			for (Edge next : adj[cdx]) {
				int ndx = next.idx;
				long nextCost = dist[cdx] + next.cost;

				if (nextCost >= dist[ndx]) continue;

				dist[ndx] = nextCost;
				queue.offer(new Edge(ndx, dist[ndx]));
			}
		}
	}
}