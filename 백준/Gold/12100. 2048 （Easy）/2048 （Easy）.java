//2048(Easy)

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	// 배열의 크기
	static int N;
	
	// 2048 map
	static int[][] map;
	
	// 블럭을 이동시킬 방향을 저장하는 배열
	static int[] selected;
	
	// 최댓값
	static int maxScore;
	
	// 블럭 옮기기 메소드
	static int moveBlocks(int cnt, int[][] curMap) {
		
		// base case: 5번 다 옮기면 종료
		if (cnt == 5) {
			
			// 배열에서 가장 큰 수 찾기
			int curMax = 0;
			
			// 맵 돌면서 최댓값 갱신
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (curMap[r][c] > curMax)
						curMax = curMap[r][c];
				}
			}
			
			// 정답 출력
			return curMax;
		}
		
		// recursive case
		// 이번에 이동할 방향
		int dir = selected[cnt];
		
		// 방향에 따라 이동 결정
		switch (dir) {
		case 0:
			// 위로 이동
			// 모든 열에 대해서 이동하기
			for (int c = 0; c < N; c++) {
				
				// pointer 1: 합쳐지기를 기다리는 블럭의 위치
				int idx = 0;
				
				// pointer 2: 그 후 발견한 첫번째 블럭의 위치(그 다음칸으로 초기화)
				int idx2 = 1;
		
				// 포인터 2(합칠게 있는지 확인)가 끝까지 가면 종료
				while (idx2 < N) {
					
					// 만약 블럭이 있으면
					if (curMap[idx2][c] > 0) {
						
						// 일단 현재 블럭이 빈칸이면 떙겨놓기
						if (curMap[idx][c] <= 0) {
							curMap[idx][c] = curMap[idx2][c];
							curMap[idx2][c] = 0; 
						}
						else { // 빈칸이 아니면
							
							// 현재 블럭과 값이 같다면 
							if (curMap[idx][c] == curMap[idx2][c]) {
								
								// 합치기
								curMap[idx][c] *= 2;
								
								// 블럭 2개가 하나가 됐으니까 2번째 idx는 0으로 바꾸고
								curMap[idx2][c] = 0; 
								
								// 포인터 이동
								idx++; // 한번 합친 블럭은 다시 합칠 수 없으니까
							}
							
							// 현재 블럭과 값이 다르면
							else { // curMap[idx][c] != curMap[idx2][c]
								
								// 현재 블럭 앞으로 땡겨놓기
								curMap[idx+1][c] = curMap[idx2][c];
								if (idx+1 != idx2)
									curMap[idx2][c] = 0;
								
								// 포인터 이동
								idx++;
							}
						}
					}	
					
					// 포인터 전진
					idx2++;
					
				}		
			}
			break;
		case 1:
			// 왼쪽으로 이동
			// 모든 줄에 대해서 이동하기
			for (int r = 0; r < N; r++) {
				
				// pointer 1: 합쳐지기를 기다리는 블럭의 위치(for문)
				int idx = 0;
				
				// pointer 2: 그 후 발견한 첫번째 블럭의 위치(그 다음칸으로 초기화)
				int idx2 = 1;
		
				// 포인터 2(합칠게 있는지 확인)가 끝까지 가면 종료
				while (idx2 < N) {
					
					// 만약 블럭이 있으면
					if (curMap[r][idx2] > 0) {
						
						// 일단 현재 블럭이 빈칸이면 떙겨놓기
						if (curMap[r][idx] <= 0) {
							curMap[r][idx] = curMap[r][idx2];
							curMap[r][idx2] = 0; 
							
						}
						else { // 빈칸이 아니면
							
							// 현재 블럭과 값이 같다면 
							if (curMap[r][idx] == curMap[r][idx2]) {
								
								// 합치기
								curMap[r][idx] *= 2;
								
								// 블럭 2개가 하나가 됐으니까 2번째 idx는 0으로 바꾸고
								curMap[r][idx2] = 0; 
								
								// 포인터 이동
								idx++; // 한번 합친 블럭은 다시 합칠 수 없으니까
							}
							
							// 현재 블럭과 값이 다르면
							else { // curMap[r][idx] != curMap[r][idx2]
								
								// 현재 블럭 앞으로 땡겨놓기
								curMap[r][idx+1] = curMap[r][idx2];
								if (idx+1 != idx2)
									curMap[r][idx2] = 0;
								
								// 포인터 이동
								idx++;
							}
						}
					}	
					
					// 포인터 전진
					idx2++;
				}		
			}			
			break;
		case 2:
			// 아래로 이동
			// 모든 열에 대해서 이동하기
			for (int c = 0; c < N; c++) {
				
				// pointer 1: 합쳐지기를 기다리는 블럭의 위치(for문)
				int idx = N-1;
				
				// pointer 2: 그 후 발견한 첫번째 블럭의 위치(그 다음칸으로 초기화)
				int idx2 = N-2;
		
				// 포인터 2(합칠게 있는지 확인)가 끝까지 가면 종료
				while (idx2 >= 0) {
					
					// 만약 블럭이 있으면
					if (curMap[idx2][c] > 0) {
						
						// 일단 현재 블럭이 빈칸이면 떙겨놓기
						if (curMap[idx][c] <= 0) {
							curMap[idx][c] = curMap[idx2][c];
							curMap[idx2][c] = 0; 
							
						}
						else { // 빈칸이 아니면
							
							// 현재 블럭과 값이 같다면 
							if (curMap[idx][c] == curMap[idx2][c]) {
								
								// 합치기
								curMap[idx][c] *= 2;
								
								// 블럭 2개가 하나가 됐으니까 2번째 idx는 0으로 바꾸고
								curMap[idx2][c] = 0; 
								
								// 포인터 이동
								idx--; // 한번 합친 블럭은 다시 합칠 수 없으니까
							}
							
							// 현재 블럭과 값이 다르면
							else { // curMap[idx][c] != curMap[idx2][c]
								
								// 현재 블럭 앞으로 땡겨놓기
								curMap[idx-1][c] = curMap[idx2][c];
								if (idx-1 != idx2)
									curMap[idx2][c] = 0;
								
								// 포인터 이동
								idx--;
							}
						}
					}
					
					// 포인터 전진
					idx2--;
				}
			}
			break;
		case 3:
			// 오른쪽으로 이동
			// 모든 줄에 대해서 이동하기
			for (int r = 0; r < N; r++) {
				
				// pointer 1: 합쳐지기를 기다리는 블럭의 위치(for문)
				int idx = N-1;
				
				// pointer 2: 그 후 발견한 첫번째 블럭의 위치(그 다음칸으로 초기화)
				int idx2 = N-2;
		
				// 포인터 2(합칠게 있는지 확인)가 끝까지 가면 종료
				while (idx2 >= 0) {
					
					// 만약 블럭이 있으면
					if (curMap[r][idx2] > 0) {
						
						// 일단 현재 블럭이 빈칸이면 떙겨놓기
						if (curMap[r][idx] <= 0) {
							curMap[r][idx] = curMap[r][idx2];
							curMap[r][idx2] = 0; 
							
							// 이 경우는 이제 해당 블럭은 안 합쳐졌으니까 합쳐질 수 있는 여부를 봐야 하니까 
							// idx는 이동하지 않는다
						}
						else { // 빈칸이 아니면
							
							// 현재 블럭과 값이 같다면 
							if (curMap[r][idx] == curMap[r][idx2]) {
								
								// 합치기
								curMap[r][idx] *= 2;
								
								// 블럭 2개가 하나가 됐으니까 2번째 idx는 0으로 바꾸고
								curMap[r][idx2] = 0; 
								
								// 포인터 이동
								idx--; // 한번 합친 블럭은 다시 합칠 수 없으니까
							}
							
							// 현재 블럭과 값이 다르면
							else { // curMap[r][idx] != curMap[r][idx2]
								
								// 현재 블럭 앞으로 땡겨놓기
								curMap[r][idx-1] = curMap[r][idx2];
								if (idx-1 != idx2)
									curMap[r][idx2] = 0;
								
								// 포인터 이동
								idx--;
							}
						}
					}		
					
					// 포인터 전진
					idx2--;
				}		
				
				
			}
			break;
		}
		
		// 다 했으면 다음으로 이동
		return moveBlocks(cnt+1, curMap);
	}
	
	// 옮길 방향 정하기 메소드
	static void chooseDir(int cnt) {
		
		// base case: 5번 다 정하면
		if (cnt == 5) {
			
			// 배열 복사하고
			int[][] newMap = new int[N][N];
			for (int r = 0; r < N; r++)
				newMap[r] = Arrays.copyOf(map[r], N);

			// 블럭 옮기러 가기
			int curMax = moveBlocks(0, newMap);
			
			// 최댓값 갱신
			maxScore = Math.max(curMax, maxScore);
			
			return;
		}
		
		// recursive case
		for (int dir = 0; dir < 4; dir++) {
			
			// 정하기
			selected[cnt] = dir; 
			
			// 다음 선택하러 이동
			chooseDir(cnt+1);
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 배열 크기
		N = input.nextInt();
		
		// 배열 생성 및 입력
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = input.nextInt(); 
			}
		}
		
		// 배열 및 변수 초기화
		selected = new int[5];
		maxScore = 0;
		
		// 최선의 방식 찾기
		chooseDir(0);
		
		// 정답 출력
		System.out.println(maxScore);
		
		input.close();
	}
}