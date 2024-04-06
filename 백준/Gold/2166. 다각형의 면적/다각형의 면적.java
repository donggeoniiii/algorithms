// 다각형의 면적

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// n각형
		int n = Integer.parseInt(br.readLine());

		// n각형의 좌표
		long[][] loc = new long[n+1][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			loc[i][0] = Long.parseLong(st.nextToken());
			loc[i][1] = Long.parseLong(st.nextToken());
		}
		loc[n] = loc[0];

		// 다각형을 삼각형으로 나눠서 합산하기(신발끈 공식)
		long d1 = 0;
		long d2 = 0;
		for (int i = 0; i + 1 <= n; i++) {
			d1 += loc[i][0] * loc[i+1][1];
			d2 += loc[i][1] * loc[i+1][0];
		}

		System.out.printf("%.1f", Math.abs(d1 - d2) / 2D);
	}
}