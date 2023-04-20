import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	// 뿌요뿌요 필드의 크기
	static final int N = 12, M = 6;
	
	// 뿌요뿌요 맵
	static char[][] map;
	
	// 방문 배열(for bfs)
	static boolean[][] visited;
	
	// 델타배열
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	// 이번 연쇄에 터지는 애들을 넣을 스택
	static Stack<int[]> stack;
	
	// 이번 탐색에서 깨진게 있는지 확인하는 변수
	static boolean hasPuyo;
	
	
	// 중력 메소드
	static void gravity(int cc) {
		
		// 시작은 맨 아래부터
		for (int r = N-1; r > 0; r--) {
			int cr = r;
			
			// 마지막 지점에 도달하거나 빈 지점이 아닐 때까지
			while (cr > 0 && map[cr][cc] == '.') {
				// 위로 한칸 올리기
				cr--;
			}
			
			// 마지막 지점에 왔는데 돌이 없으면 종료
			if (cr == 0 && map[cr][cc] == '.')
				return;
			
			// 밑이 빈칸이면 땡겨놓기
			while (cr < N-1 && map[cr+1][cc] == '.') {
				map[cr+1][cc] = map[cr][cc]; 
				map[cr][cc] = '.'; 
				cr++;
			}
		}
		
		
	}
	
	
	// BFS 하면서 배열 나갔나 보기
	static boolean outOfIndex(int nr, int nc) {
		return nr >= N || nc >= M || nr < 0 || nc < 0;
	}
	
	// bfs 메소드
	static void bfs(int sr, int sc) {
	
		// 탐색을 위한 queue
		Queue<int[]> queue = new LinkedList<>();
		
		// 시작점 입력
		queue.offer(new int[] {sr, sc});
		
		// 방문체크
		visited[sr][sc] = true;
		
		// 일단 시작점도 처음엔 뽀개기 위해 스택에 추가
		stack.push(new int[] {sr, sc});
		
		// 탐색한 지점 수
		int puyo = 1;
		
		// 더 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 탐색 지점 가져오기
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			for (int dt = 0; dt < 4; dt++) {
			
				// 델타배열로 탐색한 새로운 좌표
				int nr = cr + dr[dt];
				int nc = cc + dc[dt];
				
				// index 벗어나면 스킵
				if (outOfIndex(nr, nc))
					continue;
				
				// 뿌요가 없으면 스킵
				if (map[nr][nc] == '.')
					continue;
				
				// 다른 색이면 스킵
				if (map[nr][nc] != map[cr][cc])
					continue;
				
				// 방문했으면 스킵
				if (visited[nr][nc])
					continue;
				
				// 새로운 탐색위치 queue에 저장
				queue.offer(new int[] {nr,nc});
				
				// 방문체크
				visited[nr][nc] = true; 
				
				// 부수기 위해 스택에 저장, 탐색횟수 증가
				stack.push(new int[] {nr,nc});
				puyo++;
			}
			
		}

		// 만약 탐색 지점이 4개가 안되면 스택에 최근에 넣은 애들 빼기
		if (!stack.isEmpty() && puyo < 4) {
			while (puyo > 0) {
				stack.pop();
				puyo--;
			}
		}
		
		
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 배열 생성
		map = new char[N][M];
		
		// 값 집어넣기
		for (int r = 0; r < N; r++) {
			String line = input.nextLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c); 
			}
		}
		
		// 몇번 연쇄됐는지 확인
		int chain = 0;
		
        
		// 이번 탐색에서 깨진게 있는지 확인
		hasPuyo = true;
        
		// 연쇄되는 지점 찾기
		while (hasPuyo) {
			// 이번 탐색에서 깨진게 없으면 종료하게
			hasPuyo = false;
			
			// 연쇄 회차별로 방문배열, 없앨 뿌요뿌요 위치 저장하는 stack 초기화
			visited = new boolean[N][M];
			stack = new Stack<>();
			
			
			// 돌면서 처음 보는 지점이면 BFS
			// 중력이 작용하니까 맨 밑부터 탐색
			for (int r = N-1; r >= 0; r--) {
				
				// 만약 탐색한 한줄이 다 .이었으면 중력때문에 위는 없을 것
				// 그러면 탐색을 끝내도 되니까 체크하는 변수
				boolean noPuyo = true;
				
				for (int c = 0; c < M; c++) {
					
					// 그 줄에 돌이 있었으면 일단 부술게 있으니까 탐색 계속 해야
					if (map[r][c] != '.') {
						noPuyo = false;
				
						// 처음 보는 지점이 나오면 탐색 시작
						if (!visited[r][c]) 
							bfs(r,c);	
					}
				}
				
				// 만약 뿌요뿌요가 없었으면 더 해보지 말고 종료
				if (noPuyo)
					break;
			}
			
			// 스택에 들어있는 좌표 .으로 바꾸기
			while (!stack.isEmpty()) {
				
				// 깰게 있었으면 일단 연쇄스택은 쌓을 수 있다
				hasPuyo = true;
				
				// 지울 좌표 꺼내오기
				int[] cur = stack.pop();
				int cr = cur[0];
				int cc = cur[1];
				
				// 뿌요뿌요
				map[cr][cc] = '.'; 
			}
			
			// 다 뽀갰으면 중력으로 땡겨오기
			for (int c = 0; c < M; c++) {
				gravity(c);
			}
			
			// 뽀갠게 있으면 스택 증가
			if (hasPuyo)
				chain++;
			
		}
		
		// 정답 출력
		System.out.println(chain);
		
		input.close();
	}

}