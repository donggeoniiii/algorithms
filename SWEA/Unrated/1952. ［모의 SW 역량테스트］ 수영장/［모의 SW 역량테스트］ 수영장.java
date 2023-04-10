// 수영장(DP)

import java.util.Scanner;

public class Solution {
	
	// 이용권 가격
	static int daily, monthly, quarter, annual;
	
	// 월별 이용계획
	static int[] month;
	
	// 최소비용
	static int minCost;
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 이용권 가격
			daily = input.nextInt();
			monthly = input.nextInt();
			quarter = input.nextInt();
			annual = input.nextInt();
			
			// 12개월 이용 계획
			month = new int[13];
			
			// n월까지 금액합
			int[] totalCost = new int[13];
			
			month[0] = 0;
			for (int idx = 1; idx <= 12; idx++) 
				month[idx] = input.nextInt(); 
			
			// 최솟값은 1년치로 초기화(갱신 안되면 얘가 최소)
			minCost = annual;
			
			// 1월달은 이렇게
			totalCost[0] = 0;
			totalCost[1] = totalCost[0] + Math.min(daily * month[1], monthly);
			
			// 최소비용 찾기
			for (int idx = 1; idx <= 12; idx++) {
				// n월달까지 합 = n-1월까지 합 + min(한달치, 하루치*사용일수)
				totalCost[idx] = Math.min(totalCost[idx-1] + month[idx]*daily, totalCost[idx-1] + monthly);
				
				// 만약 3월 이후라면 n월달까지 합 = 위에서 구한 최솟값과 3달전까지 합 + 3달치 금액
				if (idx >= 3) {
					totalCost[idx] = Math.min(totalCost[idx], totalCost[idx-3]+quarter);
				}
			}
			
			// 1년치와 비용 비교
			minCost = Math.min(totalCost[12], annual);
			
			// 정답 추가
			sb.append("#").append(tc).append(" ").append(minCost).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}