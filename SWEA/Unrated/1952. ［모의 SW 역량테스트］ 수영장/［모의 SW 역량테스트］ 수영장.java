import java.util.Scanner;

public class Solution {
	
	/** 가격표 배열, 1일 / 1달 / 3달치
	 * 
	 */
	static int[] price;
	
	/**
	 * 1년 이용권
	 */
	static int annualPrice;
	
	/**
	 * 월별 수영장 이용시간
	 */
	static int[] time;
	
	/** 최솟값
	 * 
	 */
	static int minCost;
	
	// 탐색 알고리즘
	private static void swimming(int month, int total) {
		
		// pruning: 이미 가격 합이 최소값을 넘어서면 쳐내
		if (total > minCost) return;
		
		// base case: 1년치를 다 보고 나면
		if (month >= 12) { // 11월, 12월에도 3개월치를 긁을 수 있으니까 변수가 12를 넘어설 수 있으므로
 			
			// 1년치와 가격 비교, 1년 이용권이 더 싸면 쳐내
			if (total > annualPrice) return;
			
			// 현재 최소값과 비교해서 더 작으면 갱신
			if (total < minCost) minCost = total;
			
			return;
		}
		
		// recursive case
		// 매 달마다 어떻게 결제할지 결정
		for (int payment = 0; payment < 3; payment++) {
			
			switch (payment) { // 결제 수단에 따라 구분
			
			case 0: // 1일치로 결제
				
				// 1일치로 결제한 만큼 결제 금액 추가하고 다음달로 이동
				swimming(month+1, total + price[0]*time[month]);
				break;
				
			case 1: // 1달치로 결제
				
				// 1달치로 결제한 만큼 결제 금액 추가하고 다음달로 이동
				swimming(month+1, total + price[1]);
				break;
				
			case 2: // 3달치로 결제
				
				// 3달치로 결제한 만큼 결제 금액 추가하고 3달 후로 이동
				swimming(month+3, total + price[2]);
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 이용권 가격 입력
			price = new int[3];
			for (int i = 0; i < 3; i++) {
				price[i] = input.nextInt();
			}
			annualPrice = input.nextInt();
			
			// 올해 이용계획 입력
			time = new int[12];
			for (int i = 0; i < 12; i++) {
				time[i] = input.nextInt(); 
			}
			
			// 최소비용 초기화
			minCost = Integer.MAX_VALUE;
			
			// 최소비용 탐색
			swimming(0, 0);
			
			// 만약 최솟값이 갱신되지 않았으면 최소비용은 1년치
			if (minCost == Integer.MAX_VALUE) minCost = annualPrice;
			
			// 정답 입력
			sb.append("#").append(tc).append(" ").append(minCost).append("\n");
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
		input.close();
	}
}