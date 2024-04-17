// 배열 합치기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] a = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		int[] b = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		int adx = 0;
		int bdx = 0;
		int[] answer = new int[n+m];
		for (int i = 0; i < n+m; i++) {
			if (adx < n && bdx < m) {
				if (a[adx] <= b[bdx]) {
					answer[i] = a[adx++];
				}
				else {
					answer[i] = b[bdx++];
				}
			}
			else {
				if (adx < n) answer[i] = a[adx++];
				else answer[i] = b[bdx++];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n+m; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}