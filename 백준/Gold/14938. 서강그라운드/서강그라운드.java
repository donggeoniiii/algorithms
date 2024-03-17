// 서강그라운드

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 지역의 개수, 예은이의 수색 범위, 길의 개수
	static int n, m, r;

	// 각 구역에 있는 아이템의 수
	static int[] item;

	// 거리 정보
	static int[][] dist;

	// 해당 시점에서 시작했을 때 파밍 가능한 아이템 수
	static int[][] next;

	static int maxItem = -1;

	static final int IMPOSSIBLE = 987987987;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		item = new int[n+1];
		for (int i = 1; i <= n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		dist = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], IMPOSSIBLE);
			dist[i][i] = 0;
		}

		// 최단거리 이동 시 다음 경로
		next = new int[n+1][n+1];

		for (int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());

			dist[left][right] = len;
			dist[right][left] = len;
			next[left][right] = right;
			next[right][left] = left;
		}
	}

	private static void solution() {
		// 일단 최단 이동 거리 및 경로 표시하기
		for (int mid = 1; mid <= n; mid++) {
			for (int src = 1; src <= n; src++) {
				for (int dest = 1; dest <= n; dest++) {
					int curDist = dist[src][mid] + dist[mid][dest];
					if (curDist < dist[src][dest]) {
						dist[src][dest] = curDist;
						next[src][dest] = next[src][mid];
					}
				}
			}
		}

		// 이번에 이동한 경로 표시
		maxItem = 0;
		for (int i = 1; i <= n; i++) {

			// 이번 시작지점에서 방문 가능한 지역 리스트
			boolean[] visited = new boolean[n+1];

			for (int j = 1; j <= n; j++) {
				// 만약 최단거리가 m보다 크면 없는 경로
				if (dist[i][j] > m) {
					continue;
				}

				// 시작 포인트
				int cur = i;

				// 도착하기 전까지 합산
				while (cur != j) {
					visited[cur] = true;
					cur = next[cur][j];
				}

				// 마지막 위치 방문체크
				visited[j] = true;
			}

			// 이번 탐색에 모은 아이템 수
			int curItem = 0;

			// 방문한 지역 아이템 개수 합산
			for (int j = 1; j <= n; j++) {
				if (visited[j]) {
					curItem += item[j];
				}
			}

			// 아이템 개수 최고값 갱신
			maxItem = Math.max(maxItem, curItem);
		}

		System.out.println(maxItem);
	}
}