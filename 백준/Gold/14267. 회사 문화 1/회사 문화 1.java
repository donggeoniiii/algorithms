// 회사 문화 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 회사의 직원 수, 최초 칭찬 횟수
	static int n, m;

	// 회사 라인
	static List<Integer>[] adj;

	// 직속상사 표시
	static int[] parent;

	// 사원별 칭찬 포인트
	static int[] totalPoint;

	// 사장은 1번
	static int ceo = 1;

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
		parent = new int[n+1];
		totalPoint = new int[n+1];

		// 직원별 직속 상관 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			parent[i] = Integer.parseInt(st.nextToken());

			// 상관에게 연결로 달아놓기
			if (parent[i] == -1) {
				continue;
			}
			adj[parent[i]].add(i);
		}

		// 직속 상관에게 칭찬 받은 사람과 칭찬 수치
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int name = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());

			// 일단 직접 받은 포인트 적어놓기
			totalPoint[name] += point;
		}
	}

	private static void solution() {
		// 한 직원이 동시에 2명의 직속 상관을 받지 않으므로, root부터 타고 들어가서 칭찬 부여
		traverse(ceo);

		// 누적된 칭찬 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append(totalPoint[i]).append(" ");
		}
		System.out.println(sb);
	}

	private static void traverse(int root) {
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(root);

		// 말단 사원까지 내려가기
		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int next : adj[cur]) {
				// 칭찬 추가해놓기
				totalPoint[next] += totalPoint[parent[next]];

				queue.offer(next);
			}
		}
	}
}