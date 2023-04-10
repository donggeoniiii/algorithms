// 수영장(re)

import java.util.Scanner;

public class Solution {
	
	// 이용권 가격
	static int daily, monthly, quarter, annual;
	
	// 월별 이용계획
	static int[] month;
	
	// 최소비용
	static int minCost;
	
	// 백트래킹 알고리즘
	static void swimmingFee(int cnt, int cost) {
		
		// pruning: 현재까지 비용합보다 최소비용이 더 작으면 더 고려할 필요 없음
		if (minCost < cost)
			return;
		
		// base case: 12월까지 다 고려하고 나면 종료
		if (cnt >= 12) {
			
			// 최솟값과 비교, 갱신
			minCost = Math.min(minCost, cost);
			
			return;
		}
		
		
		// recursive case: 연간 금액을 제외한 3개 중 선택
		for (int i = 0; i < 3; i++) {
			switch (i) {
			case 0: // 1일치
				swimmingFee(cnt+1, cost + month[cnt]*daily);
				break;
			case 1: // 1달치
				swimmingFee(cnt+1, cost + monthly);
				break;
			case 2: // 3달치
				swimmingFee(cnt+3, cost + quarter);
				break;
			}
		}
		
	}
	
	
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
			month = new int[12];
			for (int idx = 0; idx < 12; idx++) 
				month[idx] = input.nextInt(); 
			
			// 최솟값은 1년치로 초기화(갱신 안되면 얘가 최소)
			minCost = annual;
			
			// 최소비용 찾기
			swimmingFee(0, 0);
			
			// 정답 추가
			sb.append("#").append(tc).append(" ").append(minCost).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}