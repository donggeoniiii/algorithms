// 전기가 부족해

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 도시의 개수, 케이블의 개수, 발전소의 개수
	static int n, m, k;

	// 발전소가 설치된 도시로부터 이을 수 있는 모든 경우의 수
	static List<int[]> adj;

	// 선택된 경우의 수 세기 위한 union-find
	static int[] p;
	static int find(int node) {
		if (p[node] < 0) {
			return node;
		}

		return p[node] = find(p[node]);
	}

	static boolean isUnion (int n1, int n2) {
		n1 = find(n1);
		n2 = find(n2);

		if (n1 == n2) {
			return true;
		}

		p[n2] = n1;
		return false;
	}

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		p = new int[n+1];
		for (int i = 0; i <= n; i++) {
			p[i] = -1;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int plant = Integer.parseInt(st.nextToken());

			// 발전소가 있는 도시는 0번째 도시로부터 공급받는다고 간주
			isUnion(0, plant);
		}

		// 연결된 케이블
		adj = new ArrayList<>();
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj.add(new int[] {l, r, cost});
		}
	}

	private static void solution() {
		// 가장 비용이 덜 드는 곳부터 탐색
		adj.sort(Comparator.comparingInt(o -> o[2]));

		long totalCost = 0;
		int powerCnt = k; // 이미 k개의 발전소 도시는 연결된거나 마찬가지
		for (int[] cur : adj) {
			int l = cur[0];
			int r = cur[1];
			int cost = cur[2];

			// 이미 연결된 도시면 패스
			if (isUnion(l, r)) {
				continue;
			}

			// 연결하고 비용 추가
			totalCost += cost;
			powerCnt++;

			// 만약 n개의 케이블이 연결되면 종료
			if (powerCnt == n) {
				break;
			}
		}

		System.out.println(totalCost);
	}
}