// 백양로 브레이크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 건물의 수, 길의 수
	private static int n, m;

	// 길 정보 입력
	private static int[][] cost;

	private static final int IMPOSSIBLE = 987987987;

	// 질문의 수
	private static int k;

	// 질문 목록
	private static List<int[]> question;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		cost = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(cost[i], IMPOSSIBLE);
			cost[i][i] = 0;
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int isTwoWay = Integer.parseInt(st.nextToken());

			// 일방통행이면 뚫린쪽은 0, 아닌쪽은 1로 cost 설정
			if (isTwoWay == 0) {
				cost[l][r] = 0;
				cost[r][l] = 1;
			}
			// 양방통행이면 양쪽 다 0으로 cost 설정
			else {
				cost[l][r] = 0;
				cost[r][l] = 0;
			}
		}

		k = Integer.parseInt(br.readLine());

		question = new LinkedList<>();
		for (int i = 1; i <= k; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			question.add(new int[] {src, dest});
		}
	}

	private static void solution() {
		for (int mid = 1; mid <= n; mid++) {
			for (int src = 1; src <= n; src++) {
				for (int dest = 1; dest <= n; dest++) {
					int curCost = cost[src][mid] + cost[mid][dest];
					cost[src][dest] = Math.min(cost[src][dest], curCost);
				}
			}
		}

		// 질문 처리하기
		StringBuilder sb = new StringBuilder();
		for (int[] q : question) {
			int src = q[0];
			int dest = q[1];

			sb.append(cost[src][dest]).append("\n");
		}

		System.out.println(sb);
	}
}