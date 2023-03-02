import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 땅의 크기
	static int N, M;
	
	// 전체 땅
	static int[][] map;
	
	// 올해 녹는 양 체크
	static int[][] melt;
	
	// 방문체크
	static boolean[][] visited;
	
	// 델타배열
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	// bfs를 위한 queue
	static Queue<int[]> queue;
	
	
	// bfs 알고리즘
	static void BFS(int r, int c) {
		
		// 시작점 입력
		queue.offer(new int[] {r,c});
		
		// 시작점 방문체크
		visited[r][c] = true;
		
		// 더 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 새로운 탐색지점 좌표 받아오기
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			// 델타탐색
			for (int dt = 0; dt < 4; dt++) {
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				
				// index를 벗어나면 제외
				if (nr >= N || nc >= M || nr < 0 || nc < 0) continue;
				
				// 물이거나 이미 방문했으면 제외
				if (map[nr][nc] == 0 || visited[nr][nc]) continue;
				
				// 새로운 방문지점 추가
				queue.offer(new int[] {nr,nc});
				
				// 방문체크
				visited[nr][nc] = true;
			}
			
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 땅의 크기
		N = input.nextInt();
		M = input.nextInt();
		
		// 배열 크기 설정
		map = new int[N][M];
		
		// BFS를 위한 queue
		queue = new LinkedList<>();
		
		// 빙산의 높이 합계 구하기
		int sum = 0;
		
		// 빙산 정보 입력
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = input.nextInt();
				sum += map[r][c];
			}
		}
		
		// 몇년이 지났는지 체크하는 변수
		int year = 0;
		
		// 빙산이 다 녹을 때까지, 연도별로 높이 확인
		while (sum != 0) {
			
			// boolean 배열 초기화
			visited = new boolean[N][M];
			melt = new int[N][M];
			
			
			// 빙산의 개수 카운트
			int ice = 0;
			
			// 배열 돌면서 bfs 시작점 추가
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					
					// 만약 아직 탐색하지 않은 얼음을 만났다면 BFS on
					if (map[r][c] > 0 && !visited[r][c]) {
						BFS(r, c);
						
						// 카운트 추가
						ice++;
					}
				}
			}
			
			// 만약 빙산의 개수가 2개 이상이면 break
			if (ice >= 2) break;
			
			
			// 1년 증가
			year++;
			
			// 1년 지난동안 낮아지는 높이 계산
			for (int r = 0; r < N; r++) {
				for (int c= 0; c < M; c++) {
					
					// 만약 땅을 만나게 되면
					if (map[r][c] > 0) {
						
						// 델타탐색
						for (int dt = 0; dt < 4; dt++) {
							
							int nr = r + dr[dt];
							int nc = c + dc[dt];
							
							// index를 벗어나면 제외
							if (nr >= N || nc >= M || nr < 0 || nc < 0) continue;
							
							// 바다와 접한 면이면 녹는 높이 +1
							if (map[nr][nc] == 0) melt[r][c]++;
						
						}
					}
				}
			}
			

			// 높이 초기화 후 다시 합산
			sum = 0;
			
			// 높이 반영
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					map[r][c] -= melt[r][c];	
					
					// 만약 높이가 음수가 되면 0으로 바꾸기
					if (map[r][c] < 0) map[r][c] = 0;
					
					// 높이 구하기
					sum += map[r][c];
				}
			}

		}
	
		// 빙산이 녹고 끝났으면 -1 출력, 아니면 연도 출력
		if (sum == 0) System.out.println(0);
		else System.out.println(year);
		
	}
}