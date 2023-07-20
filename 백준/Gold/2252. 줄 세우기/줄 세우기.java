// 줄 세우기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();

	// 사람 수
	static int n;

	// 알려진 데이터의 수
	static int m;

	// 위상정렬을 위한 배열 - 본인보다 키 작은 사람의 수 카운트
	static int[] shorterCnt;

	// 위상정렬을 위한 인접리스트 - 본인보다 키 큰 사람 이름 저장
	static List<Integer>[] adj;

	// 위상정렬을 위한 bfs - priority queue
	static PriorityQueue<Integer> queue;

	// 위상정렬을 위한 bfs
	static void findHeightOrder() {

		// 더 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			// 다음 탐색 지점 가져오기
			int cur = queue.poll();

			// 애초에 queue에 들어오려면 진입차수가 0이니까 출력형태에 저장
			sb.append(cur).append(" ");

			// 인접리스트에서 이어지는 애들 가져오기
			for (int next : adj[cur]) {
				// 진입차수 -1
				shorterCnt[next]--;

				// 만약 0이 되었으면 queue에 추가
				if (shorterCnt[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 사람의 수, 키 비교 데이터의 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 키가 큰 사람의 이름을 저장할 인접리스트
		adj = new LinkedList[n + 1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new LinkedList<>();
		}

		// 자기보다 키 작은 사람의 수 카운트 배열
		shorterCnt = new int[n + 1];

		// 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int shorter = Integer.parseInt(st.nextToken());
			int taller = Integer.parseInt(st.nextToken());

			// 데이터 저장
			adj[shorter].add(taller);
			shorterCnt[taller]++;
		}

		// bfs를 위한 queue 선언
		queue = new PriorityQueue<>();

		// 진입차수 0인 사람을 찾으면 queue에 추가
		for (int i = 1; i <= n; i++) {
			if (shorterCnt[i] == 0) {
				queue.offer(i);
			}
		}

		// bfs on
		findHeightOrder();

		// 정답 출력
		System.out.println(sb);
	}
}