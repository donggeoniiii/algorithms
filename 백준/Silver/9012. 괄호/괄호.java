// 괄호

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	// vps 검사
	static boolean isValidParenthesisString(String input) throws IOException {
		// 검사할 stack
		Stack<Character> stack = new Stack<>();

		// 하나씩 넣기
		int idx = 0;
		while (idx < input.length()) {

			// 다음에 입력할 괄호
			char next = input.charAt(idx);

			// top과 비교해서 없앨 수 있으면 없애기
			if (!stack.isEmpty() && stack.peek() == '(' && next == ')')
				stack.pop();
				// 아니면 그냥 추가
			else {
				// 만약 next가 ) 인데 stack이 비어있으면
				if (!stack.isEmpty() && next == ')') {
					// 절대 vps가 될 수 없으므로 return false
					return false;
				}
				// 다른 경우는 그냥 추가하면 됨
				stack.push(next);
			}

			// 다음으로 이동
			idx++;
		}

		// 끝났는데 top에 남은게 있으면 vps가 아니니까 return false
		if (!stack.isEmpty())
			return false;

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테케 개수
		int T = Integer.parseInt(br.readLine());

		// 테케별로 입력
		for (int i = 0; i < T; i++) {
			String input = br.readLine();

			// vps인지 테스트하고 결과
			boolean isValid = isValidParenthesisString(input);

			// 결과에 따라 출력형태에 저장
			sb.append(isValid ? "YES" : "NO").append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}