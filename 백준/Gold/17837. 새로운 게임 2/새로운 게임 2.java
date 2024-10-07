// 새로운 게임 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static int n, k;
	private static int[][] chessboard;
	private static int[][] horses;
	private static Deque<Integer>[][] horseDeque;

	private static final int[] dr = {0, 0, 0, -1, 1};
	private static final int[] dc = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		chessboard = new int[n+1][n+1];
		for (int r = 1; r <= n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= n; c++) {
				chessboard[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 체스판 현황을 보여줄 horseDeque 초기화
		horseDeque = new Deque[n+1][n+1];
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				horseDeque[r][c] = new ArrayDeque<>();
			}
		}

		// 말의 이동 정보
		horses = new int[k+1][3];
		for (int i = 1; i <= k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			horses[i][0] = r;
			horses[i][1] = c;
			horses[i][2] = dir;

			horseDeque[r][c].add(i);
		}

		// showChessboard(0);

		int turn = 1;
		while (turn <= 1000) {
			// 말 이동
			moveHorses();

			// showChessboard(turn);

			if (gameOver()) {
				System.out.println(turn);
				return;
			}

			// 다음 턴 진행
			turn++;
		}

		System.out.println(-1);
	} // main

	private static void showChessboard(int turn) {
		StringBuilder sb = new StringBuilder();
		sb.append("turn: ").append(turn).append("\n");
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				sb.append(horseDeque[r][c].size()).append(" ");
			}
			sb.append("\n");
		}

		for (int i = 1; i <= k; i++) {
			sb.append(i).append(": ").append(Arrays.toString(horses[i])).append("\n");
		}
		System.out.println(sb);

	} // showChessboard()

	private static void moveHorses() {
		for (int i = 1; i <= k; i++) {
			int cr = horses[i][0];
			int cc = horses[i][1];
			int dir = horses[i][2];

			int nr = cr + dr[dir];
			int nc = cc + dc[dir];

			// 밖으로 나가는 경우에는 파란색 땅을 밞은 거랑 똑같음
			if (outOfIndex(nr, nc) || chessboard[nr][nc] == 2) {
				moveBlue(i);
			} else if (chessboard[nr][nc] == 0) {
				moveWhite(i);
			} else {
				moveRed(i);
			}

			// 종료조건 체크
			if (gameOver()) {
				return;
			}
		}
	} // moveHorses()

	private static void moveBlue(int horseIdx) {
		// 방향 전환
		switchDirection(horseIdx);
		
		int cr = horses[horseIdx][0];
		int cc = horses[horseIdx][1];
		int dir = horses[horseIdx][2];

		int nr = cr + dr[dir];
		int nc = cc + dc[dir];

		if (outOfIndex(nr, nc) || chessboard[nr][nc] == 2) return;

		if (chessboard[nr][nc] == 0) moveWhite(horseIdx);
		else moveRed(horseIdx);
	} // moveBlue()

	private static void moveWhite(int horseIdx) {
		int cr = horses[horseIdx][0];
		int cc = horses[horseIdx][1];
		int dir = horses[horseIdx][2];

		int nr = cr + dr[dir];
		int nc = cc + dc[dir];

		Deque<Integer> curDeque = horseDeque[cr][cc];
		Deque<Integer> nextDeque = horseDeque[nr][nc];
		Deque<Integer> temp = new ArrayDeque<>();

		while (!curDeque.isEmpty()) {
			int cur = curDeque.peekLast();

			if (cur == horseIdx) {
				temp.addFirst(curDeque.pollLast());
				break;
			}

			temp.addFirst(curDeque.pollLast());
		}

		nextDeque.addAll(temp);
		curDeque.removeAll(temp);

		// 좌표 업데이트
		for (int horse : temp) {
			horses[horse][0] = nr;
			horses[horse][1] = nc;
		}
	} // moveWhite()

	private static void moveRed(int horseIdx) {
		int cr = horses[horseIdx][0];
		int cc = horses[horseIdx][1];
		int dir = horses[horseIdx][2];

		int nr = cr + dr[dir];
		int nc = cc + dc[dir];

		Deque<Integer> curDeque = horseDeque[cr][cc];
		Deque<Integer> nextDeque = horseDeque[nr][nc];
		Deque<Integer> temp = new ArrayDeque<>();

		while (!curDeque.isEmpty()) {
			int cur = curDeque.peekLast();

			if (cur == horseIdx) {
				temp.add(curDeque.pollLast());
				break;
			}

			temp.add(curDeque.pollLast());
		}

		nextDeque.addAll(temp);
		curDeque.removeAll(temp);

		// 좌표 업데이트
		for (int horse : temp) {
			horses[horse][0] = nr;
			horses[horse][1] = nc;
		}
	} // moveRed()

	private static void switchDirection(int horseIdx) {
		int dir = horses[horseIdx][2];

		switch (dir) {
			case 1:
				horses[horseIdx][2] = 2;
				break;
			case 2:
				horses[horseIdx][2] = 1;
				break;
			case 3:
				horses[horseIdx][2] = 4;
				break;
			case 4:
				horses[horseIdx][2] = 3;
				break;
		}
	}

	private static boolean outOfIndex(int r, int c) {
		return r <= 0 || c <= 0 || r > n || c > n;
	}

	private static boolean gameOver() {
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				if (horseDeque[r][c].size() >= 4) return true;
			}
		}

		return false;
	}
}