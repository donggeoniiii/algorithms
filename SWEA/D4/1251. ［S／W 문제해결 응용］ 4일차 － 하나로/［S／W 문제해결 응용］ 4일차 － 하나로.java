// 하나로

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
	/**
	 *  섬의 개수
	 */
	static int V; // vertex
	
	/**
	 *  섬과 섬 사이에서 만들어지는 모든 터널의 개수
	 */
	static int E; // edge
	
	/**
	 *  터널 건설시 부과되는 환경 부담 세율
	 */
	static double tax;

	/**
	 *  섬의 좌표
	 *  index 0 : x좌표, index 1: y좌표
	 */
	static int[][] islands;
	
	/** 
	 * 모든 터널의 종착지(부모 섬 ? 표시)
	 */
	static int[] departure; 
	
	/**
	 * 크루스칼 알고리즘을 위한 makeSet 메소드
	 * @param x 자기 자신만을 원소로 하는 집합 생성
	 */
	static void makeSet(int x) {
		departure[x] = x;
	}
	
	/**
	 * 크루스칼 알고리즘을 위한 findSet 메소드
	 * @param x 부모 섬을 찾으려는 섬 index
	 * @return 부모 섬의 index
	 */
	static int findSet(int x) {
		// path compression
		// 해당 원소의 부모를 찾을 때까지 위로 이동
		if ( departure[x] != x ) departure[x] = findSet(departure[x]);
		
		return departure[x]; 
	}
	
	/**
	 * 크루스칼 알고리즘을 위한 union 메소드
	 * 두 대표값 동기화
	 * @param x 포함할 집합(의 대표값)
	 * @param y 포함될 집합(의 대표값)
	 */
	static void union(int x, int y) {
		departure[findSet(y)] = findSet(x); 
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb;
	
		// 테스트 케이스
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 섬의 개수
			V = input.nextInt();
			
			// 터널의 개수 : N * N-1 / 2개
			E = V * (V-1) / 2;
			
			// 배열 크기 설정
			islands = new int[V][2];
			departure = new int[V];
			
			// 크루스칼 알고리즘을 위한 우선순위 queue 
			// 비용순으로 모든 터널을 정렬한다
			PriorityQueue<double[]> tunnel = new PriorityQueue<>(new Comparator<double[]>() {
				
				@Override
				public int compare(double[] o1, double[] o2) {
					return (int) (o1[2] - o2[2]);
				}
				
			});
			
			// 섬 별 위치 정보 입력 (x좌표)
			for (int idx = 0; idx < V; idx++) {
				islands[idx][0] = input.nextInt();
			}		
			
			// 섬 별 위치 정보 입력 (y좌표)
			for (int idx = 0; idx < V; idx++) {
				islands[idx][1] = input.nextInt();
			}
			
			// 세율 입력
			tax = input.nextDouble();
			
			// 섬 별 위치정보를 가지고 가능한 모든 간선 정보 생성
			int tdx = 0; // tunnel index
			for (int idx = 0; idx < V; idx++) {
				for (int jdx = idx; jdx < V; jdx++) {
					
					// 같은 index인 경우 제외
					if (idx == jdx) continue;
					
					// 두 섬 사이 거리
					double dx = Math.abs(islands[idx][0] - islands[jdx][0]);
					double dy = Math.abs(islands[idx][1] - islands[jdx][1]);
					double dist = (dx*dx) + (dy*dy);
					
					/**
					 * 	터널 정보 입력
					 *  모든 터널의 정보
					 *  index 0 : 시작점, index 1 : 끝점, index 2 : 점 사이 거리
					 */
					
					double[] newTunnel = new double[3];
					newTunnel[0] = idx;
					newTunnel[1] = jdx;
					newTunnel[2] = dist;
					
					// 우선순위 큐에 추가
					tunnel.offer(newTunnel);
				}
			}
			
			
			/**
			 * 크루스칼 알고리즘 1단계
			 * 모든 섬을 원소의 개수가 하나인 부분 집합으로 만든다
			 */
			for (int idx = 0; idx < V; idx++) {
				makeSet(idx);
			}

			
			/**
			 * 크루스칼 알고리즘 2단계
			 * N-1개의 터널을 고를 때까지 반복
			 */
			// 비용 합
			double totalCost = 0;
			
			// 선택한 터널 수
			int cnt = 0;
			
			// 모든 터널에 대해 탐색
			while (!tunnel.isEmpty()) {
				
				// 터널 하나 선정, 시작섬 끝섬 가져오기
				double[] curTunnel = tunnel.poll();
				int x = (int) curTunnel[0];
				int y = (int) curTunnel[1];
				
				// 터널 하나를 뽑았는데 둘의 부모섬(?)이 다르면 사이클이 생기지 않으므로
				if (findSet(x) != findSet(y)) {
					
					// 다음데 해당 섬이 들어있는 집합에서는 뽑지 않도록
					// 두 섬을 같은 집합으로 엮고
					union(x, y);
					
					// 터널 건설 확정, 비용 추가
					totalCost += curTunnel[2] * tax;
					
					// 선택 카운트 증가
					cnt++;
				}
				
				// 만약 필요한 개수를 다 뽑았다면 종료
				if (cnt == V-1) break;
				
			}
			
			// 정답 추가
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(Math.round(totalCost));
			
			// 테스트케이스별 정답 출력
			System.out.println(sb.toString());
		}
		
		
		input.close();
	}
}