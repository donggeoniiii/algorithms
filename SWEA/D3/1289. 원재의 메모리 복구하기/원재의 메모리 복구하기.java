// 원재의 메모리 복구하기

import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Stack<Character> stack;
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// 메모리 입력
			String s = input.nextLine();
			
			// 변환을 몇번 해야하는지 세기 위한 스택 생성
			stack = new Stack<>();
			
			// 변환을 몇번 해야하는지 세는 카운트
			int switchCnt = 0;
			
			// 스택에 첫자리부터 넣으면서 top과 같은지 비교
			for (char c : s.toCharArray()) {
				
				// 일단 첫 자리면 push부터, 넣은 값이 1이면 카운트 1부터 시작
				if (stack.isEmpty()) {
					stack.push(c);
					if (stack.peek() == '1') switchCnt++;
				}
				
				// 첫자리가 아니면 현재 top과 넣으려는 값이 같은지 보고 카운트여부 따진 다음 push
				else {
					if (stack.peek() != c) switchCnt++;
					stack.push(c);
				}
					
			}
			
			// 정답 추가
			sb.append("#").append(tc).append(" ").append(switchCnt).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
	}
}