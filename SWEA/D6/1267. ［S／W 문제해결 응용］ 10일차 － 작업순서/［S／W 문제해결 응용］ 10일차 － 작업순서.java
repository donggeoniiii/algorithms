// 작업순서

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {	
	// 위상정렬을 위한 queue(index 저장)
	static Queue<Integer> queue;
	
	// 그래프를 표현하는 인접리스트
	static ArrayList<Integer>[] adj;
	
	// 각 정점의 진입차수를 값으로 갖는 배열
	static int[] inCnt;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 정점의 개수
			int V = input.nextInt();
			
			// 간선의 개수
			int E = input.nextInt();
			
			// 배열 및 인접리스트 초기화
			queue = new LinkedList<>();
			inCnt = new int[V+1];
			adj = new ArrayList[V+1];
			for (int idx = 1; idx <= V; idx++) {
				adj[idx] = new ArrayList<>();
			}
			
			// 간선 정보 입력 받기
			for (int edge = 1; edge <= E; edge++) {
				int idx = input.nextInt();
				int next = input.nextInt();
				adj[idx].add(next);
				inCnt[next]++;
			}
			
			// 위상정렬을 통해서 작업순서 찾기
			// 1. 진입차수가 0인 지점은 queue에 넣기 
			for (int idx = 1; idx <= V; idx++) {
				if (inCnt[idx] == 0) {
					sb.append(idx).append(" "); // 출력형태에 추가
					queue.offer(idx);
				}
			}
			
			// 2. queue가 빌 때까지
			while (!queue.isEmpty()) {
				
				// 2-1. 정점 좌표 가져오기
				int cur = queue.poll();
				
				// 2-2. 해당 정점과 이어지는 좌표 진입차수 감소
				for (int next : adj[cur]) {
					
					inCnt[next] -= 1;
					
					// 만약 0이 되면 queue에 추가
					if (inCnt[next] == 0) {
						sb.append(next).append(" "); // 출력형태에 추가
						queue.offer(next);
					}
				}
			}
			
			// 출력형태 설정
			sb.append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}
