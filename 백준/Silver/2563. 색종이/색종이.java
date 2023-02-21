// 색종이

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	
		// 도화지 배열
		// 처음에는 false으로 표시
		boolean[][] map = new boolean[100][100];
		
		// 색종이 수
		int N = input.nextInt();
		
		// 색종이가 덮은 부분 카운트
		int paperCnt = 0;
	
		// 색종이 별 좌표 입력
		for (int paper = 1; paper <= N; paper++) {
			// 도화지와 색종이의 왼쪽변 사이의 거리
			int col = input.nextInt();
			
			// 도화지와 색종이의 아래변 사이의 거리
			int row = input.nextInt();
			
			// 색종이 덮기
			for (int r = row; r < row+10; r++) {
				for (int c = col; c < col+10; c++) {
					// 이미 색종이로 덮어진 부분은 넘어가자
					if (map[r][c] == true) continue;
					
					// 색종이가 닿는 부분은 true로 표시
					map[r][c] = true;
					
					// 색칠된 부분 카운트
					paperCnt++;
				}
			}
		}
		
		// 정답 출력
		System.out.println(paperCnt);
		
		
		
	}
}