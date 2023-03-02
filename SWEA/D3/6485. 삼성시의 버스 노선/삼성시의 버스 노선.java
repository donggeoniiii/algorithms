// 삼성시의 버스 노선

import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			sb.append("#").append(tc).append(" ");
			
			// 버스 노선의 수
			int N = input.nextInt();
			
			// 버스 노선 정보를 관리할 배열 생성
			int[][] bus = new int[N][2];

			// 버스 운행정보 입력
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 2; j++)
				bus[i][j] = input.nextInt();
				
			}
			
			// 버스정류장 개수
			int P = input.nextInt();
			
			// 정류장별 버스 방문여부 탐색
			for (int i = 0; i < P; i++) {
				
				// 정류장 이름 입력
				int station = input.nextInt();
				
				// 해당 정류장을 지나는 버스의 수
				int busCnt = 0;
				
				// 버스노선 별로 돌면서
				for (int j = 0; j < N; j++) {
					
					// 만약 이 노선이 해당 정류장을 지난다면 
					if (station >= bus[j][0] && station <= bus[j][1]) {
						
						// 해당 버스정류장을 지나는 노선의 수 +1
						busCnt++;
					}
				}
				
				// 정답에 추가
				sb.append(busCnt).append(" ");
				
			}

			sb.append("\n");
		}

		// 정답 출력
		System.out.println(sb.toString());
	}
}