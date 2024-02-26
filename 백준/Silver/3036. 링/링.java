// 링

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 링의 개수
	static int n;

	// 링의 반지름 길이
	static int[] radius;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		radius = new int[101];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int idx = 1;
		while (st.hasMoreTokens()) {
			radius[idx++] = Integer.parseInt(st.nextToken());
		}
	}

	private static void solution() {
		// 첫 번째 링을 기준으로, 그 비율에 맞게 돌아간다
		StringBuilder sb = new StringBuilder();

		int idx = 2;
		for (int i = 0; i < n-1; i++) {
			// 최대공약수 구하기
			int lcm = gcd(radius[1], radius[idx]);

			sb.append(radius[1] / lcm).append("/").append(radius[idx++] / lcm).append("\n");
		}

		// 출력
		System.out.println(sb);
	}

	private static int gcd(int r1, int r2) {
		if (r1 % r2 == 0) {
			return r2;
		}
		return gcd(r2, r1 % r2);
	}
}