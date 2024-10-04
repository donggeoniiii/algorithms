// 가장 긴 증가하는 부분수열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
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

		// memo[i]: i까지 최대 부분수열의 길이
		int[] memo = new int[n];

		// 일단 기본적으로 본인 스스로가 수열의 시작이 될 수 있음
		Arrays.fill(memo, 1);

		// 현재까지 최장 증가 수열의 길이(기본값 1)
		int lis = 1;

		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					memo[i] = Math.max(memo[i], memo[j] + 1);
				}
			}
			lis = Math.max(lis, memo[i]);
		}

		StringBuilder answer = new StringBuilder();
		answer.append(lis).append("\n");
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = n-1; i >= 0; i--) {
			if (memo[i] == lis) {
				stack.push(nums[i]);
				lis--;
			}
		}
		while (!stack.isEmpty()) {
			answer.append(stack.pop()).append(" ");
		}

		System.out.println(answer);
	}
}