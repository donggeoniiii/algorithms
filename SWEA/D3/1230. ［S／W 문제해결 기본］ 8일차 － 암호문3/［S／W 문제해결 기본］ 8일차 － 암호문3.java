// 암호문3

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Queue<String> queue = new LinkedList<>();
		ArrayList<String> nums = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		// 10개의 테스트케이스
		for (int tc = 1; tc <= 10; tc++) {
			
			// 정답 출력 양식 맞추기
			sb.append("#").append(tc).append(" ");
			
			// 첫 번째 줄: 암호문의 길이 N
			int N = Integer.parseInt(input.nextLine());
			
			
			// 두 번째 줄: 원본 암호문
			st = new StringTokenizer(input.nextLine());
			
			// 암호문을 나눠서 리스트에 입력
			while(st.hasMoreTokens()) nums.add(st.nextToken());
			
			
			// 세번째 줄: 명령어 개수
			int O = Integer.parseInt(input.nextLine());
			
			
			// 네번째 줄: 명령어
			st = new StringTokenizer(input.nextLine());
			
			// 명령어를 나눠서 큐에 입력
			while (st.hasMoreTokens()) queue.offer(st.nextToken());
			
			
			// 명령이 끝날 때까지
			while (!queue.isEmpty()) {
				
				// 명령어 받아오기
				String order = queue.poll();
				
				// 만약 명령이 I(삽입)이면
				if (order.equals("I")) {
					
					// index 저장
					int x = Integer.parseInt(queue.poll());
					
					// x 다음부터(0-index이므로 x index부터) y개의 숫자 삽입
					// 넣는 순서를 맞추기 위해 x의 index 계속 수정
					int y = Integer.parseInt(queue.poll());
					for (int num = 1; num <= y; num++) nums.add(x++, queue.poll());
				
				}
				
				// 만약 명령이 D(삭제)면
				else if (order.equals("D")) {
					
					// index 저장
					int x = Integer.parseInt(queue.poll());
					
					// 다음부터  y개의 숫자 삭제
					int y = Integer.parseInt(queue.poll());
					for (int num = 1; num <= y; num++) nums.remove(x);
					
				}
				
				// 만약 명령이 A(추가)면
				else { // if (order.equals("A"))
					
					// 맨 뒤에 y개 추가
					int y = Integer.parseInt(queue.poll());
					for (int num = 1; num <= y; num++) nums.add(queue.poll());
				}
				
			}
			
			// 첫 10개 출력
			for (int num = 0; num < 10; num++) sb.append(nums.get(num)).append(" ");
			sb.append("\n");
			
		}
		
		
		// 정답 출력
		System.out.println(sb.toString());
		
	}
}