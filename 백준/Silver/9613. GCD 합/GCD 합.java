// G.C.D 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());

			int[] num = new int[n];
			for (int idx = 0; idx < n; idx++) {
				num[idx] = Integer.parseInt(st.nextToken());
			}

			long gcdSum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = i+1; j < n; j++) {

					gcdSum += gcd(num[i], num[j]);
				}
			}
			sb.append(gcdSum).append("\n");
		}
		System.out.println(sb);
	}

	private static long gcd(int a, int b) {
		if (b == 0) return a;

		return gcd(b, a % b);
	}
}