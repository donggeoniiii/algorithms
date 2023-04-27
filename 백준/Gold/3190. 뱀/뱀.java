// 뱀

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		// 보드의 크기
		int N = Integer.parseInt(input.nextLine());
		
		// 게임할 보드 배열 생성
		int[][] map = new int[N][N];
		
		// 뱀 이동에 필요한 델타배열
		int[] dr = {1,0,-1,0};
		int[] dc = {0,-1,0,1};
		
		// 사과의 개수
		int K = Integer.parseInt(input.nextLine());
		
		// 보드에 사과 입력
		for (int apple = 1; apple <= K; apple++) {
			st = new StringTokenizer(input.nextLine());
			
			// 문제는 맨 좌측 위가 (1,1)인데 좌표계는 (0,0)이니까
			int appleL = Integer.parseInt(st.nextToken())-1;
			int appleC = Integer.parseInt(st.nextToken())-1;
			
			// map에 사과 입력
			map[appleL][appleC] = 1;
		}
		
		// 방향 변환 횟수
		int L = Integer.parseInt(input.nextLine());
		
		// 큐 1: 방향 변환 정보 저장
		Queue<String[]> dirQueue = new LinkedList<>();
		
		for (int l = 1; l <= L; l++) {
			st = new StringTokenizer(input.nextLine());
			
			// 방향 정보 입력
			String time = st.nextToken();
			String turn = st.nextToken();
			dirQueue.offer(new String[] {time, turn});
		}
		
		// 큐 2: 뱀이 차지하는 칸 저장
		Queue<int[]> snakeQueue = new LinkedList<>();
		
		// 시작좌표 : (0,0) 저장
		int sr = 0;
		int sc = 0;
		snakeQueue.offer(new int[] {0,0});

		// 시작점 지남 표시
		map[sr][sc] = -1; 
		
		// 뱀의 현재 진행 좌표를 나타내면서 좌표 변경에 사용할 변수
		int cr = sr;
		int cc = sc;
		
		// 처음에 오른쪽으로 진행
		int dir = 3;

		// 여기까지 기본 세팅.
		int sec = 0;

		// 벽에 닿거나 뱀의 몸에 닿을 때까지 진행
		boolean terminate = false;
		while (!terminate) {

			// 1초 경과
			sec++;
			
			// 방향대로 한칸 이동
			int nr = cr + dr[dir];
			int nc = cc + dc[dir];

			// 만약 가는 방향이 이미 몸이거나 배열을 벗어나면 종료
			if ((nr >= N || nc >= N || nr < 0 || nc < 0) || map[nr][nc] == -1) 
				terminate = true;
			else { 
				// 발견한 길이 처음 가는 땅이면
				// 현재까지 뱀의 이동경로 저장
				snakeQueue.offer(new int[] {nr,nc});
				
				// 만약 사과가 아닌 빈땅을 밟았다면 길이가 유지되어야 하니까
				if (map[nr][nc] == 0) {
					int[] cur = snakeQueue.poll();
					
					// 다시 빈 땅으로 만들어서 뱀이 지나갔다고 표시
					map[cur[0]][cur[1]] = 0;
				} 
				else { // 밟은 땅이 사과라면
					
					// 사과는 먹어서 없어지고
					map[nr][nc] = 0; 
					
					// 길이는 그대로 유지되니까 계속 진행
				}

				// 땅 밟음표시
				map[nr][nc] = -1;
				
				// 이동한 위치 반영
				cr = nr;
				cc = nc;
			}

			// 만약 이동방향이 바뀌면
			if (!dirQueue.isEmpty() && sec == Integer.parseInt(dirQueue.peek()[0])) {

				// 어느 방향으로 도는지 확인
				String switchDir = dirQueue.poll()[1];
				
				// 도는 방향에 맞춰 방향 전환
				if (switchDir.equals("L"))
					dir = (dir+3)%4;
				else 
					dir = (dir+1)%4;
			}
			

		}
		
		// 끝나는 시간 출력
		System.out.println(sec);
		
		input.close();
	}
}