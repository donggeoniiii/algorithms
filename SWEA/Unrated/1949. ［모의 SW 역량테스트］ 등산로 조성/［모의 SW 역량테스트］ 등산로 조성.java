// 등산로 조성

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	// 산 높이
	static int N;
	
	// 최대 공사 깊이
	static int K;
	
	// 산 높이 배열
	static int[][] mountain;
	
	// 주변 탐색을 위한 델타 배열
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// 최댓값 저장 변수
	static int maxLength;
	
	/** 
	 * DFS 메소드
	 * @param r 현재 row
	 * @param c 현재 column
	 * @param length 현재까지 이동 거리, 1부터 시작해야
	 */
	static void DFS(int cr, int cc, int curLength) {
		// pruning: 길이가 최대넓이와 같은 경우가 나오면 더 해볼 필요 없음
		if (curLength == N*N)
			return;
		
		// base case: 값이 0이거나 더 오를 곳이 없으면
		if (!ableToGo(cr, cc)) {

			// 현재까지 저장된 길이와 저장된 최댓값 비교하기
			maxLength = Math.max(maxLength, curLength);
		}
		
		// recursive case
		// 델타배열로 주변 이동
		for (int dt = 0; dt < 4; dt++) {
			
			// 새로운 좌표
			int nr = cr + dr[dt];
			int nc = cc + dc[dt];
			
			// index를 벗어나면 스킵
			if (nr >= N || nc >= N || nr < 0 || nc < 0) 
				continue;
			
			// 못가는 길이면 스킵
			if (mountain[nr][nc] >= mountain[cr][cc])
				continue;
			
			// 다음으로 이동
			DFS(nr, nc, curLength+1);
			
		}
		
	}
	
	/**
	 *  주변 확인 메소드
	 * @param cr 기준이 되는 row
	 * @param cc 기준이 되는 column
	 * @return 4군데가 다 막히면 false, 아님 true 반환
	 */
	static boolean ableToGo(int cr, int cc) {
		// 불가능 카운트
		int impossible = 0;
		
		// 델타 배열을 통해 주변 탐색
		for (int dt = 0; dt < 4; dt++) {
			
			// 새로운 좌표
			int nr = cr + dr[dt];
			int nc = cc + dc[dt];
			
			// index 벗어나면 불가능 카운트 증가, 스킵
			if (nr >= N || nc >= N || nr < 0 || nc < 0) {
				impossible++;
				continue;
			}
			
			// 만약 주변값이 현재 값보다 크거나 같으면 불가능 카운트 ++
			if (mountain[nr][nc] >= mountain[cr][cc]) 
				impossible++;
		}
		
		// 불가능 카운트가 4면 false 반환, 아니면 true
		if (impossible == 4) return false;
		else return true;
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 산의 크기
			N = input.nextInt();
			
			// 최대 공사 가능 깊이
			K = input.nextInt();
			
			// 배열 생성 및 정보 입력
			mountain = new int[N][N];
			
			// 최대위치 좌표를 저장할 ArrayList
			ArrayList<Integer> highestPoints = new ArrayList<>();
			
			int maxHeight = Integer.MIN_VALUE;
			
			// 배열 입력
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					mountain[r][c] = input.nextInt();
					
					// 만약 최대값이 갱신되면 
					if (mountain[r][c] > maxHeight) {
						
						// queue 비우고 최댓값 넣고 갱신
						highestPoints.clear();
						highestPoints.add(r);
						highestPoints.add(c);
						
						maxHeight = mountain[r][c];
						
						// 아래 코드 안타고 가게 갱신하고 좌표 넣었으면 스킵
						continue;
					}
					
					// 최댓값 만나면 queue에 좌표 추가
					if (mountain[r][c] == maxHeight) {
						highestPoints.add(r);
						highestPoints.add(c);
					}
				}
			}
			
			// 최댓값 초기화
			maxLength = -1;
			
			
			// 공사할 좌표, 길이에 따라 DFS
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					
					// 많이 깎을 수록 길이 더 많을 테니
					for (int k = K; k >= 1; k--) {
						
						// 최고점을 다 탐색할 때까지
						int idx = 0;
						while (idx < highestPoints.size()) {
							// 탐색좌표 받아오기
							int cr = highestPoints.get(idx++);
							int cc = highestPoints.get(idx++);
							
							// 만약 깎이는 좌표랑 같으면 최대높이가 아니니까 버리고 다시 받아오기
							if (cr == r && cc == c) {
								if (idx == highestPoints.size()) 
									break;
								
								cr = highestPoints.get(idx++);
								cc = highestPoints.get(idx++);
							}
							
							// 탐색 전에 공사하기
							mountain[r][c] -= k; 
							
							// DFS on
							DFS(cr, cc, 1);

							// 해당 경우 탐색 끝났으니까 다시 메꿔놓고 다음 경우 탐색
							mountain[r][c] += k;
						}
					}

				}
			}
			
			// 정답 입력
			sb.append("#").append(tc).append(" ").append(maxLength).append("\n");
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
		input.close();
	}
}
