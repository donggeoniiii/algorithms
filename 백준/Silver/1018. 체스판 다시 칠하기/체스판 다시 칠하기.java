// 체스판 다시 칠하기

import java.util.Scanner;

public class Main {
	// 행의 갯수 N
	static int N;

	// 열의 갯수 M
	static int M;

	// N*M 크기의 체스판
	static char[][] chess;

	// 8*8 크기의 비교용 체스판 2개
	static char[][] chessWhite8;
	static char[][] chessBlack8;

	// 백트래킹을 위한 최솟값 저장 변수
	static int globalMinCnt;

	// 배열 바꾸고 비교하는 알고리즘
	static int switchAndCountChangeSpot(char[][] originalBoard, char[][] board8, int sr, int sc) {
		// 바꿔야 하는 좌표 계산
		int changeSpotCnt = 0;
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (originalBoard[r + sr][c + sc] != board8[r][c])
					changeSpotCnt++;
			}
		}
		// 결과 반환
		return changeSpotCnt;
	}

	// 백트래킹 알고리즘
	static void findMinimumChangePoint(int cnt) {
		// pruning: 0인 경우 찾으면 종료
		if (globalMinCnt == 0)
			return;

		// base case: cnt == (M-8+1) * (N-8+1)이면 종료
		if (cnt == (M - 8 + 1) * (N - 8 + 1))
			return;

		// recursive case
		// 시작하는 좌표 정하기
		int cr = cnt / (M - 8 + 1);
		int cc = cnt % (M - 8 + 1);

		// 바꿔야 하는 블록 수 계산
		int whiteCnt = switchAndCountChangeSpot(chess, chessWhite8, cr, cc);
		int blackCnt = switchAndCountChangeSpot(chess, chessBlack8, cr, cc);

		// 둘중 최솟값
		int localMinCnt = Math.min(whiteCnt, blackCnt);

		// 최솟값 갱신
		globalMinCnt = Math.min(globalMinCnt, localMinCnt);

		// 다음 위치로 이동
		findMinimumChangePoint(cnt + 1);
	}

	// 체스판 채우기 (귀찮아서..)
	static void paintBlocks(char[][] whiteBoard, char[][] blackBoard) {
		for (int r = 0; r < whiteBoard.length; r++) {
			for (int c = 0; c < whiteBoard[0].length; c++) {
				whiteBoard[r][c] = ((r + c) % 2 == 0) ? 'W' : 'B';
			}
		}
		for (int r = 0; r < blackBoard.length; r++) {
			for (int c = 0; c < blackBoard[0].length; c++) {
				blackBoard[r][c] = ((r + c) % 2 == 0) ? 'B' : 'W';
			}
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 행의 개수 N
		N = input.nextInt();

		// 열의 개수 M
		M = input.nextInt();

		// 체스판 생성
		chess = new char[N][M];

		// 체스판 입력
		for (int r = 0; r < N; r++) {
			String line = input.next();
			for (int c = 0; c < M; c++) {
				chess[r][c] = line.charAt(c);
			}
		}

		// 비교를 위한 체스판 생성
		chessBlack8 = new char[8][8];
		chessWhite8 = new char[8][8];

		// 체스판에 정보 입력
		paintBlocks(chessWhite8, chessBlack8);

		// 최솟값 초기화
		globalMinCnt = N * M + 1;

		// 백트래킹으로 최소인 경우 찾기
		findMinimumChangePoint(0);

		// 정답 출력
		System.out.println(globalMinCnt);
	}
}