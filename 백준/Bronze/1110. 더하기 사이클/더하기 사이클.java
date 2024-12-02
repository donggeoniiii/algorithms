// 더하기 사이클

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String n = br.readLine();
		int original = Integer.parseInt(n);

		int answer = 0;
		while (true) {
			// 주어진 숫자가 한자리면 0n으로 포맷 변경
			n = changeToTwoDigitFormat(n);

			int digit1 = n.charAt(0) - '0';
			int digit2 = n.charAt(1) - '0';

			String sum = changeToTwoDigitFormat(String.valueOf(digit1 + digit2));

			String next = n.charAt(1) - '0' + String.valueOf(sum.charAt(1) - '0');

			answer++;

			if (original == Integer.parseInt(next)) {
				System.out.println(answer);
				return;
			}

			n = next;
		}
	}

	static String changeToTwoDigitFormat(String s) {
		if (s.length() < 2)	return "0" + s;

		return s;
	}
}