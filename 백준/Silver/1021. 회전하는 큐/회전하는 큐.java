// 회전하는 큐


import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
/*
 * 한쪽으로만 돌려봐도 된다!!
 * cnt로 한쪽으로 index에 있는 값이 나올 때까지 몇번 돌리는지 세면, 반대편은 N-cnt가 된다!
 * 둘의 값을 비교해서 작은 값을 min에 넣으면 최솟값이 된다.
 */

public class Main {
	public static void main(String[] args) {
		// 양방향 순환 큐: deque
		// 1. pollFirst
		// 2. offerLast(pollFirst)
		// 3. offerFirst(pollLast)
		
		Scanner input = new Scanner(System.in);
		Deque<Integer> deque = new LinkedList<>();
		
		int N = input.nextInt();
		int M = input.nextInt();
		
		// 초기 index값을 덱에 넣는 원소의 값으로 기억
		for (int num = 1; num <= N; num++) {
			deque.offer(num);
		}
		
		// 뽑을 M개의 값을 순서대로 저장하는 배열
		int[] nums = new int[M];
		
		// 뽑을 값 저장
		for (int i = 0; i < M; i++) {
			nums[i] = input.nextInt();
		}
		
		// 2 or 3번 메소드의 최소수행횟수 
		int min = 0;
		
		// 순서대로 num 찾기
		for (int num : nums) {
			// head에 있는 원소를 tail로 이동시키는 2번 메소드 횟수 cnt
			// 3번 메소드 횟수: 현재 N-cnt
			int cnt = 0;
			
			while (deque.peekFirst() != num) {
				// head가 아니면 2번 메소드 반복
				deque.offerLast(deque.pollFirst());
				// 카운트 증가
				cnt++;
				
				// head에 도달하면 while문 탈출
				if (deque.peekFirst() == num) {
					break;	
				}
			}
			// peekFirst == num이면 pollFirst
			deque.pollFirst();
			
			// cnt와 N-cnt 중에 더 작은 값 저장
			int minCnt = (cnt < N-cnt) ? cnt : N-cnt;
			// min에 합산
			min += minCnt;
			// 수가 하나 빠졌으니 크기 1 감소
			N--;
		}
		
		// 정답 출력
		System.out.println(min);
		
	}
}
