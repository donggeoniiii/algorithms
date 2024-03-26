// 좌표 정렬하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] loc = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			loc[i][0] = Integer.parseInt(st.nextToken());
			loc[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(loc, Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(loc[i][0]).append(" ").append(loc[i][1]).append("\n");
		}
		System.out.println(sb);
	}
}