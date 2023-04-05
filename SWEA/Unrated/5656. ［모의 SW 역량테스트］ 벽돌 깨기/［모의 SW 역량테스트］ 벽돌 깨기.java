// 벽돌 깨기

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	// 벽돌맵의 크기
	static int W, H;
	
	// 벽돌 맵에 떨굴 구슬 수
	static int N;
	
	// 벽돌 격파를 위한 델타배열
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// 최댓값 저장변수
	static int minCnt;
	
	// 구슬 떨구기 알고리즘
	static void selectLine(int cnt, int[][] curMap) {
		
		// pruning: 이미 최솟값이 0이면 더 해볼 필요 없음
		if (minCnt == 0) return; 
		
		// base case: N개를 다 떨구면 종료
		if (cnt == N) {

			// 남은 갯수 세기
			int brickCnt = 0;
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (curMap[r][c] == 0) continue;
					brickCnt++;
				}
			}
			
			// 최댓값 갱신
			minCnt = Math.min(minCnt, brickCnt);
			
			return;
		}

		
		// 벽돌을 안만난 열의 개수
		int noBrick = 0;
		// recursive case: 
		for (int cc = 0; cc < W; cc++) {
			// 벽돌에 닿을 때까지 낙하
			int cr = 0; 
			while (cr < H && curMap[cr][cc] == 0)
				cr++;
			
			// 벽돌이 끝까지 내려가면 깰게 없으니까 제외
			if (cr == H) {
				noBrick++;
				
				// 벽돌을 안 만난 열의 수가 W개가 되면 바로 N번째로 이동해서 최솟값 반영
				if (noBrick == W) 
					selectLine(N, curMap);
				continue;
			}

			// 깨기 전 깨진 모습을 반영할 새로운 배열 생성
			int[][] newMap = new int[H][W];
			for (int r = 0; r < H; r++) {
				newMap[r] = Arrays.copyOf(curMap[r], W); 
			}
			
			// 맞은 벽돌이 값이 1이면 그거만 깨고 종료
			// 그 외 벽돌에 닿으면 폭파 알고리즘 on
			if (newMap[cr][cc] == 1) {
				newMap[cr][cc] = 0; 
			} else 
				bomb(cr, cc, newMap);
			
			// 다 깨졌으면 깨진부분 채우기
			for (int fc = 0; fc < W; fc++) { // fix column
				for (int fr = H-1; fr >= 0; fr--) { // fix row
					
					// 이미 깨져서 빈 부분 스킵
					if (newMap[fr][fc] == 0) continue;
					
					// 아래에서부터 떠있는 벽돌 아래로 끌어 맞추기
					int bdx = fr; // brick index;
					while (bdx < H-1 && newMap[bdx+1][fc] == 0) {
						newMap[bdx+1][fc] = newMap[bdx][fc];
						newMap[bdx][fc] = 0; 
						bdx++;
					}
				}
			}
			
			// 깨고 나서는 다음 구슬 위치 선택을 위해 이동
			selectLine(cnt+1, newMap);
		}
	}
	
	// 벽돌 깨기 알고리즘
	static void bomb(int r, int c, int[][] map) {
		
		// base case: 값이 1이면 깨고 종료
		if (map[r][c] == 1) {
			map[r][c] = 0; 
			return;
		}
		
		// recursive case: 1이 아니면 값-1의 범위까지 벽돌 파괴
		int power = map[r][c] - 1;
		map[r][c] = 0; 
		for (int range = 1; range <= power; range++) {
			for (int dt = 0; dt < 4; dt++) {
				
				// 새로운 좌표
				int nr = r + dr[dt] * range;
				int nc = c + dc[dt] * range;
				
				// index를 벗어나면 스킵
				if (nr >= H || nc >= W || nr < 0 || nc < 0) continue;
				
				// 재귀호출을 통해 연쇄적으로 벽돌깨기 on
				bomb(nr, nc, map);
			}	
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 던질 구슬 개수
			N = input.nextInt();
			
			// 벽돌 맵 크기
			W = input.nextInt();
			H = input.nextInt();
			
			// 배열 생성
			int[][] map = new int[H][W];
			
			// 입력받기
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					map[r][c] = input.nextInt();
				}
			}
			
			// 최솟값 초기화
			minCnt = Integer.MAX_VALUE;
			
			// 구슬 던지기
			selectLine(0, map);
			
			
			// 최댓값 추가
			sb.append("#").append(tc).append(" ").append(minCnt).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
		input.close();
	}

}