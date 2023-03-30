// 창용마을 무리의 개수

import java.util.HashSet;
import java.util.Scanner;

public class Solution {
	
	// 해당 사람이 속한 무리의 리더가 누군지 저장하는 배열
	static int[] leader;
	
	// 리더 찾기 메소드
	static int findLeader(int x) {
		
		// path-compression 
		if (leader[x] != x) return leader[x] = findLeader(leader[x]);
		
		return leader[x];
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 사람 수
			int N = input.nextInt();
			
			// 관계의 개수 M
			int M = input.nextInt();
			
			// 각 사람을 1명으로 이루어진 무리로 간주, 리더는 자기 자신이 된다
			leader = new int[N+1];
			for (int idx = 1; idx <= N; idx++) {
				leader[idx] = idx;
			}
			
			// 관계를 입력하면서 같은 무리로 묶을 수 있으면 묶기
			for (int relation = 1; relation <= M; relation++) {
				
				int man1 = input.nextInt();
				int man2 = input.nextInt();
				
				// 만약 이미 같은 무리면 스킵
				if (findLeader(man1) == findLeader(man2)) continue;
				
				// 아니면 묶기
				leader[findLeader(man2)] = findLeader(man1);
			}
			
			
			// 무리의 개수를 세기 위한 set
			HashSet<Integer> leaderCnt = new HashSet<>();
			
			// 사람별로 set에 자기 무리 리더 이름 넣기
			for (int i = 1; i <= N; i++) {
				leaderCnt.add(findLeader(i));
			}
			
			// 정답 입력
			sb.append("#").append(tc).append(" ").append(leaderCnt.size()).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
		
		input.close();
	}
}