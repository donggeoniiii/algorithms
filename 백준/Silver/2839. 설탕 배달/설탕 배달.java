// 설탕배달

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 설탕의 킬로 수
		int N = input.nextInt();
		
		// 봉지에 다 담는게 가능한지 확인하는 변수
		boolean clear = false;
		
		// 5킬로 봉지수
		int cnt5 = N/5;
		
		// 최소 봉지수: 모든 설탕이 3킬로 봉지에 담기면 최대, 계속 비교하며 갱신
		int minCnt = N/3;
		
		// 5킬로 봉지가 0개가 될 때까지 모든 경우 탐색
		while (true) {
			// 3킬로 봉지수: 5킬로로 빠진만큼 무게에서 제외한 나머지를 3으로 나눈 값
			int cnt3 = (N - 5*cnt5) / 3;
			
			// 만약 두 봉지에 담긴 양이 전체와 같으면 다 담기는 경우가 있긴 한거니까 배달 가능으로 처리
			if (5*cnt5 + 3*cnt3 == N) {
				clear = true;

				// 최소값 갱신
				if (cnt5 + cnt3 < minCnt) minCnt = cnt5 + cnt3;
			}
			
			
			
			// 카운트 감소, 다음경우와 비교 준비
			cnt5--;
			
			// 만약 카운트가 음수가 되면 종료
			if (cnt5 < 0) break;
		}

		
		// 남은게 0이 아니면 -1, 아니면 봉지수 출력
		if (clear) System.out.println(minCnt);
		else System.out.println(-1);
	}
}