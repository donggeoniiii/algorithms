// 서로소 집합

import java.util.Scanner;

public class Solution {
	// 집합의 대표자를 저장하는 배열
	static int[] leader;
	
	// 집합의 대표자를 찾는 메소드
	static int findleader(int x) {
		// path-compression
		// 대표자가 바로 구해지지 않으면 한칸 올라가기
		if (leader[x] != x) return leader[x] = findleader(leader[x]);
		
		return leader[x];	
	}
	
	// 서로 다른 두 집합을 합치는 메소드
	static void union (int x, int y) {
		if (findleader(x) != findleader(y)) leader[findleader(y)] = findleader(x);
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 원소의 개수
			int N = input.nextInt();
			
			// 연산 횟수
			int M = input.nextInt();
			
			// 각각의 원소를 단일 원소로 하는 집합으로 초기화
			leader = new int[N+1];
			
			for (int num = 1; num <= N; num++) {
				leader[num] = num;
			}
			
			// 연산 입력
			for (int oper = 1; oper <= M; oper++) {
				
				// 연산의 유형
				int type = input.nextInt();
				
				// 집합1
				int set1 = input.nextInt();
				
				// 집합2
				int set2 = input.nextInt();
				
				// 연산 유형에 따라 구분
				switch (type) {
				case 0: // 합치라고 하는 거면
					// 합치기
					union(set1, set2);
					break;
				case 1: // 같은 집합에 있는지 확인하라는 거면
					// 확인해서 같으면 1, 다르면 0 반환
					if (findleader(set1) == findleader(set2)) {
						sb.append("1");
					}
					else sb.append("0");
					break;
				}
			}
			sb.append("\n");
		}
		// 정답 출력
		System.out.println(sb.toString());
		
		input.close();
	}
}