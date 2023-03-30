// 창용마을 무리의 개수

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	/*
	 * 창용마을 사람 수
	 */
	static int N;
	
	/*
	 * 서로 아는 관계의 수
	 */
	static int M;
	
	/*
	 * 관계를 정리한 배열
	 * 크기: N*2, index 0, 1은 각각 연결된 사람
	 */
	static int[][] relations;
	
	/**
	 *  각 사람이 속한 무리의 대표를 정리하는 배열
	 */
	static int[] leader;
	
	/**
	 * 이 사람이 속한 무리의 leader를 반환하는 메소드
	 * @param x 무리에 속한 사람
	 * @return 해당 무리 리더의 index
	 */
	static int findLeader(int x) {
		// 리더가 누군지 바로 알 수 없는 경우 더 관계를 타고 이동
		if (leader[x] != x) leader[x] = findLeader(leader[x]); 
		return leader[x];
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 사람 수
			N = input.nextInt();
			
			// 서로 알고 있는 관계 수
			M = input.nextInt();
			
			// 배열 크기 설정
			relations = new int[M][2];
			leader = new int[N+1]; // 사람 이름을 index로 쓰기 위해 크기 +1
			
			// 관계 입력
			for (int idx = 0; idx < M; idx++) {
				relations[idx][0] = input.nextInt();
				relations[idx][1] = input.nextInt();
			}
			
			// 모든 사람을 하나의 무리로 간주(초기화)
			for (int idx = 1; idx <= N; idx++) {
				leader[idx] = idx;
			}
			
			// 각 관계를 확인하면서 무리짓기
			for (int idx = 0; idx < M; idx++) {
				
				// 관계 하나 선택, 두명의 index 정보 받기
				int man1 = relations[idx][0];
				int man2 = relations[idx][1];
				
				// 만약 두 사람이 이미 같은 무리에 있다면 스킵
				if (findLeader(man1) == findLeader(man2)) continue;
				
				// 관계가 있다는 건 묶을 수 있다는 뜻이므로 두 경우 합치기
					leader[findLeader(man1)] = findLeader(man2);
			}
		
			// 무리의 수를 세기 위한 set
			Set<Integer> set = new HashSet<>();
			
			for (int idx = 1; idx <= N; idx++) {
				set.add(findLeader(idx));
			}
			
			// 테스트 케이스 별 정답 추가
			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}
		
		// 전체 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}