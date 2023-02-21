// OX퀴즈

import java.util.Scanner;

/*
 * O는 굳이 기억할 필요 없음!
 * 그냥 숫자를 1씩 올려서 더하다가 X를 만나면 초기화하자.
 */

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 테스트케이스 개수
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			// OX퀴즈 결과 입력
			String ox = input.nextLine();
			
			// 점수를 매기기 위한 변수
			int points = 1;
			
			// 점수의 총합을 기록하는 변수
			int total = 0;
			
			// OX퀴즈 점수를 매기기 위해 한 문제씩 확인
			for (char c : ox.toCharArray()) {
				
				// 만약 틀렸다면 점수 1로 초기화
				if (c == 'X') points = 1;
				// 맞췄다면 연속으로 맞춘 문제 수만큼 점수 추가
				else total += points++;
				
			}
			
			// 정답 출력
			System.out.println(total);
			
		}
		
	}
}