// 괄호의 값

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	// 검사를 위한 stack
	static Stack<Character> stack;

	static int checkStringTotalScore(String input) {
		// 검사에 쓸 스택
		stack = new Stack<>();

		// 반환할 전체 합계
		int totalScore = 0;

		// 결과값
		int value = 1;

		// 한칸씩 보면서 경우 구분
		for (int i = 0; i < input.length(); i++) {

			// 넣을 값이 여는 괄호이면
			if (input.charAt(i) == '(') {
				// 스택에 넣고 괄호 종류에 맞춰 곱하기 (언젠가 스택이 닫히면 빠짐)
				stack.push(input.charAt(i));
				value *= 2;
			} else if (input.charAt(i) == '[') {
				stack.push(input.charAt(i));
				value *= 3;
			}
			// 넣을 값이 닫는 괄호면
			else if (input.charAt(i) == ')') {
				// 근데 스택에 쌓인게 없거나 여는 괄호인데 괄호 모양이 안맞으면 fail
				if (stack.isEmpty() || stack.peek() != '(') {
					return 0;
				}
				// 여는 괄호인데 모양이 맞으면 이제 곱한 값만큼 더해줌
				else if (input.charAt(i - 1) == '(') {
					totalScore += value;
				}
				// 짝이 맞으면 더 볼 필요 없으니까 제외
				stack.pop();
				// 닫혔으니까 닫힌 만큼은 제외
				value /= 2;
			} else if (input.charAt(i) == ']') {
				if (stack.isEmpty() || stack.peek() != '[') {
					return 0;
				} else if (input.charAt(i - 1) == '[') {
					totalScore += value;
				}
				stack.pop();
				value /= 3;
			}
		}

		return totalScore;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 검사할 괄호열
		String input = br.readLine();

		// 계산된 점수
		int score = checkStringTotalScore(input);

		// stack에 뭔가 남은 채로 끝나면 유효하지 않으니까
		if (!stack.isEmpty())
			System.out.println(0);
		else
			System.out.println(score);
	}
}