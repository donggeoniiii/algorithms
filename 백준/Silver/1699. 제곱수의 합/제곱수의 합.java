// 제곱수의 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] dp = new int[n+1];
		dp[1] = 1;

		// 제곱수 모음, 1은 제외
		ArrayList<Integer> squareNum = new ArrayList<>();

		if (n >= 2) {
			for (int i = 2; i <= n; i++) {
				// default : +1
				dp[i] = dp[i-1] + 1;

				// 자신보다 작은 제곱수를 통하는 경우 체크
				for (int num : squareNum) {
					dp[i] = Math.min(dp[i], dp[i - num] + 1);
				}

				// 만약 자기 자신이 제곱수인 경우 제곱수 리스트에 추가
				if (Math.sqrt(i) == (int) Math.sqrt(i)) {
					dp[i] = 1;
					squareNum.add(i);
				}
			}
		}

		System.out.println(dp[n]);
	}
}