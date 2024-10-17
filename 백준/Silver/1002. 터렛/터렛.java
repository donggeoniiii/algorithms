// 터렛

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());

			// 만약 두 원이 겹치면 무한대이므로 -1
			if (x1 == x2 && y1 == y2 && r1 == r2) {
				sb.append(-1);
			} else {
				// (x1, y1), (x2, y2)에서 각각 r1, r2짜리 반지름의 원을 그린다고 가정
				double dist = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

				// 두 직원 사이 거리가 반지름 길이 합 또는 차와 같으면 1, 가까우면 2
				// 만약 반지름 길이 합보다 크거나 반지름 길이 차보다 작으면 0
				if (dist > r1 + r2 || dist < Math.abs(r1 - r2)) {
					sb.append(0);
				} else if (dist == r1 + r2 || dist == Math.abs(r1 - r2)) {
					sb.append(1);
				} else {
					sb.append(2);
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}