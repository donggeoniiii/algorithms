// 덩치

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 사람 수
	private static int n;

	// 덩치 정보
	private static int[][] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		size = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			size[i][0] = w;
			size[i][1] = h;
		}

		// 덩치 등수
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(countRank(i)).append(" ");
		}

		System.out.println(sb);
	}

	private static int countRank(int cur) {
		int cw = size[cur][0];
		int ch = size[cur][1];

		// 더 덩치가 큰 사람 카운트
		int supCnt = 0;

		for (int i = 0; i < n; i++) {
			if (i == cur) continue;

			// 체중도 키도 둘다 큰 사람이 있으면 카운트
			if (size[i][0] > cw && size[i][1] > ch) supCnt++;
		}

		// 등수 == 나보다 큰 사람의 수 + 1
		return supCnt + 1;
	}
}