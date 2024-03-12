// 트리의 부모찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 노드의 개수
	static int n;

	// 연결을 저장할 리스트
	static List<Integer>[] adj;

	// 노드별 부모 노드
	static int[] parent;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		adj = new ArrayList[n+1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		StringTokenizer st;
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			adj[node1].add(node2);
			adj[node2].add(node1);
		}

		parent = new int[n+1];
	}

	private static void solution() {
		// 노드부터 순회하면서 체크하기
		traverse(1);

		// 2번노드부터 부모 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= n; i++) {
			sb.append(parent[i]).append("\n");
		}
		System.out.println(sb);
	}

	private static void traverse(int root) {
		// 순회를 위한 queue
		Queue<Integer> queue = new LinkedList<>();

		// 시작점 입력
		queue.add(root);

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()){
			// 이번에 방문할 지점
			int cur = queue.poll();

			// 트리는 부모 아니면 자식만 연결되어 있으니까
			for (int next : adj[cur]) {
				// 부모인 경우는 방문할 필요 x
				if (next == parent[cur]) {
					continue;
				}

				// 부모가 아니면 다 자식이니까 부모체크
				parent[next] = cur;
				queue.offer(next);
			}
		}
	}
}