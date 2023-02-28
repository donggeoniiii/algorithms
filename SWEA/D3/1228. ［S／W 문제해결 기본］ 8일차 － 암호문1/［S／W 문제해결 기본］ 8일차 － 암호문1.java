// 암호문1

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		LinkedList<String> cypher;
		Queue<String> queue = new LinkedList<>();
		
		
		// 테스트케이스 10개
		for (int tc = 1; tc <= 10; tc++) {
			
			// 정답 양식 맞추기
			sb.append("#").append(tc).append(" ");
			
			// 원본 암호문의 길이 N
			int N = Integer.parseInt(input.nextLine());
			
			// 원본 암호문
			st = new StringTokenizer(input.nextLine());
			
			// 담을 list 생성
			cypher = new LinkedList<>();
			// list에 입력
			while (st.hasMoreTokens()) cypher.add(st.nextToken());
			
			// 명령어의 개수
			int O = Integer.parseInt(input.nextLine());
			
			// 명령어
			st = new StringTokenizer(input.nextLine());
			// 큐에 차례대로 입력
			while (st.hasMoreTokens()) queue.offer(st.nextToken());
			
			// 큐에 저장된 명령이 끝날 때까지
			while (!queue.isEmpty()) {
				
				// 하나씩 오더 받아오기. 시작은 무조건 I이므로 그대로 poll
				queue.poll();
					
				// 다음에 나올 숫자: 숫자를 넣을 index
				int x = Integer.parseInt(queue.poll());
				
				// 그다음에 나올 숫자: 넣을 숫자의 개수
				int y = Integer.parseInt(queue.poll());
					
				// y개의 숫자 index x에(x번째 수 다음에) 입력, 입력 순서를 지키기 위해 index 갱신
				for (int num = 1; num <= y; num++) cypher.add(x++, queue.poll());
				
			}
			
			// 정답에 추가
			for (int i = 0; i < 10; i++) sb.append(cypher.get(i)).append(" ");
			sb.append("\n");
			
 		}
		
		// 정답 출력
		System.out.println(sb.toString());
		
	}
}