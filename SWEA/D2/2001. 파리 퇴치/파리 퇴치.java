// 파리잡기

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			// N, M 입력
			int N = input.nextInt();
			int M = input.nextInt();
			
			// 파리가 몇 마리 있는지 적혀있는 2차원 배열 flies
			int[][] flies = new int[N][N];
			
			// 배열에 입력
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					flies[r][c] = input.nextInt();
				}
			}
			
			// 최댓값 구하기
			int max = 0;
			
			// 파리채로 잡은 파리 수 구하기
			// 배열의 각 지점에서 시작하는 M*M 배열이 있다고 생각한다
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 구간 별로 죽은 파리수 구하기   
					int deadFlies = 0;
					/*
					 * MxM 크기의 파리채로 내려치기
					 */
					for (int dr = 0; dr < M; dr++) {
						for (int dc = 0; dc < M; dc++) {
							int nr = r + dr;
							int nc = c + dc;
							if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
							deadFlies += flies[nr][nc];
						}
					}
					if (max < deadFlies) max = deadFlies;
				}
			}
			
			System.out.printf("#%d %d\n", tc, max);
		}
		
	}
}