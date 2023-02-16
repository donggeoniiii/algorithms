// 달팽이 숫자

import java.util.Scanner;

/*
 * 달팽이 모양은 껍데기부터 떨어지는 양파껍질 같은 모양이라고 볼 수 있다.
 * 정사각형 한 변의 길이 -1만큼 한 방향으로 이동하고, 그 다음에 방향을 바꿔서 또 같은 길이만큼 이동하고를 4번 반복하고, size -2
 * size가 양수인 동안 while문을 진행한다.
 */
public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// 테스트케이스의 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			// 달팽이의 크기
			int N = input.nextInt();
			
			// 달팽이 배열 생성
			int[][] snail = new int[N][N];
			
			// 숫자는 1부터 시작해서 N*N까지 
			int num = 1;
			
			// 방향을 저장하는 델타배열, 시작점 설정
			int[][] dt = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
			int r = 0, c = 0; 
			
			// N부터 시작해 크기를 2씩 줄이면서(가로 세로로 두번씩 빠지니까) 진행
			while (N > 0) {
				// N이 홀수인 경우 마지막에 N이 1이면 하나만 입력하면 되므로
				if (N == 1) {
					snail[r][c] = num;
					break;
				}
				
				// 4방향으로 이동, 이동할때마다 숫자 입력하고 +1
				for (int d = 0; d < 4; d++) {
					// N보다 하나 작은 크기로 이동해야 딱 맞다!!!
					for (int i = 0; i < N-1; i++) {
						// 먼저 숫자를 입력하고, 해당 방향으로 이동한다.
						snail[r][c] = num++;
						
						// 만약 한바퀴를 다 돌았다면 안쪽으로 들어가야 하므로 col만 +1
						if (d == 3 && i == N-2) {
							c++;
						} else {
							r += dt[d][0];
							c += dt[d][1];
						}
						
					}
				}
				// 한 바퀴 입력했으면 size -2
				N -= 2;
			}
			
			// 달팽이 출력
			System.out.printf("#%d\n", tc);
			for (int[] innerArray : snail) {
				for (int element : innerArray) {
					System.out.printf("%d ", element);
				}
				System.out.println();
			}
			
		}
		
	}
}
