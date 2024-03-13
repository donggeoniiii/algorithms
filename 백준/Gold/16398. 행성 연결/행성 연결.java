// 행성 연결

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 행성 개수
	static int n;

	// 플로우 관리 비용
	private static class Flow {
		int left;
		int right;
		int cost;

		Flow (int left, int right, int cost) {
			this.left = left;
			this.right = right;
			this.cost = cost;
		}
	}

	// 이미 선택한 행성인지 판단하기 위한 union-find
	static int[] root;

	static int find(int node) {
		if (root[node] < 0) {
			return node;
		}

		return root[node] = find(root[node]);
	}

	static boolean union(int node1, int node2) {
		node1 = find(node1);
		node2 = find(node2);

		if (node1 == node2) {
			return true;
		}
		if (root[node1] == root[node2]) {
			root[node1]--;
		}
		if (root[node1] < root[node2]) {
			root[node2] = node1;
		}
		else root[node1] = node2;
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		List<Flow> flow = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int cost = Integer.parseInt(st.nextToken());

				// 같은 점인 경우는 스킵
				if (cost == 0) continue;

				Flow f = new Flow(i, j, cost);
				flow.add(f);
			}
		}

		// 제일 싼 값부터 확인해봐야 되니까
		flow.sort(Comparator.comparingInt(o -> o.cost));

		root = new int[n+1];
		for (int i = 1; i <= n; i++) {
			root[i] = -1;
		}

		// n개의 행성을 최소한으로 연결하려면 n-1개의 플로우를 선택해야 함
		long totalCost = 0;
		int flowCnt = 0;

		for (Flow f : flow) {
			if (union(f.left, f.right)) continue;

			totalCost += f.cost;

			flowCnt++;

			if (flowCnt == n-1) {
				break;
			}
		}

		// 정답 출력
		System.out.println(totalCost);
	}
}