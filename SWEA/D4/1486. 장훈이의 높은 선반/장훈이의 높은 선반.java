import java.util.Arrays;

// 장훈이의 높은 선반

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb;
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			// 점원의 수
			int N = input.nextInt();
			
			// 선반의 높이
			int B = input.nextInt();
			
			// 점원의 키 배열
			int[] height = new int[N];
			
			// 키 입력
			for (int idx = 0; idx < N; idx++) {
				height[idx] = input.nextInt(); 
			}
	
			// 부분집합 구하기
			int[] powerset = new int [1<<N];
			
			// i: 공집합을 포함한 모든 부분집합 (1<<N개)
			for (int i = 0; i < (1<<N); i++) {
				
				// j: 각각의 부분집합에 들어갈 수 있는 원소들 (N개)
				for (int j = 0; j < N; j++) {
					
					// 해당 원소가 들어가면 그 부분집합에 원소값 추가 
					if ((i & (1<<j)) > 0) powerset[i] += height[j];  
					
				}
			}
			
			// 배열에서 기준높이 B보다 높은 가장 낮은 값 찾기
			int answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < (1<<N); i++) {
				
				// 만약 해당 탑의 높이가 B이상이면서 현재까지 가장 낮다면 갱신
				if (powerset[i] >= B && powerset[i] < answer) {
					answer = powerset[i];
				}
			}
			
			// 정답 추가
			sb.append(answer - B);
			
			// 출력
			System.out.println(sb.toString());
		}
		
	}
}