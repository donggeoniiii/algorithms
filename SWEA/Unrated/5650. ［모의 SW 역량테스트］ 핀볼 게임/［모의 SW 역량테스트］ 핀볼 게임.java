//핀볼 게임

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	// 전체 맵의 크기
	static int N;
	
	// 전체 맵
	static int[][] map;
	
	// 웜홀의 배열
	// 0,1 : 첫번째 웜홀 좌표, 2,3 : 두번째 웜홀 좌표
	static int[][] wormhole;
	
	// 시작점
	static int sr, sc; // start row, start column
	
	// 블랙홀 좌표
	static int br, bc; // black hole row, black hole column
	
	// 델타배열: 위부터 반시계방향으로 왼쪽, 아래, 오른쪽 순
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	
	// 핀볼 메소드
	public static int pinball(int r, int c, int direction) {
		
		// 해당 이동에서 획득한 점수
		int score = 0;
		
		// 이동시킬 index 변수
		int nr = r;
		int nc = c;
		
		// 현재 움직이는 방향
		int dir = direction;
		
		// 도착점에 도달할 때까지 이동
		boolean terminated = false;
		while (!terminated) {
			
			// 진행방향으로 한칸 이동
			nr += dr[dir];
			nc += dc[dir];
			
			// 좌표 확인 후 방향 전환
			// 0. 종료조건: 시작점이거나 블랙홀이면 종료
			if ((nr == sr && nc == sc) || (nr < N && nc < N && nr >= 0 && nc >= 0 && map[nr][nc] == -1)) {
				terminated = true;
			}
			else {
				// 1. index를 벗어나면
				if (nr >= N || nc >= N || nr < 0 || nc < 0) {
					
					// 5번 블록(ㅁ)를 만났다고 생각하고 반대 방향으로 방향 전환
					dir = (dir + 2) % 4;
					
					// 벽에 부딪히는 순간 바로 길을 돌아가기 때문에, 현재 카운트에 +1한 값을 저장하고 loop 종료
					score = score*2 + 1;
					terminated = true;
				}
				// 2. index 안에서 
				else {
					// map의 값에 따라 구분해서 실행
					int controller = map[nr][nc];
					
					// 값이 1~5면 방향 전환이 일어나므로 카운트 증가
					if (controller >= 1 && controller <= 5) score++;
						
					switch (controller) {
					case 0: // 그냥 기본 땅
						// 아무것도 안해도 됨
						break;
						
					case 1: // 블럭 1: 아래로 내려오면서(2) 만나면 오른쪽(3)으로, 왼쪽으로 가면서(1) 만나면 위(0)로, 나머지 반사
						if (dir == 2) dir = 3;
						else if (dir == 1) dir = 0;
						else {
							dir = (dir + 2) % 4;

							// 180도로 부딪히는 순간 바로 길을 돌아가기 때문에, 현재 카운트에 +1한 값을 저장하고 loop 종료
							score = (score-1)*2 + 1;
							terminated = true;
						}
						break;
						
					case 2: // 블럭 2: 위로 올라오면서(0) 만나면 오른쪽(3)으로, 왼쪽으로 가면서(1) 만나면 아래(2)로, 나머지 반사
						if (dir == 0) dir = 3;
						else if (dir == 1) dir = 2;
						else {
							dir = (dir + 2) % 4;

							// 180도로 부딪히는 순간 바로 길을 돌아가기 때문에, 현재 카운트에 +1한 값을 저장하고 loop 종료
							score = (score-1)*2 + 1;
							terminated = true;
						}
						break;
						
					case 3: // 블럭 3: 위로 올라오면서(0) 만나면 왼쪽(1)으로, 오른쪽으로 가면서(3) 만나면 아래(2)로, 나머지 반사
						if (dir == 0) dir = 1;
						else if (dir == 3) dir = 2;
						else {
							dir = (dir + 2) % 4;

							// 180도로 부딪히는 순간 바로 길을 돌아가기 때문에, 현재 카운트에 +1한 값을 저장하고 loop 종료
							score = (score-1)*2 + 1;
							terminated = true;
						}
						break;
						
					case 4: // 블럭 4: 아래로 내려오면서(2) 만나면 왼쪽(1)으로, 오른쪽으로 가면서(3) 만나면 위(0)로, 나머지 반사
						if (dir == 2) dir = 1;
						else if (dir == 3) dir = 0;
						else {
							dir = (dir + 2) % 4;

							// 180도로 부딪히는 순간 바로 길을 돌아가기 때문에, 현재 카운트에 +1한 값을 저장하고 loop 종료
							score = (score-1)*2 + 1;
							terminated = true;
						}
						break;
						
					case 5: // 블럭 5: 반사
						dir = (dir + 2) % 4;
						

						// 180도로 부딪히는 순간 바로 길을 돌아가기 때문에, 현재 카운트에 +1한 값을 저장하고 loop 종료
						score = (score-1)*2 + 1;
						terminated = true;
						break;
						
					default: // 6~10은 웜홀 이동, 한번에 default로 처리
						int wdx = controller - 6; // worm hole index
						// 반대편 웜홀로 이동
						nr = (nr == wormhole[wdx][0]) ? wormhole[wdx][2] : wormhole[wdx][0];
						nc = (nc == wormhole[wdx][1]) ? wormhole[wdx][3] : wormhole[wdx][1];
						break;	
					}
				}
			}
			
		}
		
		// 점수 반환
		return score;

	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 핀볼판의 크기
			N = input.nextInt();
			
			// 배열 크기 설정
			map = new int[N][N];
			wormhole = new int[5][4];
			// 입력받았는지 여부를 확인하기 위해 -1로 초기화
			for (int[] line : wormhole)	Arrays.fill(line, -1);
			
			// 배열 정보 입력
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					map[r][c] = input.nextInt();
					
					// 블랙홀 위치 입력
					if (map[r][c] == -1) {
						br = r;
						bc = c;
					}
					
					// 웜홀 위치 입력
					if (map[r][c] >= 6 && map[r][c] <= 10) {
						int wdx = map[r][c] - 6;
						
						// 첫 쌍이 입력이 안됐으면 첫 쌍 위치에, 아니면 두번째 쌍 위치에 입력
						if (wormhole[wdx][0] == -1) {
							wormhole[wdx][0] = r;
							wormhole[wdx][1] = c;	
						} else {
							wormhole[wdx][2] = r;
							wormhole[wdx][3] = c;
						}
					}
				}
			}
			
			// 최고점수
			int maxScore = Integer.MIN_VALUE;
			
			// 모든 0인 자리에 대해 DFS on
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					
					if (map[r][c] == 0) {
						
						// 시작위치 설정
						sr = r;
						sc = c;
						
						for (int dt = 0; dt < 4; dt++) {
							// 카운트 초기화
							int curScore = 0;
							
							// 핀볼 게임 진행해서 스코어 받아오기
							curScore = pinball(r, c, dt);
							
							// 만약 최대값이 갱신되면 반영
							if (curScore > maxScore) maxScore = curScore;
						}
					}
				}
			}
			
			
			// 정답 입력
			sb.append("#").append(tc).append(" ").append(maxScore).append("\n");
			
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
		input.close();
	}
}