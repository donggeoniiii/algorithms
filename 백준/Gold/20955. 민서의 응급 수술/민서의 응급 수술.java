// 민서의 응급수술

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 뉴런의 개수, 시냅스의 개수
	static int n, m;

	// 뉴런 연결리스트
	static List<Integer>[] adj;

	// 연결상태를 점검하기 위한 방문배열
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			adj[node1].add(node2);
			adj[node2].add(node1);
		}

		visited = new boolean[n+1];
	}

	private static void solution() {
		// 트리가 되기 위해서 우선 끊어진 연결요소가 몇 개인지 세기
		int connectionCnt = 0;

		for (int i = 1; i <= n; i++) {
			if (visited[i]) {
				continue;
			}

			// 처음 방문하는 노드면 방문체크하고 이어진 점 탐색
			visited[i] = true;
			traverse(i);

			// 새로운 연결요소를 찾았으니 카운트 증가
			connectionCnt++;
		}

		/*
		우선 끊어진 connectionCnt 개의 연결요소를 서로 이어야 하므로
		connectionCnt-1의 선이 더 필요함
		*/
		int plusCnt = connectionCnt - 1;

		/*
		이렇게 전체 선의 개수는 (m + connectionCnt - 1) 개가 된다
		거기서 트리를 위한 n-1개의 간선은 있어야 하므로,
		지워야 하는 간선의 개수는
		(m + connectionCnt - 1) - (n - 1)개가 된다.
		*/
		int minusCnt = (m + connectionCnt - 1) - (n - 1);

		// 정답 출력
		System.out.println(plusCnt + minusCnt);
	}

	private static void traverse(int src) {
		Queue<Integer> queue = new LinkedList<>();

		queue.add(src);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int next : adj[cur]) {
				if (visited[next]) {
					continue;
				}

				visited[next] = true;
				queue.offer(next);
			}
		}
	}
}