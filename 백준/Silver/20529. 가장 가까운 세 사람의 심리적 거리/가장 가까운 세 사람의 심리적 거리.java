// 가장 가까운 세 사람의 심리적 거리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 학생의 수
	private static int n;

	// 학생들의 mbti
	private static char[][] mbti;

	private static int minDist;

	private static int[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());

			// pruning: 만약 n이 16 x 3 == 48명 이상이라면 무조건 0
			if (n >= 48) {
				sb.append(0).append("\n");
				br.readLine();
				continue;
			}

			mbti = new char[n][4];
			String[] input = br.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				mbti[i] = input[i].toCharArray();
			}

			// 3명씩 선택해서 최소인 경우 찾기
			minDist = Integer.MAX_VALUE;
			selected = new int[3];
			findMinimumCase(-1, 0);

			sb.append(minDist).append("\n");
		}
		System.out.println(sb);
	}

	private static void findMinimumCase(int idx, int cnt) {
		if (minDist == 0) return;

		if (idx == n) return;

		if (cnt == 3) {
			int cdx = 0;
			int mdx1 = selected[cdx++];
			int mdx2 = selected[cdx++];
			int mdx3 = selected[cdx];

			int dist1 = 0;
			int dist2 = 0;
			int dist3 = 0;
			for (int i = 0; i < 4; i++) {
				if (mbti[mdx1][i] != mbti[mdx2][i]) dist1++;
				if (mbti[mdx2][i] != mbti[mdx3][i]) dist2++;
				if (mbti[mdx3][i] != mbti[mdx1][i]) dist3++;
			}

			minDist = Math.min(minDist, dist1 + dist2 + dist3);
			return;
		}

		for (int i = idx + 1; i < n; i++) {
			selected[cnt] = i;
			findMinimumCase(i, cnt + 1);
		}
	}
}