// 미로 만들기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		br.readLine(); // 홍준이 적은 내용의 길이, 사용 안함

		char[] move = br.readLine().toCharArray();

		// 시작점을 가운데 지점으로, 상대적 위치 저장
		boolean[][] visited = new boolean[101][101];

		int cr = 50;
		int cc = 50;

		visited[cr][cc] = true;

		// 가장 음의 방향으로 이동한 경우
		int minR = cr;
		int minC = cc;

		// 가장 양의 방향으로 이동한 경우
		int maxR = cr;
		int maxC = cc;

		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, -1, 0, 1};
		int dt = 0; // 시작할 땐 남쪽 보는 중

		for (char c : move) {
			switch (c) {
				case 'F':
					cr += dr[dt];
					cc += dc[dt];
					visited[cr][cc] = true;
					
					minR = Math.min(minR, cr);
					minC = Math.min(minC, cc);
					maxR = Math.max(maxR, cr);
					maxC = Math.max(maxC, cc);
					break;
				case 'L':
					dt -= 1;
					if (dt == -1) dt = 3;
					break;
				case 'R':
					dt = (dt + 1) % 4;
					break;
			}
		}

		// 이동 범위 내에서 출력
		StringBuilder answer = new StringBuilder();
		for (int i = minR; i <= maxR; i++) {
			for (int j = minC; j <= maxC; j++) {
				if (visited[i][j]) answer.append(".");
				else answer.append("#");
			}
			answer.append('\n');
		}

		System.out.println(answer);
	}
}