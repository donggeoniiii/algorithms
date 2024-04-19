// 카드

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		long[] nums = new long[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}

		// <숫자, 숫자별 카운트>
		HashMap<Long, Integer> numCnt = new HashMap<>();
		for (int i = 0; i < n; i++) {
			numCnt.put(nums[i], numCnt.getOrDefault(nums[i], 0) + 1);
		}

		int maxCnt = 0;
		long answer = 0;
		for (long num : numCnt.keySet()) {
			if (numCnt.get(num) > maxCnt) {
				maxCnt = numCnt.get(num);
				answer = num;
			}
			else if (numCnt.get(num) == maxCnt) {
				answer = Math.min(answer, num);
			}
		}

		System.out.println(answer);
	}
}