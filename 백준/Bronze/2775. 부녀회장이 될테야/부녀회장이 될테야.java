// 부녀회장이 될테야

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < t; tc++) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());

			int[][] apartment = new int[k+1][n+1];
			for (int i = 1; i <= n; i++) {
				apartment[0][i] = i;
			}

			for (int i = 1; i <= k; i++) {
				for (int j = 1; j <= n; j++) {
					for (int l = 1; l <= j; l++) {
						apartment[i][j] += apartment[i-1][l];
					}
				}
			}
			sb.append(apartment[k][n]).append("\n");
		}
		System.out.println(sb);
	}
}