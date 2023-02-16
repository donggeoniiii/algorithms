 // 오목판정

import java.util.Scanner;

/*
 * 델타 탐색을 좌우 / 상하 / 대각선으로 +ㅡ 2번까지 이동해서 확인
 * index를 벗어나거나 중간에 돌이 아닌 지점이 있으면 제외
 */

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 델타 탐색을 이용한 오목 성립여부 확인
		int[][] rowCheck = {{-2, 0}, {-1, 0}, {0, 0}, {1, 0}, {2, 0}};
		int[][] colCheck = {{0, -2}, {0, -1}, {0, 0},  {0, 1}, {0, 2}};
		int[][] dia1Check = {{-2, -2}, {-1, -1}, {0, 0},  {1, 1}, {2, 2}};
		int[][] dia2Check = {{-2, 2}, {-1, 1}, {0, 0},  {1, -1}, {2, -2}};


		// 테스트케이스 개수
		int T = Integer.parseInt(input.nextLine());
		
		
		for (int tc = 1; tc <= T; tc++) {
			// 오목판 크기 설정
			int N = Integer.parseInt(input.nextLine());
			String[][] map = new String[N][N];
			
			// 상황 입력
			for (int r = 0; r < N; r++) {
				String[] s = input.nextLine().split("");
				for (int c = 0; c < N; c++) {
					map[r][c] = s[c];
				}
			}

			// 오목이 되는지 확인하는 변수
			boolean success = false;
			
			// 오목여부 확인하기 위한 map 순회
			for (int r = 0; r < N; r++) {
				
				for (int c = 0; c < N; c++) {
					
					// 가로로 오목이 되는지 확인
					int cnt1 = 0;
					for (int delta = 0; delta < 5; delta++) {
						int nr = r + rowCheck[delta][0];
						int nc = c + rowCheck[delta][1];
						// 만약 index를 벗어나면 해당 지점을 기준으로는 확인해볼 필요 없음
						if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
						else {
							if (map[nr][nc].equals("o")) cnt1++;
						}
					}
					// 오목이 되면 성공했으므로 더이상 확인해볼 필요 없음
					if (cnt1 == 5) {
						success = true;
						break;
					}
					
					// 세로로 오목이 되는지 확인
					int cnt2 = 0;
					for (int delta = 0; delta < 5; delta++) {
						int nr = r + colCheck[delta][0];
						int nc = c + colCheck[delta][1];
						// 만약 index를 벗어나면 해당 지점을 기준으로는 확인해볼 필요 없음
						if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
						else {
							if (map[nr][nc].equals("o")) cnt2++;
						}
					}
					// 오목이 되면 성공
					if (cnt2 == 5) {
						success = true;
					}
					
					// 대각선1로 오목이 되는지 확인
					int cnt3 = 0;
					for (int delta = 0; delta < 5; delta++) {
						int nr = r + dia1Check[delta][0];
						int nc = c + dia1Check[delta][1];
						// 만약 index를 벗어나면 해당 지점을 기준으로는 확인해볼 필요 없음
						if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
						else {
							if (map[nr][nc].equals("o")) cnt3++;
						}
					}
					// 오목이 되면 성공
					if (cnt3 == 5) {
						success = true;
					}
					
					// 대각선2로 오목이 되는지 확인
					int cnt4 = 0;
					for (int delta = 0; delta < 5; delta++) {
						int nr = r + dia2Check[delta][0];
						int nc = c + dia2Check[delta][1];
						// 만약 index를 벗어나면 해당 지점을 기준으로는 확인해볼 필요 없음
						if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
						else {
							if (map[nr][nc].equals("o")) cnt4++;
						}
					}
					// 오목이 되면 성공
					if (cnt4 == 5) {
						success = true;
					}
				}
			}
			
			if (success == true) System.out.printf("#%d YES\n", tc);
			else System.out.printf("#%d NO\n", tc);
			
		}
		
	}
}