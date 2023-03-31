// 보급로(BFS)

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	/**
	 *  크기
	 */
	static int N;

	/**
	 * 전체 지역
	 */
	static int[][] map;
	
	/**
	 * 현재 지역까지 도달하는데 걸리는 최소 시간 저장배열
	 */
	static int[][] eta;
	
	/**
	 * BFS를 위한 queue
	 */
	static Queue<int[]> queue;
	
	/**
	 *  BFS를 위한 델타배열
	 *  상, 하, 좌, 우 순으로 탐색
	 */
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	/**
	 * 너비 우선 탐색 알고리즘
	 */
	static void BFS() {
		
		// 더 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 새로운 방문 지점 가져오기
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			// 델타배열 on
			for (int dt = 0; dt < 4; dt++) {
				
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				
				// index를 벗어나면 스킵
				if (nr >= N || nc >= N || nr < 0 || nc < 0) continue;
				
				// 이미 값이 들어있으면 
				if (eta[nr][nc] != -1) { 
					
					// 값을 비교하고 갱신
					if (eta[cr][cc] + map[nr][nc] < eta[nr][nc]) {
						eta[nr][nc] = eta[cr][cc] + map[nr][nc];
						
						// 새로운 방문 지점 탐색 queue에 추가
						queue.offer(new int[] {nr, nc});
					}
				}
				// 처음 보는 지역이면 값 추가하고 탐색지점 queue에 추가
				else {
					eta[nr][nc] = eta[cr][cc] + map[nr][nc];
					queue.offer(new int[] {nr, nc});
				}
				
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 전장의 크기
			N = input.nextInt();
			
			// 배열 크기 설정
			map = new int[N][N];
			eta = new int[N][N];
			
			// 배열 정보 입력
			for (int r = 0; r < N; r++) {
				String s = input.next();
				for (int c = 0; c < N; c++) {
					map[r][c] = s.charAt(c) - '0';
				}
			}
			
			// 최소시간 배열은 -1로 초기화
			for (int[] arr : eta) {
				Arrays.fill(arr, -1);
			}
			
			queue = new LinkedList<>();
			
			// 시작점 탐색 queue에 입력
			queue.offer(new int[] {0,0});
			
			// 시작점 방문처리
			eta[0][0] = 0;
			
			// BFS on
			BFS();
			
			// 도착지에 보관된 최소 시간 입력
			sb.append("#").append(tc).append(" ").append(eta[N-1][N-1]).append("\n");
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
		input.close();
	}
}
