// 작업순서(stack)

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Solution {
	
	// 진입차수를 보관하는 배열
	static int[] inCnt;
	
	// 출력을 위한 stack
	static Stack<Integer> topoResult;
	
	// 인접리스트
	static ArrayList<Integer>[] adj;
	
	// 이미 체크한 정점을 표시하는 배열
	static boolean[] visited;
	
	// 시스템 스택을 이용한 위상정렬 메소드
	static void topo(int idx) {
		
		// 해당 지점 방문체크
		visited[idx] = true;
		
		// 이어지는 모든 정점들에 대해
		for (int next : adj[idx]) {

			// 아직 방문하지 않았다면 재귀 호출
			if (!visited[next]) topo(next);
			
		}
		
		// 해당 지점 출력을 위해 stack에 추가
		topoResult.add(idx);
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수 10개로 고정
		for (int tc = 1; tc <= 10; tc++) {
			
			// 출력형태 설정
			sb.append("#").append(tc).append(" ");
			
			// 정점의 개수
			int V = input.nextInt();
			
			// 간선의 개수
			int E = input.nextInt();
			
			// 자료구조 초기화
			inCnt = new int[V+1];
			topoResult = new Stack<>();
			adj = new ArrayList[V+1];
			visited = new boolean[V+1];
			for (int idx = 1; idx <= V; idx++) adj[idx] = new ArrayList<>(); 
			
			// 정점간 인접 정보 저장
			for (int edge = 1; edge <= E; edge++) {
				int idx = input.nextInt();
				int next = input.nextInt();
				adj[idx].add(next);
				
				// 진입차수 계산
				inCnt[next]++;
			}
			
			// 배열을 돌면서 진입차수가 0인 정점에서부터 DFS
			for (int idx = 1; idx <= V; idx++) {
				if (inCnt[idx] == 0) topo(idx);
			}
			
			// 정답 추가
			while (!topoResult.isEmpty()) {
				sb.append(topoResult.pop()).append(" ");
			}
			sb.append("\n");
			
		}
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}