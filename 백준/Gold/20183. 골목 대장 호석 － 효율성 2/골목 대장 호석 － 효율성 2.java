// 골목 대장 호석 - 효율성 2

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
	// 교차로의 개수, 골목 개수, 시작 교차로 번호, 도착 교차로 번호
	private static int n, m, src, dest;

	// 가진 돈
	private static long c;

	private static final long INF = Long.MAX_VALUE;

	// 인접 리스트
	private static class Edge {
		int idx;
		long cost;

		Edge (int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
	private static List<Edge>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		src = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
		c = Long.parseLong(st.nextToken());

		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		// 돈을 최대로 내야 하는 금액의 상한 정하기
		long left = 1;
		long right = -1;

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());

			adj[l].add(new Edge(r, cost));
			adj[r].add(new Edge(l, cost));

			right = Math.max(right, cost);
		}

		while (left < right) {
			long mid = (left + right) / 2;

			// 만약 이 금액에서 통과할 수 있으면 더 적게 내도 통과 가능한 지 테스트
			if (dijkstra(mid)) right = mid;

			// 안되면 좀 더 내는 범위에서 되는 경우 찾기
			else left = mid + 1;
		}

		// 최종적으로 찾은 값 출력
		if (dijkstra(left)) {
			System.out.println(left);
		}
		else {
			System.out.println(-1);
		}
	}

	private static boolean dijkstra(long limit) {
		// 최소비용 배열 초기화
		long[] dist = new long[n + 1];
		Arrays.fill(dist, INF);
		dist[src] = 0;

		PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingLong(o -> o.cost));
		queue.offer(new Edge(src, dist[src]));

		while (!queue.isEmpty()) {
			Edge cur = queue.poll();
			int cdx = cur.idx;

			// 이미 최소비용보다 더 썼으면 스킵
			if (cur.cost > dist[cdx]) continue;

			for (Edge next : adj[cdx]) {
				// 만약 간선 중 이번 탐색에서 허용된 금액보다 더 내야 되면 스킵
				if (next.cost > limit) continue;

				int ndx = next.idx;
				long newCost = dist[cdx] + next.cost;

				// 최소비용이 갱신되지 않으면 스킵
				if (newCost >= dist[ndx]) continue;

				dist[ndx] = newCost;
				queue.offer(new Edge(ndx, dist[ndx]));
			}
		}

		// 만약 최소로 도착해도 c원 이상 썼다면 false 반환
		return dist[dest] <= c;
	}
}