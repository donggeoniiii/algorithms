// 수 정렬하기 5

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(a);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(a[i]).append("\n");
		}
		System.out.println(sb);
	}
}