// 최소 스패닝 트리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 정점의 개수, 간선의 개수
	static int v, e;

	// 간선의 정보를 저장할 클래스
	static class Edge {
		int left;
		int right;
		int weight;

		public Edge (int left, int right, int weight) {
			this.left = left;
			this.right = right;
			this.weight = weight;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[").append(left).append(", ").append(right).append(", ").append(weight).append("]");
			return sb.toString();
		}
	}

	// 간선을 가중치 기준으로 정렬할 우선순위 큐
	static PriorityQueue<Edge> queue;

	// union-find를 위한 집합의 대표값 표시
	static int[] root;

	// 이미 선택한 노드 표시할 union-find 알고리즘
	static int find (int node) {
		// 본인 자체가 대표 노드 == 아직 선택되지 않았음
		if (root[node] == node) {
			return node;
		}

		// 아니면 이미 선택되어 있으므로 묶여있는 집합의 대표 노드 찾으러 가기
		return find(root[node]);
	}

	static boolean union(int node1, int node2) {
		// 각 원소가 속한 트리의 루트 노드 찾기
		node1 = find(node1);
		node2 = find(node2);

		// 두 원소의 루트 노드가 같다면 같은 집합이므로 true
		if (node1 == node2) {
			return true;
		}
		// 루트 노드가 다르다면 이번에 선택은 할 거니까 같은 집합으로 합치고
		root[node2] = node1;

		// 확인하는 시점에서는 달랐으니까 false 반환
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		// 간선의 가중치 기준으로 정렬하는 우선순위 큐
		queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

		// 간선을 가중치 기준으로 저장
		for (int i = 1; i <= e; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			Edge newEdge = new Edge(left, right, weight);

			queue.offer(newEdge);
		}

		// union-find를 통해 시작 정점과 같은 집합으로 묶기
		root = new int[v+1];
		for (int i = 1; i <= v; i++) {
			root[i] = i;
		}

		// 시작 정점부터 탐색
		int totalWeight = 0;
		int nodeCnt = 0;
		while (!queue.isEmpty()) {
			Edge curEdge = queue.poll();

			// 이미 선택한 간선이면 pass
			if (union(curEdge.left, curEdge.right)) {
				continue;
			}

			// 새로운 간선을 찾았으니 가중치 합산
			totalWeight += curEdge.weight;

			// 간선 카운트 증가
			nodeCnt++;

			// 만약 선택한 간선 개수가 v-1개면 신장 트리 완성
			if (nodeCnt == v-1) {
				break;
			}
		}

		// 정답 출력
		System.out.println(totalWeight);
	}
}