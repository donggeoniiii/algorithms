// 부분문자열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 실패함수
	private static int[] f;

	// 실패함수 채우기
	private static void failure(String s) {
		f = new int[s.length()];

		int j = 0;

		for (int i = 1; i < s.length(); i++) {

			while (j > 0 && s.charAt(i) != s.charAt(j)) {
				j = f[j - 1];
			}

			if (s.charAt(i) == s.charAt(j)) {
				f[i] = ++j;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String p = br.readLine();

		failure(p);

		int j = 0;

		for (int i = 0; i < s.length(); i++) {

			while (j > 0 && s.charAt(i) != p.charAt(j)) {
				j = f[j - 1];
			}

			if (s.charAt(i) == p.charAt(j)) {
				j++;
			}

			if (j == p.length()) {
				System.out.println(1);
				return;
			}
		}

		System.out.println(0);
	}
}