// 우주선과의 교신

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 우주신들의 수, 연결된 신들과 통로 개수
	static int n, m;

	// n명의 서로 다른 좌표
	static int[][] loc;

	// 우주신들의 좌표
	static List<long[]> adj;

	// 연결된 지점을 체크하기 위한 union-find
	static int[] p;

	static int find(int node) {
		if (p[node] < 0) {
			return node;
		}
		return p[node] = find(p[node]);
	}

	static boolean isUnion(int n1, int n2) {
		n1 = find(n1);
		n2 = find(n2);

		if (n1 == n2) {
			return true;
		}
		p[n2] = n1;
		return false;
	}

	static int wayCnt;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 우주신들의 좌표 저장
		loc = new int[n+1][2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			loc[i][0] = r;
			loc[i][1] = c;
		}

		// 이미 연결된 통로 정보 반영
		p = new int[n+1];
		for (int i = 1; i <= n; i++) {
			p[i] = -1;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			// 중복된 선이 있는 경우를 고려
			if (!isUnion(l, r)) {
				wayCnt++;
			}
		}

		// 각 우주신 및 선자씨 사이 거리 저장
		adj = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				int rDiff = loc[i][0] - loc[j][0];
				int cDiff = loc[i][1] - loc[j][1];
				long dist = (long) rDiff * rDiff + (long) cDiff * cDiff;
				adj.add(new long[] {dist, i, j});
			}
		}
	}

	private static void solution() {
		// 거리가 짧은 순으로 합쳐야 되니까
		adj.sort(Comparator.comparingLong(o -> o[0]));

		// 이미 연결된 라인까지 합쳐서 n개가 되면 종료
		double totalDist = 0;
		for (long[] cur : adj) {
			long dist = cur[0];
			int l = (int) cur[1];
			int r = (int) cur[2];

			// 이미 연결된 점이면 스킵
			if (isUnion(l, r)) {
				continue;
			}

			// 새롭게 연결했으니 길이 더하기
			totalDist += Math.sqrt(dist);
			wayCnt++;

			if (wayCnt == n-1) {
				break;
			}
		}

		System.out.printf("%.2f", totalDist);
	}
}