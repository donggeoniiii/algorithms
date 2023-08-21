// LCS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받은 두 배열
		char[] word1 = br.readLine().toCharArray();
		char[] word2 = br.readLine().toCharArray();

		// 각 배열의 부분 길이별로 비교하기 위한 배열
		int[][] dp = new int[word1.length + 1][word2.length + 1];

		// 배열 채우기
		for (int i = 1; i < word1.length + 1; i++) {
			for (int j = 1; j < word2.length + 1; j++) {
				// 만약 이번에 비교한 두 값이 같으면
				if (word1[i - 1] == word2[j - 1]) {
					// 바로 직전 길이까지 비교해서 얻은 lcs 길이에 +1
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				// 다르면 두 문자열 중 하나의 문자열 길이를 +1해서 반영해 lcs 길이를 계산한 값 중 큰 값을 반영
				else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		// 정답 출력
		System.out.println(dp[word1.length][word2.length]);
	}
}