// 회문

import java.util.Scanner;

public class Solution {
	
	// 문자판
	static char[][] chars = new char[8][8];
	
	// 회문 개수 체크하는 메소드
	static int palCheck(int length) {
		// 회문개수 카운트
		int palCnt = 0;
		
		// 행 기준 탐색
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8-length+1; c++) { // 문자판 크기 - 회문 길이+1 = 한 행에서 검사할 문자열 개수
				// 탐색 결과 회문인지 알려주는 메소드
				boolean isPal = true;
				
				// 문자열 길이 절반만큼 돌면서 양끝에 있는 단어부터 비교 
				for (int idx = 0; idx < length/2; idx++) {
					
					// 만약 서로 다른 경우가 나온다면 회문이 아님
					if (chars[r][c+idx] != chars[r][c+(length-1-idx)]) isPal = false; 
				}
				
				// 탐색결과 회문이면 카운트 +1
				if (isPal) palCnt++;
			}
		}
		
		// 열 기준 탐색
		for (int c = 0; c < 8; c++) {
			for (int r = 0; r < 8-length+1; r++) { // 문자판 크기 - 회문 길이+1 = 한 열에서 검사할 문자열 개수
				// 탐색 결과 회문인지 알려주는 메소드
				boolean isPal = true;
				
				// 문자열 길이 절반만큼 돌면서 양끝에 있는 단어부터 비교 
				for (int idx = 0; idx < 0+length/2; idx++) {
					
					// 만약 서로 다른 경우가 나온다면 회문이 아님
					if (chars[r+idx][c] != chars[r+(length-1-idx)][c]) isPal = false;
				}
				
				// 탐색결과 회문이면 카운트 +1
				if (isPal) palCnt++;
			}
		}
		
		return palCnt;
	}
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 10개
		for (int tc = 1; tc <= 10; tc++) {
			
			// 회문의 길이
			int L = Integer.parseInt(input.nextLine());
			
			// 문자판에 문자열 입력
			for (int r = 0; r < 8; r++) {
				String s = input.nextLine();
				for (int c = 0; c < 8; c++) {
					chars[r][c] = s.charAt(c);
				}
			}
			
			
			// 문자열을 행 / 열 기준으로 순회하면서 회문 찾기
			int answer = palCheck(L);
			
			// 정답 추가
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
	}	
}