import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 점의 개수, 차례의 개수
	private static int n, m;

	private static int[] parent;
	private static int[] rank;

	private static int find(int node) {
		if (node == parent[node]) {
			return node;
		}

		return parent[node] = find(parent[node]);
	}

	private static boolean isUnion(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y) return true;
		if (rank[x] == rank[y]){
			rank[y]--;
		}
		if (rank[x] >= rank[y]) {
			parent[y] = x;
		}
		else {
			parent[x] = y;
		}

		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		rank = new int[n];

		// 턴마다 돌아가면서 입력
		for (int i = 1; i <= m; i++) {
			// 입력할 직선
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			// 입력 후 사이클 있는지 확인, 있으면 종료
			if (isUnion(s, e)) {
				System.out.println(i);
				return;
			}
		}

		// 사이클이 검출되지 않았으면 0 출력
		System.out.println(0);
	}
}