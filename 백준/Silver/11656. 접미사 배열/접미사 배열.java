// 접미사 배열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 글자 입력
		String input = br.readLine();

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			stack.push(input.charAt(i));
		}

		// 접미사 배열
		String[] suffix = new String[input.length()];

		// 입력
		for (int i = input.length() - 1; i >= 0; i--) {
			suffix[i] = input.substring(i);
		}

		// 정렬
		Arrays.sort(suffix);

		// 출력
		StringBuilder sb = new StringBuilder();
		for (String s : suffix) {
			sb.append(s).append("\n");
		}
		System.out.println(sb);
	}
}