// 파티

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
	// 마을의 수, 도로의 수, 파티를 벌이는 마을
	private static int n, m, dest;

	// 최단 경로
	private static int[] dist;

	private static final int INF = 987987987;

	// 도로로 연결된 정보
	private static class Road {
		int idx;
		int time;

		Road (int idx, int time) {
			this.idx = idx;
			this.time = time;
		}
	}

	// 인접리스트
	private static List<Road>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			adj[l].add(new Road(r, time));
		}

		// 출발지별로 다익스트라로 찾기
		int answer = -1;
		for (int src = 1; src <= n; src++) {

			// 다익스트라 on
			answer = Math.max(answer, (src == dest) ? 0 : dijkstra(src, dest) + dijkstra(dest, src));
		}

		System.out.println(answer);
	}

	private static int dijkstra(int src, int dest) {
		// 최단경로 정보 초기화
		dist = new int[n+1];
		Arrays.fill(dist, INF);
		dist[src] = 0;

		PriorityQueue<Road> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));

		queue.offer(new Road(src, dist[src]));

		while (!queue.isEmpty()) {
			Road cur = queue.poll();

			// 이미 최단거리인 케이스보다 이동 시간이 길면 커트
			if (cur.time > dist[cur.idx]) continue;

			for (Road next : adj[cur.idx]) {
				// 최단거리 갱신 안되면 커트
				if (dist[cur.idx] + next.time >= dist[next.idx]) continue;

				dist[next.idx] = dist[cur.idx] + next.time;
				queue.offer(new Road(next.idx, dist[next.idx]));
			}
		}

		// 결과 출력
		return dist[dest];
	}
}