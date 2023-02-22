// 퍼펙트 셔플

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 테스트케이스 결과를 보니 선입 선출이어야 하므로 Queue 사용
 * Queue 2개를 써야될 줄 알았는데 그냥 Queue 하나에 절반만 넣고 나머지는 StringTokenizer에서 빼면 된다
 */

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Queue<String> queue;
		StringTokenizer st;
		StringBuilder sb;
		
		// 테스트케이스 개수
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// 테스트케이스 별 카드 수
			int N = Integer.parseInt(input.nextLine());
			
			// 절반을 저장할 Queue 생성
			queue = new LinkedList<>();
			
			// 문자열 입력
			st = new StringTokenizer(input.nextLine());
			
			// Queue에 절반 저장
			for (int i = 1; i <= N/2; i++) {
				queue.offer(st.nextToken());
			}
			
			// 만약 n이 홀수면 queue쪽에서 하나 더 쌓게 offer +1
			if (N % 2 == 1) queue.offer(st.nextToken());
			
			// StringBuilder로 정답 문자열 생성
			sb = new StringBuilder();
			
			// StringTokenizer와 queue를 번갈아 사용하면서 카드 쌓기
			for (int i = 1; i <= N/2; i++) {
				sb.append(queue.poll() + " ");
				sb.append(st.nextToken() + " ");
			}

			// 만약 n이 홀수면 queue쪽에 하나 남았으므로 append +1
			if (N % 2 == 1) sb.append(queue.poll());
			
			// 정답 출력
			System.out.printf("#%d %s\n", tc, sb.toString());
			
		}
		
	}
}