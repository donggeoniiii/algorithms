// 수 고르기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 수열의 길이, 최소 차이
	static int n, m;

	// 수열
	static int[] nums;

	// 출력할 정답, m 이상이면서 두 수 사이 가장 작은 차이
	static int answer;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}

		// 초기화
		answer = Integer.MAX_VALUE;
	}

	private static void solution() {
		// 편한 탐색을 위한 정렬
		Arrays.sort(nums);

		// O(n^2)
		/*for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (nums[j] - nums[i] >= m) {
					answer = Math.min(answer, nums[j] - nums[i]);
				}
			}
		}*/

		/*
		투포인터 풀이
		만약 최초로 조건을 만족하는 지점을 찾기만 하면 그 반복에서 그 이상은 탐색해 볼 필요가 없다
		 */
		int end = 0;
		for (int start = 0; start < n; start++) {
			while (end < n && nums[end] - nums[start] < m) {
				end++;
			}
			if (end == n) {
				break;
			}
			// 찾은 값
			answer = Math.min(nums[end] - nums[start], answer);
		}


		// 정답 출력
		System.out.println(answer);
	}
}