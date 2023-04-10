// 햄버거 다이어트(re)

import java.util.Scanner;

public class Solution {
	// 칼로리 제한
	static int L;
	
	// 주어지는 재료의 개수
	static int N;
	
	// 재료별 점수, 칼로리 배열
	static int[] points;
	static int[] calories;
	
	// 최댓값
	static int maxTaste;
	
	// 백트래킹 알고리즘
	static void burger(int cnt, int cal, int cur) {
		
		// pruning: 칼로리 제한 넘어서면 종료
		if (cal > L) 
			return;
		
		// base case: N개의 재료 다 따져보면 종료
		if (cnt == N) {
			
			maxTaste = Math.max(maxTaste, cur);
			
			return;
		}
		
		// recursive case
		// 안 선택
		burger(cnt+1, cal, cur);
			
		// 선택
		burger(cnt+1, cal + calories[cnt], cur + points[cnt]);
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 음식 개수, 제한 칼로리
			N = input.nextInt();
			L = input.nextInt();

			// 배열 정보 입력
			points = new int[N];
			calories = new int[N];
			for (int idx = 0; idx < N; idx++) {
				points[idx] = input.nextInt();
				calories[idx] = input.nextInt(); 
			}
			
			// 제일 맛있는 조합 찾기
			maxTaste = -1;
			burger(0, 0, 0);
			
			// 정답 추가
			sb.append("#").append(tc).append(" ").append(maxTaste).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}