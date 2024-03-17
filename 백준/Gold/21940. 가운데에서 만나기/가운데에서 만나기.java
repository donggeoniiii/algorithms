// 가운데에서 만나기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 도시의 개수, 도로의 개수
	private static int n, m;

	// 두 구간 사이 최단 이동 시간
	private static int[][] minTime;

	private static final int IMPOSSIBLE = 987987987;

	// 준형이와 친구들의 총 인원
	private static int k;

	// 친구들의 사는 지역
	private static List<Integer> home;

	// 가능한 도시
	static List<int[]> answer;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		minTime = new int[n+1][n+1];
		for (int i = 1; i <= n; i++) {
			Arrays.fill(minTime[i], IMPOSSIBLE);
			minTime[i][i] = 0;
		}

		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			minTime[l][r] = Math.min(minTime[l][r], time);
		}

		// 준형이와 친구들의 인원
		k = Integer.parseInt(br.readLine());

		// 살고 있는 도시의 번호
		home = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= k; i++) {
			home.add(Integer.parseInt(st.nextToken()));
		}
	}

	private static void solution() {
		// 제일 오래 걸리는 애가 빨리 도착하는 경로가 중요함
		// 왕복시간은 편도 x2가 아님
		for (int mid = 1; mid <= n; mid++) {
			for (int src = 1; src <= n; src++) {
				for (int dest = 1; dest <= n; dest++) {
					int curTime = minTime[src][mid] + minTime[mid][dest];
					minTime[src][dest] = Math.min(curTime, minTime[src][dest]);
				}
			}
		}

		// 도시별로 가장 오래 걸리는 시간 저장
		answer = new LinkedList<>();
		for (int dest = 1; dest <= n; dest++) {
			int maxTime = 0;
			for (int src : home) {
				maxTime = Math.max(maxTime, minTime[src][dest] + minTime[dest][src]);
			}
			answer.add(new int[] {maxTime, dest});
		}

		// 오름차순 출력
		answer.sort(Comparator.comparingInt(o -> o[0]));
		StringBuilder sb = new StringBuilder();
		for (int[] time : answer) {
			if (time[0] > answer.get(0)[0]) break;

			sb.append(time[1]).append(" ");

		}
		System.out.println(sb);
	}
}