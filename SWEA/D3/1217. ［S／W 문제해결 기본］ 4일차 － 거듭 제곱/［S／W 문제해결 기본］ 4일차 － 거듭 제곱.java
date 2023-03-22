// 거듭제곱

import java.util.Scanner;

public class Solution {
	
	// 재귀, 분할정복을 이용한 거듭제곱 구하기 알고리즘
	public static int pow(int C, int N) {
		// base case: 1이면 정답 반환
		if(N == 1) return C;
		
		//재귀조건
		//1. 짝수일때: N제곱은 N/2제곱 2개를 곱한것과 같으므로
		if (N % 2 == 0) {
			int num = pow(C, N/2);
			return num * num;
		}
		
		//2. 홀수일때: N제곱은 (N-1)/2 제곱 2개에 피제곱수를 한번 더 곱한 것과 같으므로 
		else { // (N % 2 != 0)
			int num = pow(C, (N-1)/2);
			return num * num * C;
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		// 테스트케이스 10개
		for (int tc = 1; tc <= 10; tc++) {
			int testcase = input.nextInt();
			sb.append("#").append(testcase).append(" ");
			// 테스트케이스별 피제곱수와 제곱수
			int C = input.nextInt();
			int N = input.nextInt();
			
			// 정답 입력
			sb.append(pow(C, N)).append("\n");
				
		}
		// 정답 출력
		System.out.println(sb.toString());
	}
	
}