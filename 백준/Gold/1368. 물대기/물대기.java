// 물대기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 논의 수
	static int n;

	private static class Edge {
		int left;
		int right;
		int weight;

		public Edge (int left, int right, int weight) {
			this.left = left;
			this.right = right;
			this.weight = weight;
		}
	}

	// union-find 알고리즘
	static int[] root;

	static int find(int node) {
		if (root[node] == node) {
			return node;
		}
		return find(root[node]);
	}

	static boolean union(int node1, int node2) {
		node1 = find(node1);
		node2 = find(node2);

		if (node1 == node2) {
			return true;
		}

		root[node2] = node1;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		// 우물을 따로 파는데 드는 비용을 가상의 논에 대는 비용이라고 판단
		n++;

		// 두 논을 연결하는데 드는 비용을 저장, 비용이 적은 순으로 저장할 우선순위 큐
		PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
		for (int i = 1; i < n; i++) {
			int curPrice = Integer.parseInt(br.readLine());

			Edge newEdge = new Edge(i, n, curPrice);

			queue.offer(newEdge);
		}

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < n; j++) {

				int weight = Integer.parseInt(st.nextToken());
				if (weight == 0) continue;

				Edge newEdge = new Edge(i, j, weight);

				queue.offer(newEdge);
			}
		}

		root = new int[n+1];
		for (int i = 1; i <= n; i++) {
			root[i] = i;
		}

		int totalPrice = 0;
		int edgeCnt = 0;
		// 선택한 경로가 n-1개가 될 때까지
		while (edgeCnt < n-1) {
			Edge cur = queue.poll();

			// 이미 선택한 경로면 패스
			if (union(cur.left, cur.right)) continue;

			// 이번에 선택했으니 가격 추가
			totalPrice += cur.weight;

			// 경로 선택했으니 카운트 증가
			edgeCnt++;
		}

		System.out.println(totalPrice);
	}
}