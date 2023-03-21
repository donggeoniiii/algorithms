// 햄버거 다이어트

import java.util.Scanner;

public class Solution{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 재료의 수 N, 제한 칼로리 L
			int N = input.nextInt();
			int L = input.nextInt();
			
			// 민기의 맛에 대한 점수와 칼로리를 담는 배열
			int[][] tasteNcal = new int[N][2];
			
			// 담기, idx 0: 선호점수, idx 1: 칼로리
			for (int i = 0; i < N; i++) {
				tasteNcal[i][0] = input.nextInt();
				tasteNcal[i][1] = input.nextInt();
			}
			
			// 점수의 최댓값
			int maxGrade = Integer.MIN_VALUE;
			
			
			// 부분집합의 개수: 2^N == 1<<n
			for (int pset = 0; pset < (1<<N); pset++) {

				// 음식의 점수, 칼로리를 합산할 변수
				int grade = 0;
				int cal = 0;
				
				// 각 부분집합 별로 해당 음식이 포함되는지 확인
				for (int food = 0; food < N; food++) {
					
					// 만약 포함된다면 해당 음식의 점수, 칼로리 합산
					if ((pset & 1<<food) > 0) {
						grade += tasteNcal[food][0];
						cal += tasteNcal[food][1];
					}
				}
				
				// 만약 칼로리 합이 제한 칼로리를 넘어가면 해당 경우 스킵
				if (cal > L) continue;
				
				// 최댓값 갱신여부 확인
				if (grade > maxGrade) maxGrade = grade;
			}
			
			// 정답 추가
			sb.append(maxGrade).append("\n");
			
		}
		
		// 출력
		System.out.println(sb.toString());
		
	}
}
