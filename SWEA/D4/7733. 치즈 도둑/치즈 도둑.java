import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	/**
	 * 치즈 한 변의 길이
	 */
	static int N;
	
	/**
	 * 치즈 맛의 배열 
	 */
	static int[][] cheese;
	
	/** 
	 * 맛을 이미 확인한 부분을 체크하는 배열
	 */
	static boolean[][] checked;
	
	/**
	 *  BFS를 위한 Queue
	 */
	static Queue<int[]> queue;
	
	/**
	 *  주변탐색을 위한 델타배열: 상, 하, 좌, 우 순
	 */
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	/**
	 *  BFS 알고리즘 
	 * @param startingpoint 시작점
	 * @param day 요정이 먹은지 n일차
	 * @return 없음
	 */
	private static void BFS(int[] startingpoint, int day) {
		// queue 초기화
		queue = new LinkedList<>();
		
		// 시작위치 전달
		queue.offer(startingpoint);
		
		// 더 이상 탐색할 지점이 없을 때까지
		while (!queue.isEmpty()) {
			
			// 현재 지점에서부터 탐색
			int[] currentpoint = queue.poll();
			int cr = currentpoint[0]; // current row
			int cc = currentpoint[1]; // current column
			
			// 델타탐색으로 주변 확인
			for (int dt = 0; dt < 4; dt++) {
				
				// 새로운 지점 좌표
				int nr = cr + dr[dt]; // new row
				int nc = cc + dc[dt]; // new column
				
				// index를 벗어나면 제외
				if (nr >= N || nc >= N || nr < 0 || nc < 0) continue;
				
				// 이미 방문한 지역이면 제외
				if (checked[nr][nc]) continue;
				
				// 치즈가 이미 다 먹힌 구역이면 제외
				if (cheese[nr][nc] - day <= 0) continue;
				
				// 다 통과했으면 아직 이 덩어리에 확인하지 않은 부분이 있으므로 새로운 탐색지점 추가
				queue.offer(new int[] {nr,nc});

				// 해당구역 체크 표시
				checked[nr][nc] = true;
			}
			
		}
	
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 치즈 한 변의 길이
			N = input.nextInt();
			
			// 치즈 배열 생성
			cheese = new int[N][N];
			
			// 맛 입력
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					cheese[r][c] = input.nextInt(); 
				}
			}
			
			// 치즈 덩어리의 최대개수
			int maxCnt = Integer.MIN_VALUE;
			
			// 1일차부터 100일차까지 진행하며 최대값 찾기
			for (int day = 0; day <= 100; day++) {

				// 체크배열 초기화
				checked = new boolean[N][N];
				
				// 현재 시점에서의 덩어리 개수
				int cheeseCnt = 0;
				
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						
						// 만약 n일차 시점에서 아직 치즈가 남아있는 부분인데 처음 체크한다면
						if (cheese[r][c] - day > 0 && !checked[r][c]) {
							
							// 일단 방문체크
							checked[r][c] = true; 
							
							// BFS 알고리즘으로 덩어리 체크
							BFS(new int[] {r, c}, day);
							
							// 덩어리 카운트+1
							cheeseCnt++;
						}
					}
				}
				
				// 만약 최대값을 넘어섰다면 갱신
				if (cheeseCnt > maxCnt) maxCnt = cheeseCnt;
				
				// 만약 덩어리 개수가 0개에서 갱신되지 않으면 더 이상 치즈가 남지 않았으므로 탐색 종료
				if (cheeseCnt == 0) break;
				
			}
			
			// 정답 입력
			sb.append("#").append(tc).append(" ").append(maxCnt).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}