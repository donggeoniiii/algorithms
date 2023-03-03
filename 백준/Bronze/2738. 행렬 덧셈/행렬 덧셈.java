// 행렬덧셈

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 행렬의 크기
		int N = input.nextInt();
		int M = input.nextInt();
		
		// 행렬 1
		int[][] mat1 = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				mat1[r][c] = input.nextInt();
			}
		}
		
		// 행렬 2 구하면서 1과 더하고 출력
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				int num = input.nextInt() + mat1[r][c];
				sb.append(num).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}