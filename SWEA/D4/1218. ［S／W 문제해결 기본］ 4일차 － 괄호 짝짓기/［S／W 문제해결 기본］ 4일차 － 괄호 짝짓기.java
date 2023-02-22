// 괄호 짝짓기

import java.util.Scanner;
import java.util.Stack;

/*
 * 스택을 사용해서 여는 괄호면 push, 
 * 닫는 괄호면 top을 보고 같은 종류의 괄호면 pop
 * top과 괄호 종류가 일치하지 않으면 break하고 0 처리
 */

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Stack<Character> stack;
		
		for (int tc=1; tc<=10; tc++) {
			
			// 4종류의 괄호가 모두 닫히는지 세는 카운트
			int[] pSum = new int[4];
			
			// 유효하지 않은 문자열을 체크하는 변수
			int correct = 1;
			
			// 괄호를 담을 stack
			stack = new Stack<>();
			
			// 테스트케이스별 길이
			int L = Integer.parseInt(input.nextLine());
			
			
			// String 문자열을 charArray로 바꿔서 순회
			for (char p : input.nextLine().toCharArray()) {
				
				// 만약 여는 괄호면 push
				if (p == '(' || p == '[' || p == '{' || p == '<') stack.push(p);
				
				// 닫는 괄호면 맞는 괄호일 때 pop, 아니면 break
				else if (p == ')' && stack.peek() == '(') stack.pop();
				else if (p == ']' && stack.peek() == '[') stack.pop();
				else if (p == '}' && stack.peek() == '{') stack.pop();
				else if (p == '>' && stack.peek() == '<') stack.pop();
				else {
					correct = 0;
					break;
				}
			}
			
			// 정답 출력
			System.out.printf("#%d %d\n", tc, correct);
			
		}
		
	}
}