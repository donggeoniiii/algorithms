// 장훈이의 높은 선반

import java.util.Scanner;

public class Solution {
	// 유효한 탑의 최소값
	static int min;
	
	// 점원의 수
	static int N;
	
	// 기준이 되는 선반의 높이
	static int B;
	
	// 점원의 키 배열
	static int[] height; 
	
	// 고려했는지 확인하는 배열
	static boolean[] checked;
	
	static void powerset1(int cnt, int sum) {
		// pruning: 만약 합이 이미 현재 최소값을 넘어섰으면 해당경우 스킵
		if (sum > min) return;
		
		// base case: 모든 점원을 확인했다면 종료
		if (cnt == N) {
			// 만약 합이 B 이상이면서 최소값보다 낮으면 갱신
			if (sum >= B && sum < min)
				min = sum;
			return;
		}

		powerset1(cnt + 1, sum + height[cnt]); // 해당 점원 추가하는 경우
		powerset1(cnt + 1, sum); // 해당 점원 추가 안하는 경우

	}


	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb;
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			// 점원의 수
			N = input.nextInt();
			
			// 선반의 높이
			B = input.nextInt();
			
			// 배열 크기 설정
			height = new int[N];
			checked = new boolean[N];
			
			// 키 입력
			for (int idx = 0; idx < N; idx++) {
				height[idx] = input.nextInt(); 
			}
			
			// 최소값 초기화
			min = Integer.MAX_VALUE;
			
			// 백트래킹 on
			powerset1(0, 0);
			
			// 정답 추가
			sb.append(min - B);
			
			// 출력
			System.out.println(sb.toString());
		}
		
	}
}