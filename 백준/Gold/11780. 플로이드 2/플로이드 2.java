// 플로이드2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 도시의 개수
	static int n;

	// 버스의 개수
	static int m;

	// 최소비용 저장 테이블
	static int[][] minCost;

	// 최소비용 이동을 위한 다음 경로 저장 테이블
	static int[][] next;

	// 불가능 표시
	static final int IMPOSSIBLE = 0x3f3f3f3f;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		m = Integer.parseInt(br.readLine());

		minCost = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(minCost[i], IMPOSSIBLE);
			minCost[i][i] = 0;
		}
		next = new int[n+1][n+1];

		for (int i = 1; i <= m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			minCost[src][dest] = Math.min(minCost[src][dest], cost);
			next[src][dest] = dest; // src -> dest로 가는 최소비용 경로 초기화
		}
	}

	private static void solution() {
		for (int mid = 1; mid <= n; mid++) {
			for (int src = 1; src <= n; src++) {
				for (int dest = 1; dest <= n; dest++) {
					int newCost = minCost[src][mid] + minCost[mid][dest];
					// 만약 최소비용 경로가 변경되면 반영
					if (newCost < minCost[src][dest]) {
						minCost[src][dest] = newCost;
						next[src][dest] = next[src][mid];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		// 출력 1: 최소비용
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				if (minCost[r][c] == IMPOSSIBLE) {
					sb.append(0).append(" ");
				}
				else sb.append(minCost[r][c]).append(" ");
			}
			sb.append("\n");
		}

		// 출력 2: 도시 i에서 j로 가는 최소 비용 경로의 길이와 경로
		for (int src = 1; src <= n; src++) {
			for (int dest = 1; dest <= n; dest++) {
				int cdx = src;

				// 경로 저장할 list
				List<Integer> path = new LinkedList<>();

				// 시작점과 도착점이 같으면 0
				if (cdx == dest){
					sb.append(0).append("\n");
					continue;
				}

				// 도달할 수 없는 지점이면 0
				if (minCost[src][dest] == IMPOSSIBLE) {
					sb.append(0).append("\n");
					continue;
				}

				// 도착점에 도달할 때까지 다음 경로로 계속 이동
				while (cdx != dest) {
					path.add(cdx);
					cdx = next[cdx][dest];
				}

				// 도착점 추가
				path.add(cdx);

				// 경로의 길이 입력
				sb.append(path.size()).append(" ");

				// 경로 입력
				for (int cur : path) {
					sb.append(cur).append(" ");
				}

				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}