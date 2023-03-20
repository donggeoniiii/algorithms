import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 마지막 비트 N개
			int N = input.nextInt();
			
			// M (10진수)
			int M = input.nextInt();
			
			/*
			 * m의 이진수 표현의 마지막 N비트가 다 1인지 보고싶으면: M을 2^N으로 나눈 나머지만 가지고 검사 
			 * N자리가 모두 1인 수 == 2^N-1과 비교했을 때 같은 값이면 ON
			 */
			
			// m의 이진수 표현의 마지막 N자리만 가져오기
			M = (int) (M % Math.pow(2, N));
			
			// 비교 결과에 따라 정답 출력
			if (M == (int) Math.pow(2, N)-1) sb.append("ON").append("\n");
			else sb.append("OFF").append("\n");
			
		}
		System.out.println(sb.toString());
	}
}