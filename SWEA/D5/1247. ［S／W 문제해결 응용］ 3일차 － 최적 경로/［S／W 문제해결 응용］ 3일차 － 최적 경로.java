
// 최적 경로

import java.util.Scanner;

public class Solution {
	/**
	 *  고객 수
	 */
	static int N;
	
	/**
	 *  회사 좌표
	 *  index 0 : row, index 1 : column
	 */
	static int[] company;
	
	/**
	 * 집 좌표 
	 * index 0 : row, index 1 : column
	 */
	static int[] home;
	
	/**
	 *  고객들의 좌표
	 *  index 0 : row, index 1 : column
	 */
	static int[][] clients;
	
	/**
	 * 마지막으로 방문한 고객 위치
	 * index 0 : row, index 1 : column
	 */
	static int[] lastpoint;
	
	/**
	 * 방문 여부
	 */
	static boolean[] visited;
	
	/**
	 *  최솟값
	 */
	static int minDist;
	
	
	/**
	 *  백트래킹 알고리즘
	 * @param cnt 현재까지 탐색한 고객 좌표 수
	 * @param dist 현재까지 탐색하며 이동한 거리 총 합
	 * @param i 
	 * @param company2 
	 */
	public static void backtrack(int cnt, int x, int y, int dist) {
		// pruning: 거리 합이 이미 최소값을 넘어가면 제외
		if (dist > minDist) return;
		
		// base case
		if (cnt == N) {
			
			// 마지막 위치에서 집까지 거리 추가
			int finalDist = dist + Math.abs(x-home[0]) + Math.abs(y-home[1]);
			
			// 최솟값과 비교해서 더 작아지면 갱신
			if (finalDist < minDist) minDist = finalDist;
			
			return;
		}
		
		// recursive case
		for (int idx = 0; idx < N; idx++) {
			
			// 이미 방문한 경우 제외
			if (visited[idx]) continue;

			// 방문체크
			visited[idx] = true;
			
			// 거리 추가
			int dx = Math.abs(x-clients[idx][0]);
			int dy = Math.abs(y-clients[idx][1]);
			
			// 재귀
			backtrack(cnt+1, clients[idx][0], clients[idx][1], dist + dx + dy);
			
			// 해당경우 다 체크했으므로 방문체크 해제
			visited[idx] = false; 
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 고객의 수
			N = input.nextInt();
			
			// 배열 크기 설정
			company = new int[2];
			home = new int[2];
			clients = new int[N][2];
			visited = new boolean[N];
			lastpoint = new int[2]; 
			
			// 좌표 입력
			company[0] = input.nextInt();
			company[1] = input.nextInt();
			
			home[0] = input.nextInt();
			home[1] = input.nextInt();
			
			for (int i = 0; i < N; i++) {
				clients[i][0] = input.nextInt();
				clients[i][1] = input.nextInt();
			}
			
			// 최솟값 초기화
			minDist = Integer.MAX_VALUE;
			
			// 시작값 입력
			lastpoint[0] = company[0];
			lastpoint[1] = company[1];
			
			// 백트래킹 on
			backtrack(0, company[0], company[1], 0);
			
			// 정답 추가
			sb.append(minDist).append("\n");
		}

		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}
