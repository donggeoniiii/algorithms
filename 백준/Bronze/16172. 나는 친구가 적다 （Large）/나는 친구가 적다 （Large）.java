// 나는 친구가 적다(Large)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String k = br.readLine();

		s = getAlphabets(s);

		// KMP 알고리즘을 위한 실패함수
		int[] failure = new int[k.length()];

		int j = 0;
		for (int i = 1; i < k.length(); i++) {
			while (j > 0 && k.charAt(i) != k.charAt(j)) {
				j = failure[j-1];
			}

			if (k.charAt(i) == k.charAt(j)) {
				failure[i] = ++j;
			}
		}


		// KMP 알고리즘으로 찾기
		j = 0;
		for (int i = 0; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != k.charAt(j)) {
				j = failure[j-1];
			}

			if (s.charAt(i) == k.charAt(j)) {
				j++;
			}

			if (j == k.length()) {
				System.out.println(1);
				return;
			}
		}

		System.out.println(0);
	}

	// 숫자 삭제
	private static String getAlphabets(String s) {
		StringBuilder sb = new StringBuilder();

		for (int idx = 0; idx < s.length(); idx++) {
			if (s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
				continue;
			}
			sb.append(s.charAt(idx));
		}

		return sb.toString();
	}
}