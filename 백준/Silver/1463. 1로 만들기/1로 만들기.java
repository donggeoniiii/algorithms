// 1로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 주어지는 수 x
		int x = Integer.parseInt(br.readLine());

		// memo[x] = 1을 x로 만드는데 걸리는 최솟값
		int[] memo = new int[x + 1];

		// 1은 자명
		memo[1] = 0;

		// 다음 값: 2로 나누어 떨어지거나 3으로 나누어 떨어질 경우 고려해야
		for (int num = 2; num <= x; num++) {
			memo[num] = memo[num - 1] + 1;
			if (num % 2 == 0) {
				memo[num] = Math.min(memo[num], memo[num / 2] + 1);
			}
			if (num % 3 == 0) {
				memo[num] = Math.min(memo[num], memo[num / 3] + 1);
			}
		}

		// 정답 출력
		System.out.println(memo[x]);
	}
}