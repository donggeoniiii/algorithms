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
		for (int cur = 0; cur < n; cur++) {
			// 배열 내 두 수의 합으로 찾을 수 있는지 확인
			if (isExist(nums, cur)) {
				goodCount++;
			}
		}

		System.out.println(goodCount);
	}

	private static boolean isExist(int[] nums, int curIdx) {
		for (int i = 0; i < nums.length; i++) {
			if (i == curIdx) continue;

			int target = nums[curIdx] - nums[i];

			// 같은 수가 여러 개인 경우에 대비해 lower bound 탐색
			int index = lowerBound(nums, target);

			// 발견한 값이 원래 값도 아니고 합이 될 두 수 중 다른 하나도 아니면 카운트 증가 후 true 반환
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