// 리모컨

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 이동하려고 하는 채널
	private static int n;

	// 고장난 버튼의 개수
	private static int m;
	
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		m = Integer.parseInt(br.readLine());
		
		// isOff[i] : i번 버튼 고장
		boolean[] isOff = new boolean[10];
		if (m > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				int num = Integer.parseInt(st.nextToken());
				isOff[num] = true;
			}
		}
		// 숫자버튼 누르는 방법 중에 가장 빠른 방법
		int minCnt = INF;
		for (int num = 0; num < 1000001; num++) {
			char[] digit = String.valueOf(num).toCharArray();

			boolean broken = false;
			for (char c : digit) {
				if (isOff[c - '0']) {
					broken = true;
					break;
				}
			}

			// 해당 숫자가 안눌러지면 패스
			if (broken) continue;

			minCnt = Math.min(minCnt, digit.length + Math.abs(n - num));
		}

		// 그냥 +- 눌러서 이동하는 방법중에 가장 빠른 방법
		minCnt = Math.min(minCnt, Math.abs(n - 100));

		System.out.println(minCnt);
	}
}