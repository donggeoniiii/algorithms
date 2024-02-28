// 용액

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 전체 용액의 수
	static int n;

	// 용액의 특성값
	static int[] nums;

	// 최소값
	static long minDiffToZero;

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
	}

	private static void solution() {
		// 최솟값
		minDiffToZero = Long.MAX_VALUE;

		// 최소일 때의 두 값
		int min1 = 0;
		int min2 = 0;

		for (int i = 0; i < n; i++) {
			// 이번 index의 값을 가지고 이분탐색으로 가장 0에 근접한 값 찾기
			// nums[i] + x 의 값이 최소인 x를 찾을거니까 -nums[i]의 위치로 이분탐색
			int cur = nums[i];

			int start = i + 1; // i보다 작은 범위는 이미 봤으니까 볼 필요 없음
			int end = n-1;

			while (start <= end) {
				int mid = (start + end) / 2;
				long curSum = Math.abs(cur + nums[mid]);
				// 일단 구한 합이 현재까지 최소값보다 작으면 교체
				if (curSum < minDiffToZero) {
					minDiffToZero = curSum;
					min1 = cur;
					min2 = nums[mid];
				}

				// 만약 이번에 찾은 지점의 값이 -nums[i]보다 작으면
				if (nums[mid] >= -cur) {
					end = mid - 1;
				}
				else {
					start = mid + 1;
				}
			}
		}

		// 출력
		if (min1 > min2) {
			int temp = min1;
			min1 = min2;
			min2 = temp;
		}

		System.out.println(min1 + " " + min2);
	}

}