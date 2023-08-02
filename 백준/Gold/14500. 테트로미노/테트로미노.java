// 테트로미노

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 가로 세로 길이
	static int N;
	static int M;

	// 대볼 배열
	static int[][] map;

	// 테트로미노들의 배열
	static int[][] tetro1 = {{1, 1, 1, 1}};
	static int[][] tetro2 = {{1}, {1}, {1}, {1}};
	static int[][] tetro3 = {{1, 1}, {1, 1}};
	static int[][] tetro4 = {{1, 0}, {1, 0}, {1, 1}};
	static int[][] tetro5 = {{1, 1, 1}, {1, 0, 0}};
	static int[][] tetro6 = {{1, 1}, {0, 1}, {0, 1}};
	static int[][] tetro7 = {{0, 0, 1}, {1, 1, 1}};
	static int[][] tetro8 = {{0, 1}, {0, 1}, {1, 1}};
	static int[][] tetro9 = {{1, 1}, {1, 0}, {1, 0}};
	static int[][] tetro10 = {{1, 1, 1}, {0, 0, 1}};
	static int[][] tetro11 = {{1, 0, 0}, {1, 1, 1}};
	static int[][] tetro12 = {{1, 0}, {1, 1}, {0, 1}};
	static int[][] tetro13 = {{0, 1}, {1, 1}, {1, 0}};
	static int[][] tetro14 = {{1, 1, 0}, {0, 1, 1}};
	static int[][] tetro15 = {{0, 1, 1}, {1, 1, 0}};
	static int[][] tetro16 = {{1, 1, 1}, {0, 1, 0}};
	static int[][] tetro17 = {{0, 1, 0}, {1, 1, 1}};
	static int[][] tetro18 = {{1, 0}, {1, 1}, {1, 0}};
	static int[][] tetro19 = {{0, 1}, {1, 1}, {0, 1}};

	// 최고 점수
	static int highestScore;

	// // 돌리고 뒤집기 메소드
	// static int[][] turnAndFlipArray(int[][] original, int method) {
	// 	// 반환할 배열
	// 	int[][] newArr = null;
	//
	// 	int n = original.length;
	// 	int m = original[0].length;
	//
	// 	// 메소드에 따라 구분
	// 	switch (method) {
	// 		case 1: // 90도 돌리기
	// 			newArr = new int[m][n];
	// 			for (int r = 0; r < m; r++) {
	// 				for (int c = 0; c < n; c++) {
	// 					newArr[r][c] = original[(n - 1) - c][r];
	// 				}
	// 			}
	// 			break;
	//
	// 		case 2: // 180도 돌리기
	// 			newArr = new int[n][m];
	// 			for (int r = 0; r < n; r++) {
	// 				for (int c = 0; c < m; c++) {
	// 					newArr[r][c] = original[(n - 1) - r][(m - 1) - c];
	// 				}
	// 			}
	// 			break;
	//
	// 		case 3: // 270도 돌리기
	// 			newArr = new int[m][n];
	// 			for (int r = 0; r < m; r++) {
	// 				for (int c = 0; c < n; c++) {
	// 					newArr[r][c] = original[c][(m - 1) - r];
	// 				}
	// 			}
	// 			break;
	//
	// 		case 4: // 가로 기준 뒤집기
	// 			newArr = new int[n][m];
	// 			for (int r = 0; r < n; r++) {
	// 				for (int c = 0; c < m; c++) {
	// 					newArr[r][c] = original[r][(m - 1) - c];
	// 				}
	// 			}
	//
	// 			break;
	// 		case 5: // 세로 기준 뒤집기
	// 			newArr = new int[n][m];
	// 			for (int r = 0; r < n; r++) {
	// 				for (int c = 0; c < m; c++) {
	// 					newArr[r][c] = original[(n - 1) - r][c];
	// 				}
	// 			}
	//
	// 			break;
	// 		default: // 안 돌리기
	// 			newArr = original;
	// 			break;
	// 	}
	// 	return newArr;
	// }

	// 비교해보는 메소드
	static void findHighestScore(int[][] tetromino) {

		// 칸에 맞게 돌면서 보기
		for (int cr = 0; cr < N - tetromino.length + 1; cr++) {
			for (int cc = 0; cc < M - tetromino[0].length + 1; cc++) {

				// 점수 저장하는 int 변수
				int curScore = 0;
				for (int r = 0; r < tetromino.length; r++) {
					for (int c = 0; c < tetromino[0].length; c++) {
						// 테트로미노가 있는 칸이면 점수 합산
						if (tetromino[r][c] == 1)
							curScore += map[r + cr][c + cc];
					}
				}

				// 최댓값 갱신
				highestScore = Math.max(curScore, highestScore);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 가로 세로 길이
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 원래 배열 선언
		map = new int[N][M];

		// 값 입력
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 최댓값 초기화
		highestScore = 0;

		// 돌리면서 최솟값 찾기
		findHighestScore(tetro1);
		findHighestScore(tetro2);
		findHighestScore(tetro3);
		findHighestScore(tetro4);
		findHighestScore(tetro5);
		findHighestScore(tetro6);
		findHighestScore(tetro7);
		findHighestScore(tetro8);
		findHighestScore(tetro9);
		findHighestScore(tetro10);
		findHighestScore(tetro11);
		findHighestScore(tetro12);
		findHighestScore(tetro13);
		findHighestScore(tetro14);
		findHighestScore(tetro15);
		findHighestScore(tetro16);
		findHighestScore(tetro17);
		findHighestScore(tetro18);
		findHighestScore(tetro19);

		// 출력
		System.out.println(highestScore);
	}
}