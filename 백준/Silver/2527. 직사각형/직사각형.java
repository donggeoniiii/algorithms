// 직사각형

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 왼쪽 아래부터 차례대로
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int q1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());

			// 가로나 세로가 아예 안 만나면 d
			if (p1 < x2 || q1 < y2 || p2 < x1 || q2 < y1) {
				sb.append("d");
			}
			// 한 점에서 만나면 c
			else if ((x1 == p2 && y1 == q2) || (x1 == p2 && q1 == y2) || (p1 == x2 && q1 == y2) || (p1 == x2
				&& y1 == q2)) {
				sb.append("c");
			}
			// 한 면에서 만나면 b
			else if (p1 == x2 || q1 == y2 || p2 == x1 || y1 == q2) {
				sb.append("b");
			}
			// 다 아니면 a
			else {
				sb.append("a");
			}
			sb.append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}