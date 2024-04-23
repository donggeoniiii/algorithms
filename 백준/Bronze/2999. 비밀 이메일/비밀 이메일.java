// 비밀 이메일

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String cypher = br.readLine();

		int n = cypher.length();

		for (int r = n; r >= 1; r--) {
			if (n % r != 0) continue;

			int c = n / r;

			if (r > c) continue;

			char[][] arr = new char[c][r];

			int idx = 0;
			for (int i = 0; i < c; i++) {
				for (int j = 0; j < r; j++) {
					arr[i][j] = cypher.charAt(idx++);
				}
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					sb.append(arr[j][i]);
				}
			}

			System.out.println(sb);
			return;
		}
	}
}