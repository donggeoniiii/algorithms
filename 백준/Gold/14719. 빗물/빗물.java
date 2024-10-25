// 빗물

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		int[][] map = new int[h][w];

		st = new StringTokenizer(br.readLine());
		for (int c = 0; c < w; c++) {
			int curHeight = Integer.parseInt(st.nextToken());

			for (int r = 1; r <= curHeight; r++) {
				map[h - r][c] = 1;
			}
		}

		// 맨 아래층에서 시작, 양 옆이 막혀있으면 카운트 합산
		int answer = 0;
		for (int r = h-1; r >= 0; r--) {
			// 해당 열에서 블럭을 만나면, 다음 블럭을 만날 때까지 빈칸의 수 카운트하기
			boolean isBlocked = false;
			int blockCount = 0;
			for (int c = 0; c < w; c++) {
				if (map[r][c] == 1) {
					if (isBlocked) {
						answer += blockCount;
					} else {
						isBlocked = true;
					}
					// 벽을 만났으면 그전까지 카운트는 다시 초기화
					blockCount = 0;
				} else {
					blockCount++;
				}
			}
		}

		System.out.println(answer);
	}
}