// 트리와 쿼리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 전체 트리 정점의 수, 루트로 할 노드 번호, 쿼리의 수
	static int n, r, q;

	// 연결리스트
	static List<Integer>[] adj;

	// 해당
	static int[] subTreeSize;

	// 방문정보 저장
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		// 연결정보 저장
		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n-1; i++) {
			st = new StringTokenizer(br.readLine());

			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());

			adj[node1].add(node2);
			adj[node2].add(node1);
		}

		// 루트에서부터 하나씩 방문하면서 하위 트리의 크기 구하기
		visited = new boolean[n+1];
		subTreeSize = new int[n+1];
		countNode(r);

		// 정보를 바탕으로 쿼리별 정점의 수 세기
		for (int i = 0; i < q; i++) {
			int root = Integer.parseInt(br.readLine());
			sb.append(subTreeSize[root]).append("\n");
		}

		// 출력하기
		System.out.println(sb);
	}

	private static int countNode(int cur) {
		// 본인 노드 크기 더하고
		subTreeSize[cur]++;

		// 여기는 이미 구했으니까 다음에 볼 필요 없음
		visited[cur] = true;

		for (int next : adj[cur]) {
			if (visited[next]) {
				continue;
			}

			// 자녀들 밑에 있는 노드 수만큼 밑에 있는 트리 사이즈 구하기
			subTreeSize[cur] += countNode(next);
		}

		// 정답 출력
		return subTreeSize[cur];
	}
}