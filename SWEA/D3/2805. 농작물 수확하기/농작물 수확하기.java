 // 농작물 수확하기

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 테스트케이스별 농장 크기
			int N = Integer.parseInt(input.nextLine());
			
			// 농장 생성
			int[][] farm = new int[N][N];
			
			// 문자열 입력
			for (int r = 0; r < N; r++) {
				String s = input.nextLine();
				for (int c = 0; c < N; c++) {
					farm[r][c] = s.charAt(c) - '0';
				}
			}
			
			
			// 농장별 수확하는 부분의 합
			int sum = 0;
			
			// 순회하면서 해당부분 농작물 데이터 합산
			for (int r = 0; r < N; r++) {
				// 가운데 행(N/2)을 기준으로 가운데 행과의 차이만큼 양옆에서 제외하고 뽑으면 되니까
				int skip = Math.abs(N/2 -r);
				for (int c = skip; c < N-skip; c++) {
					sum += farm[r][c];
				}
			}
			
			// 정답 추가
			sb.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
	}
}
