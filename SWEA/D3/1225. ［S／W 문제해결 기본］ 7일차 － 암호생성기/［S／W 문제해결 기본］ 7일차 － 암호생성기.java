// 암호생성기

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Queue<Integer> queue;
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 10개
		for (int tc = 1; tc <= 10; tc++) {
			
			// 테스트케이스 번호(저장 안해도 됨)
			input.nextInt();
			
			// 암호문 생성을 위한 큐 생성
			queue = new LinkedList<>();
			
			// 암호문 초기값 입력
			for (int password = 1; password <= 8; password++) queue.offer(input.nextInt());
			
			// 암호문 제작을 위해 빼줘야 하는 수
			int num = 1;
			
			// 암호문이 완성될 때까지 
			while (true) {
				
				// 싸이클이 몇번 돌았는지 카운트
				int cycle = 0;
				
				// 현 사이클에서 다음에 나올 수
				// 1,2,3,4,5,1,2,3,4,5 순으로 계속 돌고 도니까
				int cur = num % 5;
				if (cur == 0) cur = 5;
				
				// poll한 값 잠시 저장
				int temp = queue.poll() - cur;
				
				// 만약 temp가 0보다 작거나 같으면 temp 대신 0을 넣고 종료, 아니면 그대로 temp 입력
				if (temp <= 0) { queue.offer(0); break;}
				else queue.offer(temp);
				
				// 뺀 숫자가 5였다면 싸이클 +1
				if (cur == 5) { cycle++;}
				
				// num +1
				num++; 
			}
			
			// 암호문 출력
			sb.append("#").append(tc).append(" ");
			while (!queue.isEmpty()) sb.append(queue.poll()).append(" ");
			sb.append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
	}
}