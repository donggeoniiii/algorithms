// 합이 0

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 학생의 수
	static int n;

	// 학생들의 코딩 실력
	static int[] stats;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		stats = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			stats[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void solution() {
		// 이진탐색을 위해 정렬
		Arrays.sort(stats);

		// 정답이 될 쌍 개수
		long answer = 0;

		/*
		두 명을 고르고 팀에 들어갈 나머지 한 명을 찾는다
		stats[i] + stats[j] + stats[k] = 0 이므로
		-(stats[i] + stats[j])의 값을 가지는 stats[k]가 있는지 탐색
		*/
		for (int i = 0; i < n-1; i++) {
			for (int j = i + 1; j < n; j++) {
				int target = - (stats[i] + stats[j]);

				// 같은 코딩 능력치(?)를 가진 사람이 많을 수 있으니까
				// lowerbound
				int ldx = lowerBound(j + 1,  n, target);

				// lowerbound가 범위를 벗어나거나 값을 찾지 못하면 없다는 뜻이므로
				if (ldx == n || stats[ldx] != target) {
					continue;
				}

				// upperbound
				int udx = upperBound(j + 1, n, target);

				// 중복된 값에 대해 동일하게 처리
				answer += udx - ldx;
			}
		}

		// 정답 출력
		System.out.println(answer);
	}

	private static int lowerBound(int start, int end, int target) {
		while (start < end) {
			int mid = (start + end) / 2;
			if (stats[mid] >= target) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}

		return end;
	}

	private static int upperBound(int start, int end, int target) {
		while (start < end) {
			int mid = (start + end) / 2;
			if (stats[mid] > target) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}

		return end;
	}
}