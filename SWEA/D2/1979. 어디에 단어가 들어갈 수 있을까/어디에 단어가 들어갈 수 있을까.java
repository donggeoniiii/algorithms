// 어디에 단어가..?

import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(input.nextLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// 지도 입력
			int[][] map = new int[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(input.nextLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 길이가 K인 문자가 들어갈 수 있는 전체 
			int totalCnt = 0;
			
			// 행 기준 순회
			for (int r = 0; r < N; r++) {
				int cnt = 0;
				for (int c = 0; c < N; c++) {
					// 만약 입력 가능하지 않은 땅이면 현재 카운트가 K인지 확인하고 초기화
					if (map[r][c] == 0) {
						if (cnt == K) {
//							System.out.println("row: " + r +", col: " + c);
							totalCnt++;
						} 
						cnt = 0;
					}
					// 아니면 cnt +1 후 마지막 index인지 확인, 마지막 index이면 현재 카운트가 K인지 확인
					else {
						cnt++;
						if (c == N-1) {
							if (cnt == K) {
//								System.out.println("row: " + r +", col: " + c);
								totalCnt++;
							} 
						}
					}
				}
			}
			
			// 열 기준 순회
			for (int c = 0; c < N; c++) {
				int cnt = 0;
				for (int r = 0; r < N; r++) {
					// 만약 입력 가능하지 않은 땅이면 현재 카운트가 K인지 확인하고 초기화
					if (map[r][c] == 0) {
						if (cnt == K) {
//							System.out.println("row: " + r +", col: " + c);
							totalCnt++;
						} 
						cnt = 0;
					}
					// 아니면 cnt +1 후 마지막 index인지 확인, 마지막 index이면 현재 카운트가 K인지 확인
					else {
						cnt++;
						if (r == N-1) {
							if (cnt == K) {
//								System.out.println("row: " + r +", col: " + c);
								totalCnt++;
							} 
						}
					}
				}
			}
			
			// 정답 출력
			System.out.printf("#%d %d\n", tc, totalCnt);
			
		}
		
	}
	
}