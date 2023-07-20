// 음악 프로그램

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();

	// 가수 수
	static int n;

	// pd 수
	static int m;

	// 자기 앞에 쌓인 순서
	static int[] order;

	// pd별로 정한 순서를 따르는 가수별 순서 리스트
	static List<Integer>[] adj;

	// bfs를 위한 priority queue
	static PriorityQueue<Integer> queue;

	// bfs가 잘 이루어졌는지 확인하기 위한 카운트
	static int resultCnt;

	// 위상정렬을 위한 bfs
	static void findStageOrder() {
		// 더 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			// 다음 탐색 지점 가져오기
			int cur = queue.poll();

			// 출력형태 저장
			resultCnt++;
			sb.append(cur).append("\n");

			// 인접리스트로 다음에 이어지는 애들 확인
			for (int next : adj[cur]) {
				// 진입차수 감소
				order[next]--;

				// 만약 0이면 queue에 추가하고
				if (order[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 가수 수, pd 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 자기보다 먼저 가야되는 사람 수를 저장하는 배열
		order = new int[n + 1];

		// pd들이 짠 리스트를 바탕으로 한 가수별 리스트
		adj = new LinkedList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new LinkedList<>();
		}

		// 값 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			// 첫 입력은 입력받은 숫자 개수
			st.nextToken();
			int prev = -1;
			while (st.hasMoreTokens()) {
				// 순서 입력
				int next = Integer.parseInt(st.nextToken());
				if (prev == -1) {
					prev = next;
				} else {
					adj[prev].add(next);
					order[next]++;
					prev = next;
				}
			}
		}

		// bfs를 위한 queue 초기화
		queue = new PriorityQueue<>();
		resultCnt = 0;

		// 이제 배열을 돌면서 0인 애들 queue에 넣고
		for (int i = 1; i <= n; i++) {
			if (order[i] == 0)
				queue.offer(i);
		}

		// bfs해서 잘 되는지 확인
		findStageOrder();

		// 만약 사이클이 있어서 무한히 도는 형태면 0 출력
		if (resultCnt != n)
			System.out.println(0);
		else
			System.out.println(sb);
	}
}