// 노드 사이의 거리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 노드의 개수, 간선의 개수
	static int n, m;

	// 연결리스트
	static List<int[]>[] adj;

	// 방문배열
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			// 연결노드 이름 + 거리 함께 저장
			adj[node1].add(new int[] {node2, dist});
			adj[node2].add(new int[] {node1, dist});
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			visited = new boolean[n+1];
			sb.append(getDistance(node1, node2)).append("\n");
		}

		System.out.println(sb);
	}

	private static int getDistance(int start, int dest) {
		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] {start, 0});
		visited[start] = true;

		int finalDist = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curNode = cur[0];
			int dist = cur[1];

			// 만약 원하는 지점을 찾으면 종료
			if (curNode == dest) {
				finalDist = dist;
				break;
			}

			for (int[] next : adj[curNode]) {
				if (visited[next[0]]) {
					continue;
				}
				visited[next[0]] = true;
				queue.offer(new int[] {next[0], next[1] + dist});
			}
		}

		return finalDist;
	}
}