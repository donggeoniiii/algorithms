 // 러시아 국기같은 깃발

import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 행별로 색이 몇개 있는지 카운트
 * 그후 최소 한줄~N-2줄로 각 색의 비율을 옮겨가면서 색칠하는 칸수 구하기
 * 각 경우마다 최대값과 비교해 갱신
 */

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		// 테스트케이스 개수
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(input.nextLine());
			// 깃발의 크기 입력
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// 행별로 색 수를 기억하는 배열
			// 0: 흰색, 1: 파란색, 2: 빨간색
			int[][] colorCnt = new int[N][3];
			
			// 행별로 색을 저장
			for (int r = 0; r < N; r++) {
				
				// 들어오는 입력 중에서 
				for (char c : input.nextLine().toCharArray()) {
			
					// 만약 흰색이면 0, 파란색이면 1, 빨간색이면 2에 저장
					if (c == 'W') {
						colorCnt[r][0]++;
					}
					else if (c == 'B') {
						colorCnt[r][1]++;
					}
					else { // if c == 'R'
						colorCnt[r][2]++; 
					}
				}
			}
			
			// 색칠하는 최소횟수를 저장하는 변수, 반복문을 돌면서 갱신할 수 있도록 가장 큰 int값으로 초기화
			int paintMin = Integer.MAX_VALUE;
			
			// 색칠 기준선을 옮기면서 최솟값 찾기
			// 흰색은 row 0 ~ N-3, 파랑은 row (흰색+1) ~ N-2, 빨강은 row (파랑+1) ~ N-1까지 가능
			for (int white = 0; white < N-2; white++) {
				for (int blue = white+1; blue < N-1; blue++) {
					
					// 해당 시행의 색칠 횟수를 담는 변수
					int temp = 0;
					
					// 각 행마다 해당하는 색으로 색칠, 그 행의 다른 색깔 칸만큼 칠하면 됨
					for (int r = 0; r < N; r++) {
						
						if (r <= white) {
							temp += colorCnt[r][1] + colorCnt[r][2];
						}
						else if (r > white && r <= blue) {
							temp += colorCnt[r][0] + colorCnt[r][2];
						}
						else { // r > blue && r < N
							temp += colorCnt[r][0] + colorCnt[r][1]; 
						}
						
					}
					
					// 만약 해당 시행에서 구한 값이 현재 최솟값보다 작다면 최솟값 갱신
					if (temp < paintMin) paintMin = temp;
					
				}
			}
			
			// 정답 출력
			System.out.printf("#%d %d\n", tc, paintMin);
			
		}
	}
}

