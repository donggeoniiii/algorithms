// 트리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 노드의 개수
	private static int n;

	private static int root;

	// 연결 정보
	private static List<Integer>[] adj;

	// 노드별 삭제여부
	private static boolean[] isDeleted;

	// 지울 노드 번호
	private static int deleteNum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		adj = new LinkedList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new LinkedList<>();
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int parent = Integer.parseInt(st.nextToken());

			if (parent == -1) {
				root = i;
			}
			else {
				adj[parent].add(i);
			}
		}

		// 지울 노드 번호
		deleteNum = Integer.parseInt(br.readLine());

		// 밑에 이어지는 애들 다 삭제
		isDeleted = new boolean[n];
		deleteNode(deleteNum);

		// 살아있는 노드 중 리프노드 세기
		System.out.println(countLeafNode(root));
	}

	private static void deleteNode(int root) {
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(root);
		isDeleted[root] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int child : adj[cur]) {
				// 자식들 삭제처리
				isDeleted[child] = true;

				// 이어지는 자식 확인 위해 queue에 추가
				queue.offer(child);
			}
		}
	}

	private static int countLeafNode(int root) {
		// 루트 노드가 삭제됐으면 세 볼 필요 없음
		if (isDeleted[root]) {
			return 0;
		}

		int cnt = 0;

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			// 자식 확인
			boolean noChild = true;
			for (int child : adj[cur]) {
				if (isDeleted[child]) {
					continue;
				}

				noChild = false;
				queue.offer(child);
			}

			if (noChild) cnt++;
		}

		return cnt;
	}
}