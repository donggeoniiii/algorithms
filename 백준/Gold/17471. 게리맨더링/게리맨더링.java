// 게리맨더링

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 구역수
	static int n;

	// 인접리스트
	static LinkedList<Integer>[] adj;

	// 구역별 인구 정보
	static int[] population;

	// 이번에 선택한 한 지역구에 속하는 구역
	static boolean[] selected;

	// 선거구 인구차이의 최솟값
	static int minPopulationGap = Integer.MAX_VALUE;

	// bfs를 위한 방문배열
	static boolean[] visited;

	// bfs
	static void checkAdjacentArea(int src) {

		// bfs를 위한 queue
		Queue<Integer> queue = new LinkedList<>();

		// 시작점 방문체크
		queue.offer(src);
		visited[src] = true;

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			// 다음 방문좌표
			int cur = queue.poll();

			// 인접한 애들 체크
			for (int next : adj[cur]) {

				// 다른 선거구면 스킵
				if (selected[cur] != selected[next])
					continue;

				// 이미 방문했으면 스킵
				if (visited[next])
					continue;

				// 같은 선거구면 방문체크
				visited[next] = true;

				// queue에 추가
				queue.offer(next);
			}
		}
	}

	// bfs를 통해 지역구 개수 2개인지 확인하기
	static boolean countAdjacentField() {
		// 방문배열 초기화
		visited = new boolean[n + 1];

		// 지역구 덩어리(?) 수
		int areaCnt = 0;

		for (int i = 1; i <= n; i++) {
			// 처음 보는 좌표를 만나면 bfs on
			if (!visited[i]) {
				checkAdjacentArea(i);

				// 그리고 bfs 개수 = 인접한 지역 수이므로 체크
				areaCnt++;
			}
		}

		// 만약 지역구 덩어리가 2개면 true 반환
		return areaCnt == 2;
	}

	// 백트래킹 알고리즘
	static void findShortestPopulationGap(int cnt, int start, int limit) {

		// pruning: 만약 두 선거구 인구가 같은 경우를 발견하면 종료
		if (minPopulationGap == 0)
			return;

		// base case: 정해놓은 숫자만큼 한쪽 선거구를 뽑아놓으면 종료
		if (cnt >= limit) {

			// 만약 두 선거구가 뭉쳐서 두 구역으로 분할되는게 아니면
			if (!countAdjacentField())
				// 그대로 종료
				return;

			// 인구차이 계산
			int populationA = 0;
			int populationB = 0;
			for (int i = 1; i <= n; i++) {
				if (selected[i])
					populationA += population[i];
				else
					populationB += population[i];
			}

			// 인구차이 최솟값 갱신
			minPopulationGap = Math.min(minPopulationGap, Math.abs(populationA - populationB));

			return;
		}

		// recursive case
		for (int idx = start; idx <= n; idx++) {
			// 선택하지 않았다면
			if (!selected[idx]) {
				// 선택하기
				selected[idx] = true;
				// 다음 선택으로 이동
				findShortestPopulationGap(cnt + 1, idx + 1, limit);
				// 다른 선택을 위해 선택 취소
				selected[idx] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 구역 수
		n = Integer.parseInt(br.readLine());

		// 인접리스트 초기화
		adj = new LinkedList[n + 1];
		for (int idx = 1; idx <= n; idx++) {
			adj[idx] = new LinkedList<>();
		}

		// 인구 정보 저장 배열
		population = new int[n + 1];

		// 구역별 인구 정보
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 인구정보 입력
		for (int idx = 1; idx <= n; idx++) {
			population[idx] = Integer.parseInt(st.nextToken());
		}

		// 인접정보 입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			// 인접한 지역의 수(안써도 됨)
			st.nextToken();

			// 인접정보
			while (st.hasMoreTokens()) {
				int cur = Integer.parseInt(st.nextToken());
				adj[i].add(cur);
			}
		}

		// 지역구별 선택내역 저장 배열
		selected = new boolean[n + 1];

		// 백트래킹 on
		for (int i = 1; i <= n / 2; i++) {

			// 백트래킹 알고리즘
			findShortestPopulationGap(0, 1, i);
		}

		// 결과 출력
		System.out.println(minPopulationGap == Integer.MAX_VALUE ? -1 : minPopulationGap);
	}
}