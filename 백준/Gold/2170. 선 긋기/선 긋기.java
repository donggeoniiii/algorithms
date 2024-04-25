// 선 긋기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] line = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			line[i][0] = Integer.parseInt(st.nextToken());
			line[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(line, Comparator.comparingInt((int[] o) -> o[0]).thenComparing(o -> o[1]));

		int total = 0;
		int src = line[0][0];
		int dest = line[0][1];
		for (int i = 1; i < n; i++) {
			// 라인 안에 두 점이 포함되면 갱신 x
			if (src <= line[i][0] && line[i][1] <= dest) continue;

			// 이어지지 않으면 그전까지 이어진 길이만큼 더하기
			if (line[i][0] > dest) {
				total += dest - src;

				src = line[i][0];

			}
			dest = line[i][1];
		}

		// 마지막 선 더하기
		total += dest - src;

		System.out.println(total);
	}
}