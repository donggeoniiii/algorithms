// 잃어버린 괄호

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받은 문자열 연산기호 기준으로 나누기
		String input = br.readLine();

		// 숫자 배열
		String[] nums = input.split("[-+]");

		// 이번엔 연산자까지 같이 저장
		Stack<String> stack = new Stack<>();

		stack.push(nums[0]);

		int numIdx = 1;
		for (char idx : input.toCharArray()) {
			// 기호를 만나면 숫자 더해줘야됨
			if (idx == '-' || idx == '+') {
				// 기호 스택에 추가
				stack.push(String.valueOf(idx));
				// 숫자 더해주기
				stack.push(nums[numIdx++]);
			}
		}

		// 연산자 하나씩 보면서 확인하기
		int result = 0;
		int curSum = 0;
		while (!stack.isEmpty()) {

			// 이번에 오는 문자열
			String cur = stack.pop();

			// 문자열이 -면
			if (cur.equals("-")) {

				// 그전까지 합산한 값만큼 빼기
				result -= curSum;

				// 합 초기화
				curSum = 0;
			}
			// 문자열이 +면
			else if (cur.equals("+")) {
				// 다음에 나오는 숫자 더하기
				curSum += Integer.parseInt(stack.pop());
			}
			// 숫자면 더하기
			else {
				curSum += Integer.parseInt(cur);
			}

			// 만약 마지막이면 더하기
			if (stack.isEmpty()) {
				result += curSum;
			}
		}

		// 정답 출력
		System.out.println(result);
	}
}