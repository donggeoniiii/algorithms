import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// 톱니바퀴

import java.util.Scanner;

public class Main {
	
	// 톱니바퀴별 배열
	// index 0 : 12시, index 2 : 3시, index 6: 9시
	static int[][] gears;
	
	// 톱니바퀴를 돌릴 deque
	static Deque<Integer> deque;
	
	// 돌리는 톱니바퀴와 방향 저장하는 큐
	static Queue<int[]> queue; 
	
	// 톱니바퀴별 회전 메소드
	static void rotate(int[] gear, int dir) {
		
		// 배열에서 값 빼와서 저장
		for (int idx = 0; idx < gear.length; idx++) {
			deque.offer(gear[idx]);
		}
		
		// 방향이 1이면 시계 방향, -1이면 반시계방향으로 한칸 이동
		if (dir == 1) 
			deque.offerFirst(deque.pollLast());
		else if (dir == -1) {
			deque.offerLast(deque.pollFirst());
		}
		
		// 다시 deque에서 빼서 차례대로 입력
		for (int idx = 0; idx < gear.length; idx++) {
			gear[idx] = deque.poll();
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 배열 생성
		gears = new int[5][8];
		
		// 자료구조 선언
		deque = new LinkedList<>();
		queue = new LinkedList<>();
		
		
		// 값 입력하기
		for (int gdx = 1; gdx <= 4; gdx++) {
			String line = input.next();
			for (int idx = 0; idx < 8; idx++) {
				gears[gdx][idx] = (int) (line.charAt(idx) - '0'); 
			}

		}
		
		
		// 회전 횟수
		int K = input.nextInt();
		
		// 회전시키기
		for (int turn = 1; turn <= K; turn++) {
			
			// 회전시킬 톱니바퀴
			int gear = input.nextInt();
			
			// 회전방향
			int dir = input.nextInt();
			
			// 각 톱니가 돌아가는 여부 저장
			boolean[] rotated = new boolean[5];
			
			// 일단 얘는 돌아가니까
			rotated[gear] = true; 
			
			// 시작점 저장
			queue.offer(new int[] {gear, dir});
			
			// 주변 애들 회전 방향 결정, 왼쪽부터
			// 마지막으로 돌아간 방향 저장하는 변수
			int last = dir;
			for (int i = gear-1; i >= 1; i--) {
				
				// 옆이 안 돌아갔으면 더 해볼 필요 없으니까 나가고
				if (!rotated[i+1])
					break;

				// 옆이 돌아가고, 오른쪽의 9시와 본인 3시와 값이 같으면
				if (gears[i+1][6] != gears[i][2]) {
					// 돌리는거 저장하고
					queue.offer(new int[] {i, -last});
					
					// 돌렸다고 표시하기
					rotated[i] = true;
					
					// 마지막으로 돌아간 방향 저장
					last = -last;
				}
				
			}
			
			// 오른쪽
			// 회전 기준 초기화
			last = dir;
			for (int i = gear+1; i <= 4; i++) {
				
				// 옆이 안 돌아갔으면 더 해볼 필요 없으니까 나가고
				if (!rotated[i-1])
					break;
				
				// 옆이 돌아가고, 오른쪽의 3시와 본인 9시와 값이 다르면
				if (gears[i-1][2] != gears[i][6]) {

					// 돌리는거 저장하고
					queue.offer(new int[] {i, -last});
					
					// 돌렸다고 표시하기
					rotated[i] = true; 
					
					// 마지막으로 돌아간 방향 저장
					last = -last;
				}
				
			}
			
			// 주변 다 확인했으면 회전시키기
			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				int curGear = cur[0];
				int curDir = cur[1];
				
				rotate(gears[curGear], curDir);
			
			}
		}
		
		// 점수 계산하기
		int score = 0;
		for (int idx = 1; idx <= 4; idx++) {
			
			if (gears[idx][0] == 1) {
				score += Math.pow(2, idx-1);
				
			}
			
		}
		
		// 정답 출력
		System.out.println(score);
		
		input.close();
	}
}