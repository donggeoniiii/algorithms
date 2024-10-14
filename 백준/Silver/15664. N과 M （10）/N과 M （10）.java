// N과 M(10)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m;

	static int[] selectedNums;

	static boolean[] selected;

	static StringBuilder sb = new StringBuilder();

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

		sb = new StringBuilder();
		findAllSequences(nums, 0);

		System.out.println(sb);
	}

	private static void findAllSequences(int[] nums, int curCount) {
		if (curCount >= m) {
			for (int i = 0; i < m; i++) {
				sb.append(selectedNums[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		int prev = -1;
		for (int i = 0; i < n; i++) {
			if (selected[i]) continue;

			int cur = nums[i];

			// 이전에 사용한 값이면 패스
			if (cur == prev) continue;

			// 바로 이전 값보다 작으면 패스
			if (curCount != 0 && cur < selectedNums[curCount-1]) continue;

			selected[i] = true;
			selectedNums[curCount] = cur;
			prev = cur;
			findAllSequences(nums, curCount + 1);
			selected[i] = false;
		}
	}
}