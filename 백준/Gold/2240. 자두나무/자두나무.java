// 자두나무

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		int[] plum = new int[t+1];
		for (int i = 1; i <= t; i++) {
			plum[i] = Integer.parseInt(br.readLine());
		}

		// memo[t][w]: t초에 w번 움직여서 받을 수 있는 자두의 최대 개수
		int[][] memo = new int[t+1][w+1];


		// 초기화: 1번 나무로 떨어지면 안 움직여도 됨
		if (plum[1] == 1) {
			memo[1][0] = 1;
		} else {
			memo[1][1] = 1;
		}

		for (int time = 2; time <= t; time++) {
			int plumIdx = plum[time];

			// move == 0: 1번 나무 밑에 계속 있으므로 1번에 떨어지면 추가, 아니면 이전까지 받은 개수 그대로
			if (plumIdx == 1) {
				memo[time][0] = memo[time-1][0] + 1;
			}
			else {
				memo[time][0] = memo[time-1][0];
			}

			// move >= 1: 자두가 떨어지는 위치와 이전까지 받은 개수를 고려해 최대값 입력
			for (int move = 1; move <= w; move++) {
				if (plumIdx == 1){
					if (move % 2 == 0) {
						memo[time][move] = Math.max(memo[time-1][move] + 1, memo[time-1][move-1]);
					}
					else {
						memo[time][move] = Math.max(memo[time-1][move-1] + 1, memo[time-1][move]);
					}
				}
				if (plumIdx == 2) {
					if (move % 2 == 1) {
						memo[time][move] = Math.max(memo[time-1][move] + 1, memo[time-1][move-1]);
					}
					else {
						memo[time][move] = Math.max(memo[time-1][move-1] + 1, memo[time-1][move]);
					}
				}
			} // for-loop (move)
		} // for-loop (time)

		// 최대 개수 판별
		int answer = 0;
		for (int[] time : memo) {
			for (int move : time) {
				answer = Math.max(answer, move);
			}
		}

		System.out.println(answer);
	}
}