// 이분그래프

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	// 테스트케이스 개수
	static int k;
	
	// 정점과 간선의 개수
	static int v, e;
	
	// bfs를 위한 인접리스트
	static List<Integer>[] adj;
	
	// bfs를 위한 방문배열
	static int[] visited;
	
	// bfs 횟수 == 그룹 수, 저장할 변수
	static int groupCnt;
	
	// bfs를 위한 queue
	static Queue<Integer> queue;
	
	// 이분그래프 판별 알고리즘
	static boolean binaryGraph() {
	
		// 1~n까지 node를 돌면서
		for (int node = 1; node <= v; node++) {
			// 탐색하지 않은(방문하지 않은) node면
			if (visited[node] == 0) {
				
				// bfs on
				// bfs를 위한 queue
				queue = new LinkedList<>();
				
				// 시작점 추가
				queue.offer(node);
				
				// 시작점 방문체크, 현재 체크중인 그룹이 몇번짼지로 표시
				visited[node] = 1;
				
				// 더 탐색할 지점이 없을 때까지
				while (!queue.isEmpty()) {
					
					// 탐색할 다음 좌표 가져오기
					int cur = queue.poll();
					
					// 이어지는 애들 색칠할 색은 현재 좌표의 색과 반대
					int curNum = (visited[cur] == 1) ? 2 : 1;
					
					// 이어지는 애들 탐색
					for (int next : adj[cur]) {
						
						// 방문하지 않은 경우
						if (visited[next] == 0) {
							
							// 방문체크
							visited[next] = curNum;
							
							// 다음 탐색좌표 추가
							queue.offer(next);
						}
						
						// 방문한 경우
						else {
							// 현재 좌표와 값이 같으면
							if (visited[next] == visited[cur])
								// 이분그래프 안되니까 false 반환
								return false;
						}
					}
				}
			}
		}

		// 무사히 통과했으면 이분 가능하므로 true 반환
		return true;
	}
	

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테케 개수
		k = input.nextInt();
		
		for (int tc = 1; tc <= k; tc++) {
			// 정점의 개수
			v = input.nextInt();
			
			// 간선의 개수 
			e = input.nextInt();
			
			// 배열 및 리스트 초기화
			visited = new int[v+1];
			adj = new LinkedList[v+1];
			for (int node = 1; node <= v; node++)
				adj[node] = new LinkedList<>(); 
			
			// 간선 정보 입력
			for (int edge = 1; edge <= e; edge++) {
				int src = input.nextInt();
				int dest = input.nextInt();
				
				// 이어지면 양쪽으로 이어졌다고 봐야되니까
				adj[src].add(dest);
				adj[dest].add(src);
			}
			
			// 가능한지 판단
			boolean isPossible = binaryGraph();
			
			
			// true면 이분 그래프, 아니면 x
			if (isPossible)
				sb.append("YES");
			else
				sb.append("NO");
				
			sb.append("\n");
		}
		
		// 정답 출력
		System.out.println(sb);
	}
}