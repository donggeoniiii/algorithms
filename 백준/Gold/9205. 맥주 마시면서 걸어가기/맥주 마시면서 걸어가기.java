// 맥주 마시면서 걸어가기

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	// 테케 개수
	static int t;
	
	// 편의점 개수
	static int n;
	
	// 도착지점
	static int tr;
	static int tc;
	
	// 현재 이동 좌표 저장하는 queue
	static Queue<int[]> queue;
	
	// 편의점 방문 확인
	static boolean[] visited;
	
	// 편의점 좌표 저장하는 list
	static List<int[]> storeList;
	
	// dfs
	static boolean walkBfs(int[] src) {
		// queue에 시작점 입력
		queue = new LinkedList<>();
		queue.offer(new int[] {src[0], src[1]});
		
		// 더 갈 수 없을 때까지
		while (!queue.isEmpty()) {
			// 다음 좌표 가져오기
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			
			// 목표지점까지 거리 계산
			int dist = Math.abs(cr-tr) + Math.abs(cc-tc);
			
			// 목표지점까지 1000m이내면 이동 가능한 거니까 true 반환
			if (dist <= 1000)
				return true;
			
			// 아니라면 편의점 좌표와 값 비교
			for (int idx = 0; idx < n; idx++) {
				int nr = storeList.get(idx)[0];
				int nc = storeList.get(idx)[1];
				
				// 이전에 갔으면 제외
				if (!visited[idx]) {
					
					// 거리 계산
					dist = Math.abs(cr-nr) + Math.abs(cc-nc);
					
					// 편의점까지 1000m 이내면 이동 가능하니까 
					if (dist <= 1000) {
						// queue에 추가
						queue.offer(new int[] {nr, nc});
						
						// 방문체크
						visited[idx] = true;
					}
				}
			}
		}
		
		// 그대로 끝나면 못가니까 종료
		return false;
	}
	
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테케개수
		t = input.nextInt();
		
		for (int testcase = 1; testcase <= t; testcase++) {			
			// 편의점 수
			n = input.nextInt();
			
			// 시작좌표
			int sr = input.nextInt();
			int sc = input.nextInt();
			
			// 편의점 좌표 입력
			storeList = new ArrayList<>();
			for (int conv = 1; conv <= n; conv++) {
				int r = input.nextInt();
				int c = input.nextInt();
				
				storeList.add(new int[] {r, c});
			}
			
			// 락페 좌표 입력
			tr = input.nextInt();
			tc = input.nextInt();
			
			// 배열 초기화
			visited = new boolean[n];
			
			
			// 정답 추가
			sb.append(walkBfs(new int[] {sr, sc})? "happy" : "sad");
			sb.append("\n");
		}
		
		
		// 정답 출력
		System.out.println(sb);
	}
}