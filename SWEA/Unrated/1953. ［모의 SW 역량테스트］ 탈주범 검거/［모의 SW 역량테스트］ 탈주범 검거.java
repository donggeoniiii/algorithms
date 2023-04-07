// 도주범 검거(BFS)

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	
	// 터널의 세로 크기
	static int N;
	
	// 터널의 가로 크기
	static int M;
	
	// 탈출 후 소요된 시간
	static int L;
	
	// 터널 지도
	static int[][] map;
	
	// 방문 시간을 기록하는 배열
	static int[][] visited;

	// 터널 종류에 따른 델타 배열
	// 순서대로 상하좌우 / 상하 / 좌우 / 상우 / 하우 / 하좌 / 상좌
	static int[][] dr = {{}, {-1,1,0,0}, {-1,1}, {0,0}, {-1,0}, {1,0}, {1,0}, {-1,0}};
	static int[][] dc = {{}, {0,0,-1,1}, {0,0}, {-1,1}, {0,1}, {0,1}, {0,-1}, {0,-1}};
	
	// BFS 메소드
	static void BFS(int r, int c) {
		
		// queue 선언
		Queue<Integer> queue = new LinkedList<>();
		
		// 시작점 입력
		queue.offer(r);
		queue.offer(c);
		
		// 더 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 탐색지점 좌표
			int cr = queue.poll();
			int cc = queue.poll();
			
			// 터널의 종류 
			int ctrl = map[cr][cc];
			
			// 현재 터널의 중류에 따라 갈 수 있는 방향만 탐색
			for (int dt = 0; dt < dr[ctrl].length; dt++) {
				
				// 새로운 터널 좌표
				int nr = cr + dr[ctrl][dt];
				int nc = cc + dc[ctrl][dt];

				// index를 벗어나면 제외
				if (nr >= N || nc >= M || nr < 0 || nc < 0)
					continue;
				
				// 이어갈 수 없으면 제외
				if (map[nr][nc] <= 0)
					continue;

				// 인접한 터널의 종류
				int nctrl = map[nr][nc];
				
				// 현재 방향에서 인접한 터널이 현재 터널과 연결 가능한지 확인
				// 두 터널이 서로 이어지는지 확인하는 변수
				boolean impossible = true;

				for (int ndt = 0; ndt < dr[nctrl].length; ndt++) {
					// 만약 인접한 터널이 현재 터널과 상하나 좌우로 이어진다면
					if ((dr[ctrl][dt] != 0 && dr[ctrl][dt] == -dr[nctrl][ndt]) || (dc[ctrl][dt] != 0 && dc[ctrl][dt] == -dc[nctrl][ndt]))
						impossible = false; // 이동 가능 표시
				}
				
				// 만약 이어지지 않아서 이동이 안되면 제외
				if (impossible) 
					continue;
				
				// 방문했거나 시간이 지나면 제외
				if (visited[nr][nc] > 0)
					continue;

				// L번 안에 방문했으면 새로운 좌표를 찾았으니까 방문 표시
				if (visited[cr][cc] + 1 <= L) {
					visited[nr][nc] = visited[cr][cc] + 1;
					
					// 더 갈 수 있으면 새로운 탐색 좌표 추가
					if (visited[nr][nc] < L) {
						queue.offer(nr);
						queue.offer(nc);
					}
				}

			}	
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 세로크기 
			N = input.nextInt();
			
			// 가로크기
			M = input.nextInt();
			
			// 배열 생성
			map = new int[N][M];
			
			// 맨홀뚜껑의 위치
			int hr = input.nextInt(); // hole row
			int hc = input.nextInt(); // hole column
			
			// 탈출 후 소요 시간
			L = input.nextInt();
			
			// 터널 상황 입력
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					map[r][c] = input.nextInt();
				}
			}
						
			// 방문배열 초기화
			visited = new int[N][M];
			
			// 시작점 방문표시
			visited[hr][hc] = 1;
			
			// BFS ON
			BFS(hr, hc);
			
			// 방문한 지역의 수 세기
			int hidePoints = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (visited[r][c] > 0) 
						hidePoints++; 
				}
			}
			
			// 정답 추가
			sb.append("#").append(tc).append(" ").append(hidePoints).append("\n");
		}
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}