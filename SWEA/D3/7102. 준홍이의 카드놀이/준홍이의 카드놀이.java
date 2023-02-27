// 준홍이의 카드놀이

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {

			// 두 카드 덱의 총 카드 수 
			int N = input.nextInt();
			int M = input.nextInt();
			
			// N, M 중 작은수 +1 -> 가장 확률이 높은 수
			int MPN = Math.min(N, M) + 1; // most probable number
			
			// |N-M|+1 -> 가장 확률이 높은 숫자들의 수
			int pNums = Math.abs(N-M) + 1; // probable numbers
 			
			// 테스트케이스별로 가장 확률 높은 수들 중 낮은 수부터 오름차순 출력
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < pNums; i++) sb.append(MPN+i).append(" ");
			sb.append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
	}
}
