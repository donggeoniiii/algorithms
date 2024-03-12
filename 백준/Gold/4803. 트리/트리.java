import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	// 정점의 개수, 간선의 개수
	static int n, m;

	// 연결 리스트
	static List<Integer>[] adj;

	// 트리 여부를 판단하기 위한 방문배열
	static boolean[] visited;

	// 트리 여부를 전달할 변수
	static boolean isTree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 0, 0이 입력될 때까지 진행
		int tc = 1;
		while (true) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			if (n == 0 && m == 0) {
				break;
			}

			// 연결리스트 생성
			adj = new ArrayList[n+1];
			for (int i = 1; i <= n; i++) {
				adj[i] = new ArrayList<>();
			}

			// 인접정보 저장
			for (int i = 1; i <= m; i++) {
				st = new StringTokenizer(br.readLine());

				int node1 = Integer.parseInt(st.nextToken());
				int node2 = Integer.parseInt(st.nextToken());

				adj[node1].add(node2);
				adj[node2].add(node1);
			}

			// 방문배열 초기화
			visited = new boolean[n+1];

			// 이번 테스트 케이스의 트리 개수
			int treeCnt = 0;

			for (int i = 1; i <= n; i++) {
				if (visited[i]){
					continue;
				}

				// 아직 방문 안한 지점을 찾으면 일단 방문체크
				visited[i] = true;

				// 일단 하나의 트리로 생각하고 이어지는 애들 전부 방문
				isTree = true;
				traverse(i, -1);

				// 순회 결과 이번에 방문한 연결요소가 트리면 +1
				treeCnt += (isTree) ? 1 : 0;
			}

			// 트리 개수에 따라 결과 입력
			sb.append("Case ").append(tc++).append(": ");
			if (treeCnt == 0) {
				sb.append("No trees.");
			}
			else if (treeCnt == 1) {
				sb.append("There is one tree.");
			}
			else {
				sb.append("A forest of ").append(treeCnt).append(" trees.");
			}
			sb.append("\n");
		}

		// 출력
		System.out.println(sb);
	}

	private static void traverse(int cur, int parent) {
		for (int next : adj[cur]) {
			// 만약 다음 지점이 부모이면 생략
			if (next == parent) {
				continue;
			}

			// 만약 부모가 아닌데 이미 방문한 지점이 있으면 트리가 아님
			if (visited[next]) {
				isTree = false;
				continue;
			}

			// 방문체크 및 다음으로 이동
			visited[next] = true;
			traverse(next, cur);
		}
	}
}