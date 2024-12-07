// 예산

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] state = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());

		int budgetTotal = 0;
		int budgetMax = -1;
		for (int i = 0; i < n; i++) {
			state[i] = Integer.parseInt(st.nextToken());
			budgetTotal += state[i];
			budgetMax = Math.max(budgetMax, state[i]);
		}

		int max = Integer.parseInt(br.readLine());

		// 예산 요청 총액이 국가 예산 이내면 그대로 배정
		if (budgetTotal <= max) {
			System.out.println(budgetMax);
			return;
		}

		int left = 1;
		int right = budgetMax;
		while (left < right) {
			int mid = (left + right + 1) / 2;

			if (isAfford(state, mid, max)) left = mid;
			else right = mid - 1;
		}

		System.out.println(left);
	}

	// 예산액 배정하기, 총액을 벗어나는 경우 제외
	static boolean isAfford(int[] state, int threshold, int max) {
		long total = 0;
		for (int cur : state) {
			total += Math.min(cur, threshold);
		}

		return total <= max;
	}
}