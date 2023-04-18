//스티커 붙이기

import java.util.Scanner;


public class Main {
	
	// 배열의 크기
	static int N, M;
	
	// 붙일 스티커의 개수
	static int T;
	
	// 노트북
	static int[][] laptop;
	
	// 맞나 대보기 메소드
	static boolean available(int cr, int cc, int[][] sticker) {

		// 스티커 크기만큼 붙여보기
		for (int r = cr; r < cr+ sticker.length; r++) {
			for (int c = cc; c < cc+ sticker[0].length; c++) {
				
			// 만약 index를 벗어나면
			if (r >= N || c >= M || r < 0 || c < 0)
				return false;
			
			// 만약 스티커 붙일 자리에 머가 있으면
			if (sticker[r-cr][c-cc] == 1 && laptop[r][c] == 1)
				return false;
			}
		}
		
		return true;
	}
	
	// 배열 돌리기 메소드
	static int[][] flip(int[][] curMap, int degree){
		
		// 반환할 배열
		int[][] newMap;
		
		// 돌리는 각도에 따라 맵 전환
		switch (degree) {
		case 1:
			// 90도 돌리기(오른쪽)
			
			// 일단 배열 만들고
			newMap = new int[curMap[0].length][curMap.length];
			
			// 값은 열부터, 거꾸로 넣기
			for (int r = 0; r < curMap[0].length; r++) {
				for (int c = 0; c < curMap.length; c++) {
					newMap[r][c] = curMap[(curMap.length-1)-c][r];
				} 
			}
			
			break;
		case 2:
			// 180도 돌리기

			// 일단 배열 만들고
			newMap = new int[curMap.length][curMap[0].length];
			
			// 값은 행부터, 거꾸로 넣기
			for (int r = 0; r < curMap.length; r++) {
				for (int c = 0; c < curMap[0].length; c++) {
					newMap[r][c] = curMap[(curMap.length-1)-r][(curMap[0].length-1)-c];
				}
			}
			
			break;
		case 3:
			// 270도 돌리기(왼쪽)

			// 일단 배열 만들고
			newMap = new int[curMap[0].length][curMap.length];
			
			// 값은 열순으로 넣기
			for (int r = 0; r < curMap[0].length; r++) {
				for (int c = 0; c < curMap.length; c++) {
					newMap[r][c] = curMap[c][(curMap[0].length-1)-r];
				}
			}
			
			break;
		default:
			// 안 돌리기
			newMap = new int[curMap.length][curMap[0].length];
			
			for (int r = 0; r < curMap.length; r++) {
				for (int c = 0; c < curMap[0].length; c++) {
					newMap[r][c] = curMap[r][c]; 
				}
			}
			
			break;
		}
		
		return newMap;
	}
	
	// 붙여보기 메소드
	static void stick(int[][] sticker) {
		
		for (int dir = 0; dir < 4; dir++) {
			
			// 붙일 스티커
			int[][] cur = flip(sticker, dir);
			
			// 맞나 대보기
			int idx = 0;
			while (idx < N*M) {
				
				int cr = idx / M;
				int cc = idx % M;
				
				// 만약 되면 붙이고 마무리
				if (available(cr, cc, cur)) {
					
					for (int r = cr; r < cr+ cur.length; r++) {
						for (int c = cc; c < cc+ cur[0].length; c++) {
							
							// 빈칸일 때는 그냥 지나가면 됨
							if (cur[r-cr][c-cc] == 0)
								continue;
							
							laptop[r][c] = cur[r-cr][c-cc]; 
						}
					}
					return;
				}
				// 안되면 다음으로 이동
				else
					idx++;
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 노트북 크기
		N = input.nextInt();
		M = input.nextInt();
		laptop = new int[N][M];

		// 스티커 개수
		T = input.nextInt();

		// 차례대로 붙이기
		for (int st = 1; st <= T; st++) {
			
			// 스티커별 세로 가로 크기
			int sr = input.nextInt();
			int sc = input.nextInt();
			int[][] newSticker = new int[sr][sc];
			
			// 스티커 정보 입력
			for (int r = 0; r < sr; r++) {
				for (int c = 0; c < sc; c++) {
					newSticker[r][c] = input.nextInt();
				}
			}
			
			// 붙일곳 찾기
			stick(newSticker);
		}
		
	
		// 다 붙이고 나서 남는 지점 세기
		int answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (laptop[r][c] == 1) answer++;
			}
		}
		
		// 정답 출력
		System.out.println(answer);
		
		input.close();
	}
}