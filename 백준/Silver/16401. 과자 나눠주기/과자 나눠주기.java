// 과자 나눠주기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 조카의 수, 과자의 수
	static int m, n;

	// 과자의 길이
	static int[] snacks;

	// 가장 긴 과자의 길이
	static int maxLength;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		snacks = new int[n];
		maxLength = -1;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			snacks[i] = Integer.parseInt(st.nextToken());
			maxLength = Math.max(maxLength, snacks[i]);
		}
	}

	private static void solution() {
		long start = 0;
		long end = maxLength;

		while (start < end) {
			long mid = (start + end + 1) / 2;

			// m개 이상으로 나눠서 과자를 줄 수 있으면 더 긴 길이로 테스트
			if (solve(mid)) {
				start = mid;
			}
			// 안되면 더 작은 크기로 찾아보기
			else {
				end = mid - 1;
			}
		}
		
		System.out.println(start);
	}

	private static boolean solve(long length) {
		if (length == 0) {
			return false;
		}

		long count = 0;
		for (int i = 0; i < n; i++) {
			count += snacks[i] / length;
		}

		return count >= m;
	}
}