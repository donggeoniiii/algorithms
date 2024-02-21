// Fly me to the Alpha Centauri

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 테스트 케이스 개수
	static int t;

	// 테스트 케이스별 입력값
	static long[][] tc;

	// 정답
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		answer();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		t = Integer.parseInt(br.readLine());

		// tc[i][j] : j번째 테스트케이스, i=0이면 x, i=1이면 y좌표, i=2면 정답
		tc = new long[3][t+1];

		StringTokenizer st;
		for (int testcase = 1; testcase <= t; testcase++) {
			st = new StringTokenizer(br.readLine());
			tc[0][testcase] = Long.parseLong(st.nextToken());
			tc[1][testcase] = Long.parseLong(st.nextToken());
		}

		// 테스트케이스별 결과
		sb = new StringBuilder();
	}

	private static void solution() {
		// 테스트 케이스 개수만큼 실행
		int curTest = 1;
		while (curTest <= t) {
			int curAnswer = 0;

			// 두 지점 사이 거리
			long dist = tc[1][curTest] - tc[0][curTest];

			// 한 턴에 최대로 이동할 수 있는 거리
			double max = Math.sqrt(dist);

			// 제곱수인 경우
			if (max % 1 == 0) {
				curAnswer = (int) (2 * max - 1);
			} else {
				double next = Math.ceil(max);

				if (dist > Math.pow(next, 2) - next) {
					curAnswer = (int) (2 * next - 1);
				}
				else {
					curAnswer = (int) (2 * next - 2);
				}
			}

			// 정답 입력
			sb.append(curAnswer).append("\n");

			// 다음 테스트케이스로 이동
			curTest++;
		}
	}

	private static void answer() {
		System.out.println(sb);
	}
}