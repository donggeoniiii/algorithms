// 규영이와 인영이의 카드게임

import java.util.Scanner;

public class Solution {
	// 규영이의 카드 배열
	static int[] p1; 
	
	// 인영이의 카드 배열
	static int[] p2;
	
	// 이미 낸 카드 표시 배열
	static boolean[] drawed;
	
	// 규영이가 얻은 점수 총합
	static int p1Points;
	
	// 이긴 횟수 카운트
	static int winCnt;
	
	// 백트래킹 알고리즘
	static void backtrack(int k) {
		
		// base case : 9번의 턴이 다 끝났다면
		if (k == 9) {
			
			// 규영이가 얻은 점수 총합이 86 이상이면(== 절반 이상)
			if (p1Points >= 86) {
				
				// 승리 카운트 +1
				winCnt++;
			}
		}
		
		// recursion case
		// 규영이의 카드 뭉치에서 꺼내는 모든 경우 탐색
		for (int num = 0; num < 9; num++) {
			
			// 이미 낸 카드면 스킵
			if (drawed[num]) continue;
			
			// 뽑은 카드는 낸 표시
			drawed[num] = true;
			
			// 규영이가 이겼으면 포인트 갱신
			if (p1[k] >= p2[num]) p1Points += p1[k] + p2[num];
			
			// 재귀 on
			backtrack(k+1);
			
			// 모든 경우에 대해 탐색했으니까 다시 안 낸 표시
			drawed[num] = false;
			
			// 해당 카드를 내서 얻은 점수가 있는 경우 되돌리기
			if (p1[k]>= p2[num]) p1Points -= p1[k] + p2[num];
		}
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 배열 크기 설정
			p1 = new int[9];
			p2 = new int[9];
			drawed = new boolean[19]; // 18 + 1;
			
			// 규영이의 카드 입력
			for (int idx = 0; idx < 9; idx++) {
				p1[idx] = input.nextInt(); 
				drawed[p1[idx]] = true; // 규영이가 뽑아간 거 표시
			}
			
			// 인영이의 카드 입력
			int idx = 0; 
			for (int i = 1; i <= 18; i++) {
				
				// 규영이가 뽑아간거면 
				if (drawed[i]) {
					
					// 나중에 게임 진행을 위해 뽑은 표시 지우고
					drawed[i] = false;
					
					// 넘김
					continue;
				}
				// 규영이가 뽑은게 아니면 인영이꺼니까 추가
				p2[idx++] = i;
			}
			
			// 승리카운트 초기화
			winCnt = 0;			
			
			// 백트래킹 on
			backtrack(0);
			
			// 결과 추가
			sb.append(winCnt).append(" ").append(362880-winCnt).append("\n");
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
	}
}
