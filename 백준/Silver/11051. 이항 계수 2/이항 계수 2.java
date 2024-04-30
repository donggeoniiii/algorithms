// 이항 계수 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// dp[i][j] : iCj
	private static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 페르마의 삼각형: nCk = n-1Ck-1 + n-1Ck 이용
		dp = new int[n+1][k+1];
		System.out.println(fermatTriangle(n, k));
	}

	private static int fermatTriangle(int n, int k) {
		// memoization
		if (dp[n][k] != 0) return dp[n][k];

		// nC0 = nCn = 1
		if (k == 0 || k == n) return dp[n][k] = 1;

		// 10007로 나눈 나머지 출력
		return dp[n][k] = (fermatTriangle(n-1, k-1) + fermatTriangle(n-1, k)) % 10007;
	}

}