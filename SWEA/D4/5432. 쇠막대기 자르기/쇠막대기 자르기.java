// 쇠막대기 자르기

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// 괄호(parentheses) 문자열 입력
			String p = input.nextLine();
			// 보기 편하게 하기 위해 레이저는 직관적으로 일직선 (|)으로 표시
			p = p.replace("()", "|");
			// ( 카운트
			int lCnt = 0;
			// ) 카운트
			int rCnt = 0;
			// 쇠막대기 조각 총 개수 카운트
			int total = 0;
			
			// 레이저+쇠막대기 배열 순회
			char[] words = p.toCharArray();

			for (int i = 0; i < words.length; i++) {
				// (, ) 카운트
				if (words[i] == '(') lCnt++;
				else if (words[i] == ')') rCnt++;
				// 만약 레이저를 만나면  
				else {
					// 그 전까지 이어지고 있던 파이프의 개수 == (의 개수만큼 잘리는 막대기 조각 수 합산 
					total += lCnt;
					// 다음 계산을 위해 이 레이저를 마지막으로 닫히는 파이프의 개수 == )의 개수만큼 lCnt 감소 
					lCnt -= rCnt;
					// 파이프가 이어지는지 반영이 됐으므로 rCnt 초기화
					rCnt = 0;	
				}
				// 마지막까지 왔다면 
				if (i == words.length-1) {
					// 마지막까지 안 닫힌 파이프 개수 == rCnt 개수만큼 합산
					total += rCnt;
				}
			}
			
			// 정답 출력
			System.out.printf("#%d %d\n", tc, total);
		}
		
	}
}
