// 부분 수열의 합

import java.util.Scanner;

public class Solution {	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 주어진 자연수 개수
			int N = input.nextInt();
			
			// 만들어야 하는 수
			int K = input.nextInt();
			
			// 주어지는 자연수를 보관할 배열
			int[] nums = new int[N];
			
			// 배열에 보관
			for (int i = 0; i < nums.length; i++) {
				nums[i] = input.nextInt(); 
			}
			
			// k가 되는 경우의 수 초기화
			int kCnt = 0;
			
			// idx: 1<<N개 중 하나의 부분집합
			for (int idx = 0; idx < (1<<N); idx++) {
				// 배열을 돌면서 각 숫자를 더해 k를 만든다
				int selSum = 0;
				
				// N개의 각 숫자에 대해 해당 부분집합에 포함되는지 여부를 확인, 포함되는 경우 합산
				for (int j = 0; j < N; j++) {
					if ((idx & (1<<j)) > 0) selSum+= nums[j]; 
				}
				
				// 만약 해당 부분집합의 합이 K면 k카운트 증가
				if (selSum == K) kCnt++;
			}
			
			// 정답 입력
			sb.append(kCnt).append("\n");
			
		}
		
		// 출력
		System.out.println(sb.toString());
		
	}
}