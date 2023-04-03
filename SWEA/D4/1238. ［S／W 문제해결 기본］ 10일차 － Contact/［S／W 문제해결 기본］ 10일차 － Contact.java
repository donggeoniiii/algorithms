// Contact

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	static final int INF = Integer.MAX_VALUE;
	
	// 한 정점에서 각 정점까지의 최단거리를 보관하는 배열
	static int[] dist;
	
	// 인접리스트
	static ArrayList<Integer>[] adj;
	
	// 탐색 순서를 담을 PriorityQueue
	static Queue<Integer> queue;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수 10개로 고정
		for (int tc = 1; tc <= 10; tc++) {
			
			// FROM-TO 데이터 길이 -> 2로 나누면 간선의 개수
			int E = input.nextInt() / 2;
			
			// 시작점
			int startV = input.nextInt();
			
			// 배열 크기 초기화, 정점의 최대 개수가 100개이므로
			dist = new int[101];
            
            // 거리는 최소거리 갱신을 위해 아주 큰 값으로 초기화
			Arrays.fill(dist, INF);
			
			// 그외 자료구조 초기화
			adj = new ArrayList[101];
			queue = new LinkedList<>();
			
			for (int idx = 1; idx <= 100; idx++) adj[idx]= new ArrayList<>(); 
			
			// FROM-TO 데이터 입력
			for (int edge = 1; edge <= E; edge++) {
				int idx = input.nextInt();
				int next = input.nextInt();
				
				adj[idx].add(next);
			}
			
			// 시작점 정보 입력
			queue.offer(startV);
			
			// 방문한 지점과 안한 지점을 구분하기 위해 시작점의 값을 1로 지정
			dist[startV] = 1;
			
			// BFS ON
			int maxDist = startV; // 최대 거리에 있는 사람 index 저장 변수
			while (!queue.isEmpty()) {
				
				// 탐색좌표 정보 받아오기
				int cur = queue.poll();
				
				// 해당 위치에서 이어지는 모든 간선 체크
				for (int next : adj[cur]) {
					
					// 방문까지 걸린 시간 체크
					if (dist[cur] + 1 < dist[next]) {
						// 기존값보다 작으면 교체하고 새로운 탐색지점 큐에 추가
						dist[next] = dist[cur] + 1;
						queue.offer(next);
						
					}
					
					// 만약 걸린 시간이 현재 최댓값보다 크거나 걸린 시간이 같은데 번호가 더 크다면 갱신
					if (dist[next] > dist[maxDist] || (dist[next] == dist[maxDist] && next > maxDist)) maxDist = next;
				}
				
			}
			
			// 정답 추가
			sb.append("#").append(tc).append(" ").append(maxDist).append("\n");
			
			
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}
