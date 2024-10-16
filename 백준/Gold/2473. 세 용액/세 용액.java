// 세 용액

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] solutions = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			solutions[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(solutions);

		// 더하는 상황에 int 최대 범위를 벗어날 수 있음
		long minDiff = Long.MAX_VALUE;
		long[] answer = new long[3];

		for (int i = 0; i < n; i++) {
			// 0으로 만드는 경우를 찾으면 출력 후 종료
			if (minDiff == 0) {
				printAnswer(answer);
				return;
			}

			for (int j = i + 1; j < n; j++) {
				long sol1 = solutions[i];
				long sol2 = solutions[j];

				// 찾아야 할 용액: 0 - sol1 - sol2의 특성값을 가진 것
				long target = - sol1 - sol2;

				int start = 0;
				int end = n;

				// 해당 특성값을 가진 용액이 없는 경우 처음으로 특성값보다 값이 큰 용액 찾기
				while (start < end) {
					int mid = (start + end) / 2;

					if (solutions[mid] >= target) {
						end = mid;
					} else {
						start = mid + 1;
					}
				}

				// out of bound, sol1 sol2와 겹치는 경우 방지를 위해 위아래로 2칸까지 탐색
				for (int dt = -2; dt <= 2; dt++) {
					int cur = end + dt;

					if (cur < 0 || cur >= n) continue;

					if (cur == i || cur == j) continue;

					int sol3 = solutions[cur];

					long curCase = sol1 + sol2 + sol3;

					if (Math.abs(curCase) < minDiff) {
						minDiff = Math.abs(curCase);

						answer[0] = sol1;
						answer[1] = sol2;
						answer[2] = sol3;
					}
				}
			}
		} // for-loop

		// 특성값이 0에 가장 가까워지는 경우 출력
		printAnswer(answer);
	}

	private static void printAnswer(long[] answer) {
		Arrays.sort(answer);

		StringBuilder sb = new StringBuilder();
		for (long sol : answer) {
			sb.append(sol).append(" ");
		}

		System.out.println(sb);
	}
}