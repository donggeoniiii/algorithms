// 단지 번호 붙이기

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 아파트 단지의 크기
	static int N;
	
	// 델타배열
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// 집인지 아닌지 구분하는 배열, 다른 정보는 필요 없으니까 boolean으로
	static boolean[][] house;
	
	// BFS를 위한 큐
	static Queue<int[]> queue;
	
	// BFS 알고리즘
	static int BFS(int r, int c) {
		
		// 단지의 크기
		int size = 1;
		
		// 탐색지점을 저장할 큐 초기화
		queue = new LinkedList<>();
		
		// 시작좌표 입력
		queue.offer(new int[] {r,c});
		
		// 다시 방문하지 않도록 집이 아닌 것으로 처리
		house[r][c] = false;
		
		// 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 탐색 좌표 데이터 받아오기
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				
				// 만약 단지밖으로 나가거나 건물이 아니라면 스킵
				if (nr >= N || nc >= N || nr < 0 || nc < 0 || !house[nr][nc]) continue;
				
				// 다음 탐색지역 추가
				queue.offer(new int[] {nr, nc});
				
				// 다시 탐색하지 않도록 집이 아닌 것으로 처리
				house[nr][nc] = false;
				
				// 단지 크기 +1
				size++;
			}
			
			
		}
		
		return size;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 지도의 크기 입력
		N = Integer.parseInt(input.nextLine());
		
		// 배열 크기 설정
		house = new boolean[N][N];
		
		// 집 데이터 입력
		for (int r = 0; r < N; r++) {
			String s = input.nextLine();
			for (int c = 0; c < N; c++) {
				if (s.charAt(c) == '1') house[r][c] = true;
			}
		}
		
		// 단지수 카운트
		int houses = 0;
		
		// 단지별 크기를 저장할 ArrayList
		ArrayList<Integer> area = new ArrayList<>();
		
		// 배열을 순회하면서 단지별 크기 구하기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				
				// 만약 새로운 아파트 단지를 만났다면
				if (house[r][c]) {
					// BFS 후 단지의 넓이 추가
					area.add(BFS(r,c));
					
					// 단지수 +1
					houses++;
				}
			}
		}
		
		// 정답 출력 전 List 정렬
		area.sort(null);
		
		// 정답 출력
		System.out.println(houses);
		for (int size : area) System.out.println(size);
		
	}
}