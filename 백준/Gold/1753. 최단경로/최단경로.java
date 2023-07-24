// 최단경로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;

	// 정점의 개수
	static int V;

	// 간선의 개수
	static int E;

	// 간선의 연결정보를 저장할 인접 리스트
	static LinkedList<Edge>[] adj;

	// 최소 거리를 저장할 배열
	static int[] minDist;

	// 간선 정보를 담는 edge 객체 선언
	static class Edge implements Comparable<Edge> {
		private int next;
		private int weight;

		public Edge(int next, int weight) {
			this.next = next;
			this.weight = weight;
		}

		// 비교는 weight 순으로
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 정점의 개수, 간선의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 시작하는 정점 번호
		int src = Integer.parseInt(br.readLine());

		// 정점 정보 저장할 인접리스트 저장
		adj = new LinkedList[V + 1];
		for (int i = 1; i <= V; i++) {
			adj[i] = new LinkedList<>();
		}

		// 정보 저장
		for (int i = 0; i < E; i++) {
			// 시작점, 도착점, 가중치
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			// 인접리스트에 저장
			adj[prev].add(new Edge(next, weight));
		}

		// 최단거리 저장할 배열
		minDist = new int[V + 1];

		// 거리는 시작점 빼고 무수히 큰 값으로 초기화
		Arrays.fill(minDist, INF);
		minDist[src] = 0;

		// 다익스트라를 위한 우선순위 큐 초기화, 정렬 기준은 가중치
		PriorityQueue<Edge> queue = new PriorityQueue<>();

		// 시작점 추가
		queue.offer(new Edge(src, 0));

		// 더 탐색할 지점이 없을 때 까지
		while (!queue.isEmpty()) {
			// 탐색지점 가져오기
			Edge curEdge = queue.poll();

			// 만약 현재 지점까지 가중치보다 최단거리가 더 짧은 경우면 스킵
			if (curEdge.weight > minDist[curEdge.next])
				continue;

			// 이어지는 애들 가져오기
			for (Edge nextEdge : adj[curEdge.next]) {

				// 최단거리가 갱신되면
				if (minDist[nextEdge.next] > curEdge.weight + nextEdge.weight) {
					// 거리 저장
					minDist[nextEdge.next] = curEdge.weight + nextEdge.weight;

					// 다음 탐색을 위해 추가
					queue.offer(new Edge(nextEdge.next, minDist[nextEdge.next]));
				}
			}
		}

		// 출력
		for (int i = 1; i <= V; i++) {
			sb.append(minDist[i] == INF ? "INF" : minDist[i]).append("\n");
		}
		System.out.println(sb);
	}
}