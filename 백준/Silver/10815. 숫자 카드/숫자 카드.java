// 숫자카드

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 상근이가 가지고 있는 카드 개수
	static int n;

	// 숫자카드
	static int[] nums;

	// 찾아야 할 정수 개수
	static int m;

	// 찾아야 할 정수 모음
	static int[] numsToFind;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		m = Integer.parseInt(br.readLine());

		numsToFind = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			numsToFind[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void solution() {
		// 이분탐색을 위한 정렬
		Arrays.sort(nums);

		StringBuilder sb = new StringBuilder();

		// 찾기
		for (int i = 0; i < m; i++) {
			int cur = numsToFind[i];

			if (inDeck(cur)) {
				sb.append(1).append(" ");
			}
			else {
				sb.append(0).append(" ");
			}
		}

		System.out.println(sb);
	}

	private static boolean inDeck(int target) {
		int start = 0;
		int end = n - 1;

		while (start <= end) {
			int mdx = (start + end) / 2;
			int mid = nums[mdx];

			if (mid == target) {
				return true;
			}
			// 중간값보다 구하려는 값이 더 크면 범위가 더
			else if (mid < target) {
				start = mdx + 1;
			}
			// 중간값보다 구하려는 값이 더 작으면 상한값 조정
			else {
				end = mdx - 1;
			}
		}
		return false;
	}
}