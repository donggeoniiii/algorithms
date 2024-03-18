// 최소 비용 구하기 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 도시의 개수
	private static int n;

	// 버스의 개수
	private static int m;

	// 버스 노선 정보를 저장할 클래스
	private static class Bus {
		int idx;
		int dist;

		Bus (int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}

	// 최소비용
	private static int[] dist;

	private static final int INF = 987987987;

	// 인접리스트
	private static List<Bus>[] adj;

	// 시작지점, 도착지점
	private static int src, dest;

	// 최소비용을 위한 경로
	private static int[] prev;

	// 출발점부터 각 지점까지의 최소비용, 경로를 구하는 다익스트라 알고리즘
	static void dijkstra(int src) {
		PriorityQueue<Bus> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));

		// 시작점 입력
		queue.offer(new Bus(src, dist[src]));

		// 이어지는 점 체크
		while (!queue.isEmpty()) {
			Bus cur = queue.poll();

			// 비용이 최소 비용과 다르면 유효하지 않은 값이므로 컷
			if (cur.dist != dist[cur.idx]) continue;

			for (Bus next : adj[cur.idx]) {
				// 해당 경로로 이동하는 경우가 최소 비용보다 크면 컷
				if (dist[cur.idx] + next.dist >= dist[next.idx]) continue;

				// 새로운 최소 비용 경로를 찾으면 반영
				dist[next.idx] = dist[cur.idx] + next.dist;

				// 최단 경로 진행 시 이전에 어디서 온 건지 기록
				prev[next.idx] = cur.idx;

				queue.offer(new Bus(next.idx, dist[next.idx]));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		dist = new int[n+1];
		Arrays.fill(dist, INF);

		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			adj[l].add(new Bus(r, dist));
		}

		st = new StringTokenizer(br.readLine());
		src = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());

		dist[src] = 0;

		prev = new int[n+1];

		// 최소비용, 이동 경로 저장
		dijkstra(src);

		StringBuilder sb = new StringBuilder();

		// 1. 목적지까지 최소비용 입력
		sb.append(dist[dest]).append("\n");

		// 2. 최소 비용 경로에 포함된 도시 개수 입력
		int cur = dest;
		List<Integer> minRoute = new ArrayList<>();
		while (cur != src) {
			minRoute.add(cur);
			cur = prev[cur];
		}
		minRoute.add(cur);
		sb.append(minRoute.size()).append("\n");

		// 3. 최소 비용 경로 입력
		Collections.reverse(minRoute);
		for (int next : minRoute) {
			sb.append(next).append(" ");
		}

		// 출력
		System.out.println(sb);
	}
}