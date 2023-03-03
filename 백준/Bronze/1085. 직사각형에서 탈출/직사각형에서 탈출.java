// 직사각형에서 탈출

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 경계선까지 최소거리
		int length = 0;
		
		// 한수의 위치
		int x = input.nextInt();
		int y = input.nextInt();
		
		// 직사각형의 오른쪽 위 꼭짓점
		int w = input.nextInt();
		int h = input.nextInt();
		
		// 어디에 제일 먼저 닿는지 알기 위해 델타 탐색 진행	
		// 델타 배열
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		boolean terminated = false;
		
		// 델타 탐색
		while (!terminated) {
			for (int dt = 0; dt < 4; dt++) {
				int nx = x + length * dx[dt];
				int ny = y + length * dy[dt];
				
				// 만약 각 경계선에 닿으면 종료조건 성립
				if (nx == 0 || ny == 0 || nx == w || ny == h) terminated = true;
				
			}

			// 탈출
			if (terminated) break;
			
			// 길이 증가
			length++;
			
		}
		
		// 정답 출력
		System.out.println(length);
		
	
	}
}