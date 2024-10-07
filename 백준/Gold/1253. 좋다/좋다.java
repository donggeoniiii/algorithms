// 좋다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);

		int goodCount = 0;
		for (int i = 0; i < n; i++) {
			// 배열 내 두 수의 합으로 찾을 수 있는지 확인할 숫자
			if (isExist(nums, i)) {
				goodCount++;
			}
		}

		System.out.println(goodCount);
	}

	private static boolean isExist(int[] nums, int curIdx) {
		for (int i = 0; i < nums.length; i++) {
			if (i == curIdx) continue;

			int target = nums[curIdx] - nums[i];

			int index = lowerBound(nums, target);

			while (index < nums.length && nums[index] == target) {
				if (index != curIdx && index != i) {
					return true;
				}
				index++;
			}
		}
		
		return false;
	}

	private static int lowerBound(int[] nums, int target) {
		int start = 0;
		int end = nums.length;

		while (start < end) {
			int mid = (start + end) / 2;
			if (nums[mid] >= target) {
				end = mid;
			}
			else start = mid + 1;
		}

		return end;
	}

}