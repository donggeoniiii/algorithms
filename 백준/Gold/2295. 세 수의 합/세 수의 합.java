// 세 수의 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {
	// 입력 길이
	static int n;

	// 수 집합
	static int[] nums;

	// 두 수의 합을 저장할 리스트
	static List<Integer> two;

	// 중복 제거 리스트
	static List<Integer> uniqueTwo;

	static int answer;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		answer();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		// 빠른 최대값 탐색을 위해 정렬
		Arrays.sort(nums, 0, n);

		two = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				two.add(nums[i] + nums[j]);
			}
		}

		// 중복 제거
		uniqueTwo = new ArrayList<>();
		int idx = 0;
		for (int i = 0; i < two.size(); i++) {
			if (i == 0 || !Objects.equals(two.get(i), two.get(i - 1))) {
				uniqueTwo.add(two.get(i));
			}
		}

		// 이분탐색을 위해 정렬
		Collections.sort(uniqueTwo);
	}

	private static void solution() {
		// 세 수 i, j, k의 합 d가 집합 내에 존재한다 == i, j의 합과 같은 d - k가 집합 내에 존재한다
		for (int i = n-1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				// 가장 큰 값부터 탐색하니까 찾는 순간 바로 종료
				if (bSearch(nums[i]-nums[j], 0, uniqueTwo.size()-1)) {
					answer = nums[i];
					return;
				}
			}
		}
	}

	private static boolean bSearch(int target, int start, int end) {
		while (start <= end) {
			// 중간값
			int midIdx = (start + end) / 2;
			int midNum = uniqueTwo.get(midIdx);

			if (midNum == target) {
				return true;
			}
			else if (midNum > target) {
				end = midIdx - 1;
			}
			else {
				start = midIdx + 1;
			}
		}

		// 못 찾으면 해당 없음
		return false;
	}

	private static void answer() {
		System.out.println(answer);
	}
}