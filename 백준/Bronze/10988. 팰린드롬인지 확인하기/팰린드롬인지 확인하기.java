// 팰린드롬인지 확인하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean isPalidrome(String input) {
		int n = input.length();
		for (int i = 0; i < n; i++) {
			// 두 값이 교차되면 회문이라고 반환하고 종료
			if (i >= input.charAt(i) - 1 - i) {
				break;
			}

			// 만약 두 값이 다르면 회문이 아니라고 반환하고 종료
			if (input.charAt(i) != input.charAt(n - 1 - i)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 알파벳 소문자로만 이루어진 입력
		String input = br.readLine();

		// 검사결과
		System.out.println((isPalidrome(input)) ? 1 : 0);
	}
}