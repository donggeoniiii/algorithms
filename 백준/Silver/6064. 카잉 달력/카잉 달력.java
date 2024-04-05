// 카잉 달력

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t; i++) {
			// M, N, x, y
			StringTokenizer st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int answer = -1;
			for (int year = x; year <= m * n; year += m) {
				int cy = year % n;

				if (cy == 0) cy = n;

				if (cy == y) {
					answer = year;
					break;
				}
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb);
	}
}