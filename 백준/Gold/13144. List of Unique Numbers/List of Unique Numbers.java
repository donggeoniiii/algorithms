// List of Unique Numbers

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 수열의 길이
	static int n;

	// 수열
	static int[] nums;

	// 경우의 수
	static long answer;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		answer = 0;
	}

	private static void solution() {
		// 부분 수열에 있는 숫자 별 카운트
		int[] present = new int[100001];

		int start = 0, end = 0;
		while (start < n) {
			// 중복된 값을 만날 때까지 end 포인터 전진
			while (end < n && present[nums[end]] == 0) {
				present[nums[end]]++;
				end++;
			}

			// 중복된 값을 만나면, 부분수열의 길이만큼 경우의 수 추가하고
			answer += end - start;

			// 뒤에서 하나 빼보기
			present[nums[start]]--;
			start++;
		}

		System.out.println(answer);
	}
}