// 최대공약수와 최소공배수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int gcd = GCD(a, b);
		int lcm = a * b / gcd;

		System.out.println(gcd);
		System.out.println(lcm);
	}

	private static int GCD(int a, int b) {
		if (b == 0) return a;

		else return GCD(b, a % b);
	}
}