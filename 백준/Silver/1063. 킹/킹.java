// 킹

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력받은 값 바탕으로 좌표로 바꿔서 저장하기
		char[] king = st.nextToken().toCharArray();
		char[] enemy = st.nextToken().toCharArray();

		int kr = 7 - (king[1] - '1'); // 줄 시작은 1, index 시작은 0이므로
		int kc = king[0] - 'A';
		int er = 7 - (enemy[1] - '1');
		int ec = enemy[0] - 'A';

		int n = Integer.parseInt(st.nextToken()); // 이동 횟수

		// 킹의 이동 순서
		int[] moves = new int[n];
		for (int i = 0; i < n; i++) {
			String next = br.readLine();

			int ndt = -1;
			switch (next) {
				case "R":
					ndt = 0;
					break;
				case "L":
					ndt = 1;
					break;
				case "B":
					ndt = 2;
					break;
				case "T":
					ndt = 3;
					break;
				case "RT":
					ndt = 4;
					break;
				case "LT":
					ndt = 5;
					break;
				case "RB":
					ndt = 6;
					break;
				case "LB":
					ndt = 7;
					break;
			}
			moves[i] = ndt;
		}

		// 이동 방향
		int[] dr = {0, 0, 1, -1, -1, -1, 1, 1};
		int[] dc = {1, -1, 0, 0, 1, -1, 1, -1};

		for (int dt : moves) {
			// 예상 이동 방향
			int nr = kr + dr[dt];
			int nc = kc + dc[dt];

			// 밖으로 벗어나게 되면 스킵
			if (outOfIndex(nr, nc)) continue;

			// 그 방향에 돌이 있는 경우
			if (nr == er && nc == ec) {
				// 돌의 다음 위치
				int ner = er + dr[dt];
				int nec = ec + dc[dt];

				// 돌이 밖으로 벗어나게 되면 스킵
				if (outOfIndex(ner, nec)) continue;

				// 안 나가면 이동
				er = ner;
				ec = nec;
			}

			// 문제 없으면 킹도 이동
			kr = nr;
			kc = nc;
		}

		// 위치 변환 후 출력
		StringBuilder answer = new StringBuilder();
		answer.append((char) (kc + 'A')).append(7 - kr + 1)
			.append(" ").append((char) (ec + 'A')).append(7 - er + 1);

		System.out.println(answer);
	}

	private static boolean outOfIndex(int r, int c) {
		return r < 0 || c < 0 || r >= 8 || c >= 8;
	}
}