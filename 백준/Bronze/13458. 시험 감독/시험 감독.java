// 시험 감독

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 응시자의 수
	private static int[] test;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		test = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			test[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		// 각 시험장 별 감독관 수 구하기
		long total = 0;
		for (int i = 0; i < n; i++) {
			int remains = test[i];

			// 1. 일단 총감독관이 먼저 커버할 수 인원만큼 커버
			remains -= b;
			total++;

			// 2. 커버하고 남은 인원이 있다면 부감독관으로 커버
			if (remains > 0) {
				total += remains / c;

				// 나머지 처리
				if (remains % c > 0) total++;
			}
		}

		System.out.println(total);
	}
}