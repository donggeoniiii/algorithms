import java.util.Scanner;

public class Solution {
	// 식재료의 수
	static int N;
	
	// 시너지 배열
	static int[][] synergy;
	
	// 음식 A에 들어가는 식재료, B에 들어가는 식재료
	static int[] aselect;
	static int[] bselect;
	
	// N개의 식재료룰 반반으로 쪼개는 모든 조합 순회하며 찾은 시너지 차이의 최소값 
	static int minGap;
	
	
	// 음식 N개(==food) 중 N/2개 (==count)를 고르는 메소드
	static void combination(int food, int count) {
		
		// 다 골랐으면 최소값을 계산해서 반환하고 종료
		if (count == N/2) {
			// 음식 a의 시너지 총합, 음식 b의 시너지 총합
			int aSum = 0;
			int bSum = 0;
			
			// b에 들어갈 애들 채우기
			int bdx = 0;
			
			// 이미 선택된 애들 체크
			boolean[] selected = new boolean[N];
			
			// 음식별로 시너지 합산
			for (int i : aselect) {
				selected[i] = true; 
				for (int j : aselect) {
					aSum += synergy[i][j];
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N/2; j++) {
					if (!selected[i]) {
						bselect[bdx++] = i;
						selected[i] = true;	 
					}
				}
			}

			
			for (int i : bselect) {
				for (int j : bselect) {
					bSum += synergy[i][j];
				}
			}
			
			// 만약 음식 맛 차이(==시너지 총합 차)의 최소값이 바뀌었으면 갱신
			if (Math.abs(aSum-bSum) < minGap) minGap = Math.abs(aSum-bSum);
			return;
		}
	
		// 만약 모든 경우를 살펴봤으면 종료
		if (food == N) return;
		
		// 모든 경우에 대해 조합 체크하기
		// 일단 현재 확인하고 있는 식재료 선택
		aselect[count] = food;
		
		// 해당 재료를 포함하는 경우: 카운트 +1, 다음 음식 관찰
		combination(food+1, count+1);
		
		// 해당 재료를 포함하지 않는 경우: 카운트는 그대로, 음식은 다음 거 관찰
		combination(food+1, count);
		
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 식재료의 수
			N = input.nextInt();
			
			// 식재료별 시너지를 체크할 배열
			synergy = new int[N][N];
			
			// 시너지 입력
			// i와 j가 서로 같은 경우는 0으로 주어진다.
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					synergy[r][c] = input.nextInt(); 
				}
			}
			
			// 선택창 초기화
			aselect = new int[N/2];
			bselect = new int[N/2];
			
			// 최소값 초기화
			minGap = Integer.MAX_VALUE;
			
			// 모든 조합 체크
			combination(0, 0);
	
			// 최소값 추가
			sb.append(minGap).append("\n");
			
		}
		
		// 출력
		System.out.println(sb.toString());
	}
}