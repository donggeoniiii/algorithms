 // 의석이의 세로로 말해요

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb;
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// 입력은 다섯줄, 최대 15글자
			String[][] words = new String[5][15];

			// 행 기준으로 라인별 문자 입력
			for (int r = 0; r < 5; r++) {
				String[] line = input.nextLine().split("");
				// 열 기준으로 한 단어씩 입력
				for (int c = 0; c < line.length; c++) {
					words[r][c] = line[c];
				}
			}
			
			// stringbuilder를 이용해 정답 출력
			sb = new StringBuilder();
			// 열 기준으로 먼저 순회하면서 차례대로 append
			for (int c = 0; c < 15; c++) {
				for (int r = 0; r < 5; r++) {
					if (words[r][c] != null) sb.append(words[r][c]);
				}
			}
			
			// 정답 출력
			System.out.printf("#%d %s\n", tc, sb.toString());
		}
		
	}
	
	
}
