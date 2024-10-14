// N과 M(9)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int n, m;

	private static int[] selectedNums;

	private static boolean[] selected;

	private static StringBuilder answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 사전순 출력을 위해 정렬
		Arrays.sort(nums);

		selectedNums = new int[m];
		selected = new boolean[n];

		answer = new StringBuilder();
		countAllSequences(nums, 0);

		System.out.println(answer);
	}

	private static void countAllSequences(int[] nums, int curCount) {
		// base case: 길이가 m이면 종료
		if (curCount >= m) {
			for (int i = 0; i < m; i++) {
				answer.append(selectedNums[i]).append(" ");
			}
			answer.append("\n");
			return;
		}

		int prev = -1; // 정렬된 배열에서만 사용 가능
		for (int i = 0; i < n; i++) {
			// 이미 해당 index를 포함한 경우 스킵
			if (selected[i]) continue;

			int cur = nums[i];

			// 이미 이전에 해당 값을 사용한 적이 있으면 스킵
			if (prev == cur) continue;

			selected[i] = true;
			selectedNums[curCount] = cur;
			prev = cur;
			countAllSequences(nums, curCount + 1);
			selected[i] = false;
		}
	}

}