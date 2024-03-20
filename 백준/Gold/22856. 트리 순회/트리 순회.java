// 트리 순회

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 노드의 개수
	private static int n;

	private static int[] parent;
	private static int[] left;
	private static int[] right;

	private static int moveCnt = 0;

	private static boolean[] visited;

	private static final int ROOT = 1;

	private static int dest;

	private static void customInorder(int cur) {
		// 방문표시, 순회카운트 증가
		visited[cur] = true;

		// 1. 방문하지 않은 왼쪽 자식 노드로 이동
		if (left[cur] != -1 && !visited[left[cur]]) {
			customInorder(left[cur]);
			moveCnt++;
		}
		// 2. 없다면 방문하지 않은 오른쪽 자식 노드로 이동
		else if (right[cur] != -1 && !visited[right[cur]]) {
			customInorder(right[cur]);
			moveCnt++;
		}
		else {
			// 3. 순회의 끝이면 종료
			if (cur == dest) {
				return;
			}
			// 4. 아니면 부모 노드로 이동
			customInorder(parent[cur]);
			moveCnt++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		parent = new int[n+1];
		left = new int[n+1];
		right = new int[n+1];

		// 입력하기
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			left[p] = l;
			right[p] = r;
			if (l != -1)
				parent[l] = p;
			if (r != -1)
				parent[r] = p;
		}

		// 중위순회의 마지막 지점은 가장 오른쪽 노드
		int cur = ROOT;
		while (right[cur] != -1) {
			cur = right[cur];
		}
		dest = cur;

		visited = new boolean[n+1];

		customInorder(ROOT);

		System.out.println(moveCnt);
	}
}