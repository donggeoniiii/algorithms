// 나무 자르기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 나무의 수, 집으로 가져가려는 나무의 길이
	static int n, m;

	// 나무의 높이
	static int[] trees;

	// 나무 중 최대 높이
	static int maxLength;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		trees = new int[n];
		maxLength = 0;
		for (int i = 0; i < n; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			maxLength = Math.max(maxLength, trees[i]);
		}
	}

	private static void solution() {
		long start = 0;
		long end = maxLength;

		while (start < end) {
			long mid = (start + end + 1) / 2;

			if (solve(mid)) {
				start = mid;
			} else {
				end = mid - 1;
			}
		}

		System.out.println(start);
	}

	private static boolean solve(long length) {
		long count = 0;

		// 절단기로 잘리고 남은 윗부분 길이 합산
		for (int i = 0; i < n; i++) {
			if (trees[i] <= length) {
				continue;
			}
			count += trees[i] - length;
		}

		return count >= m;
	}
}