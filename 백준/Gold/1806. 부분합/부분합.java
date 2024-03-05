// 부분합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 수열의 길이, 기준이 되는 부분합
	static int n, s;

	// 수열
	static int[] nums;

	// 가장 짧은 수열의 길이(정답)
	static int answer;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 초기화
		answer = Integer.MAX_VALUE;
	}

	private static void solution() {
		// 투포인터 알고리즘
		int end = 0;
		// 부분합
		int curSum = nums[0];
		for (int start = 0; start < n; start++) {
			// 합이 넘기 전까지 계속 길이 늘리고
			while (end < n && curSum < s) {
				end++;

				// 넘어가는 경우 빼고
				if (end != n) {
					curSum += nums[end];
				}
			}

			// out of index
			if (end == n) {
				break;
			}

			// 부분합 간의 최소 구하기
			answer = Math.min(answer, end - start + 1);

			// 다음 탐색을 위해 시작점이 땡겨지니까 부분합도 그에 맞게 조정
			curSum -= nums[start];
		}

		// 만약 정답이 갱신이 안됐으면 0 출력
		if (answer == Integer.MAX_VALUE) {
			answer = 0;
		}

		System.out.println(answer);
	}

}