// 오셀로

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 T
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			sb.append("#").append(tc).append(" ");
		
			// 한 변의 길이
			int N = input.nextInt();
			
			// 총 턴수
			int M = input.nextInt();
			
			// map 배열
			int[][] map = new int[N][N];
			
			// map의 중앙에 돌 배치하고 시작
			for (int r = N/2-1; r <= N/2; r++) {
				for (int c = N/2-1; c <= N/2; c++) {
					
					// 만약 행/열이 같으면 흰돌(2), 다르면 검은돌(1)
					if (r == c) map[r][c] = 2;
					else map[r][c] = 1;
				}
			}
			
			
			// 주변탐색을 위한 델타배열 (대각선까지!!!!)
			int[] dr = {-1,1,0,0,-1,-1,1,1};
			int[] dc = {0,0,-1,1,-1,1,-1,1};
			
			// 차례별 정보 입력
			for (int turn = 1; turn <= M; turn++) {
				
				// 놓을 돌의 좌표, 배열의 좌표 (0,0)이 게임에선 (1,1)이 되므로 -1씩
				int cr = input.nextInt() -1; // current row
				int cc = input.nextInt() -1; // current col
				
				// 돌의 색, 1이면 흑 2면 백
				int player = input.nextInt();
				
				// 일단 돌을 놓고
				map[cr][cc] = player;
				
				// 델타탐색
				for (int dt = 0; dt < 8; dt++) {
					int nr = cr + dr[dt];
					int nc = cc + dc[dt];
					
					// index를 벗어나면 스킵!!!!!!!
					if (nr >= N || nc >= N || nr < 0 || nc < 0) continue;
					
					// 만약 돌이 있고 상대돌이면 
					if (map[nr][nc] != 0 && map[nr][nc] != player) {
						
						// 내 돌을 만날 때까지
						while (map[nr][nc] != player) {
							
							// 해당 방향으로 한칸 더 탐색
							nr += dr[dt];
							nc += dc[dt];

							// index를 벗어나면 끝
							if (nr >= N || nc >= N || nr < 0 || nc < 0) break;
							
							// 돌이면 끝
							if (map[nr][nc] == 0) break;
							
							// 만약 내돌이면
							if (map[nr][nc] == player) {
								
								// 다시 원래자리로 오면서 만난 돌 다 내꺼하기
								// 헷갈리니까 종료조건 생성
								boolean terminated = false;
								
								while (!terminated) { // true
									
									// 한칸 옮기고
									nr -= dr[dt];
									nc -= dc[dt];

									// 색 바꿈
									map[nr][nc] = player;
									
									// 만약 원래자리까지 왔으면 종료
									if (nr == cr && nc == cc) terminated = true;
									
								}
								
							}
							

							
						}
					}
					
				}
				
			}
			
			// 돌 카운트
			int black = 0; // 흑돌
			int white = 0; // 백돌
			
			// 오셀로 판을 돌면서
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					
					// 흑돌 백돌 카운트
					if (map[r][c] == 1) black++;
					else if (map[r][c] == 2) white++;
					else continue; // 돌이 없으면 건뛰
				}
			}
			

			
			// 정답 추가
			sb.append(black).append(" ").append(white).append("\n");
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
	}
}