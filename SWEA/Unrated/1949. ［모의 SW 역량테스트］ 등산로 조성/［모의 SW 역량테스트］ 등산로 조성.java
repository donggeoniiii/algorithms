// 등산로 조성

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
	
	// 방문배열
	static boolean[][] visited;
	
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
	static void DFS(int cr, int cc, int curLength, boolean reduced) {
		// 현재까지 저장된 길이와 저장된 최댓값 비교하기
		maxLength = Math.max(maxLength, curLength);
		
		// recursive case
		// 델타배열로 주변 이동
		for (int dt = 0; dt < 4; dt++) {
			
			// 새로운 좌표
			int nr = cr + dr[dt];
			int nc = cc + dc[dt];
			
			// index를 벗어나면 스킵
			if (nr >= N || nc >= N || nr < 0 || nc < 0) 
				continue;
			
			// 방문했으면 스킵
			if (visited[nr][nc]) continue;
			
			// 다음으로 이동여부 확인
			if (mountain[nr][nc] < mountain[cr][cc]) {
				// 방문 체크
				visited[nr][nc] = true;
				
				DFS(nr, nc, curLength+1, reduced);
				
				// 방문 체크 해제
				visited[nr][nc] = false;
			}
			// 만약 깎아서 되면
			else if (!reduced && mountain[nr][nc] - K < mountain[cr][cc]) {
				// 최소로 깎아서 이동하고
				int temp = mountain[nr][nc];
				mountain[nr][nc] = mountain[cr][cc]-1;
				
				// 방문 체크
				visited[nr][nc] = true;
				
				// 이미 깎아봤다고 표시하고 넘어가기
				DFS(nr, nc, curLength+1, true);
				
				// 방문 체크 해제
				visited[nr][nc] = false;
				
				// 다시 되돌려놓기
				mountain[nr][nc] = temp; 
				
			}
		}
		
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
			
			int maxHeight = Integer.MIN_VALUE;
			
			// 배열 입력
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					mountain[r][c] = input.nextInt();
					
					// 최댓값 갱신
					maxHeight = Math.max(mountain[r][c], maxHeight);
					
				}
			}

			
			// 최고높이 좌표를 저장할 queue
			Queue<Integer> queue = new LinkedList<>();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 만약 높이가 최고높이면
					if (mountain[r][c] == maxHeight) {
						
						// 최고점 좌표 추가
						queue.offer(r);
						queue.offer(c);
					}
				}
			}
			
			// 최댓값 초기화
			maxLength = -1;
			
	
			// 최고점을 다 탐색할 때까지
			while (!queue.isEmpty()) {
				// 탐색좌표 받아오기
				int cr = queue.poll();
				int cc = queue.poll();
				
				// 방문배열 초기화
				visited = new boolean[N][N];
				
				visited[cr][cc] = true;
				
				// DFS on
				DFS(cr, cc, 1, false);

			}

			
			// 정답 입력
			sb.append("#").append(tc).append(" ").append(maxLength).append("\n");
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
		input.close();
	}
}