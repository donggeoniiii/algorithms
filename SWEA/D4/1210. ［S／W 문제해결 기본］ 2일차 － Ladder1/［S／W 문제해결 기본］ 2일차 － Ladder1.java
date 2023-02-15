// Ladder1

import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	// 사다리 밖으로 나가는 지 확인하는 메소드
	static boolean inLadder(int col) {
		return col >= 0 && col < 100;
	}
	
	// 좌우에 사다리가 있는지 확인하는 메소드
	static boolean ableToMove(int[][] arr, int r, int c) {
		return inLadder(c) && arr[r][c] == 1;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			// 테스트케이스 번호 입력
			String T = input.nextLine();
			
			// 테스트케이스 입력
			int[][] ladder = new int[100][100];
			for (int r = 0; r < 100; r++) {
				st = new StringTokenizer(input.nextLine());
				for (int c = 0; c < 100; c++) {
					ladder[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// X 표시에 도달하는 index
			int answer = 0;
			
			// 사다리타기 시작
			for (int c = 0; c < 100; c++) {
				// 좌표 탐색을 위한 변수 생성 
				int row = 0, col = c;
				
				// 시작점의 값이 0이면 시작할 필요 없음
				if (ladder[row][c] == 0) continue;
				
				// 끝에 도달하기 전까지 이동
				while (row < 99) {
					// 왼쪽으로 이동할 수 있는지 확인
					if (ableToMove(ladder, row, col-1)) {
						col--;
						// 왼쪽으로 이동할 수 있다면 index를 벗어나지 않으면서 아래로 사다리가 날 때까지 계속 왼쪽으로 이동
						while (ladder[row+1][col] != 1) {
							col--;
						}
						// 더 이상 이동할 수 없으면 밑으로 한 칸 가고 마무리
						row++;
					
					// 오른쪽으로 이동할 수 있는지 확인(사다리 특성상 왼쪽, 오른쪽이 동시에 가능할 수는 없다)
					} else if (ableToMove(ladder, row, col+1)) {
						col++;
						// 오른쪽으로 이동할 수 있다면 index를 벗어나지 않으면서 아래로 사다리가 날 때까지 계속 왼쪽으로 이동
						while (ladder[row+1][col] != 1) {
							col++;
						}
						// 더 이상 이동할 수 없으면 밑으로 한 칸 가고 마무리
						row++;
					} 
					
					// 가로로 이동할 수 없다면 면 아래로 한 칸 이동
					else row++;

					// 그렇게 도달한 마지막 행의 좌표값이 2(X)면 for문 탈출
					if (row == 99 && ladder[row][col] == 2) {
						answer = c;
						break;
					}
				}
			}
			
			// 정답 출력
			System.out.printf("#%s %d\n", T, answer);
			
		}
	}
}