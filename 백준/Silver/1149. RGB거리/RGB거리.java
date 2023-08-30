// RGB거리

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 집의 수
		int n = Integer.parseInt(br.readLine());

		// 집별로 색칠하는 비용
		int[][] colorCost = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			colorCost[i][0] = Integer.parseInt(st.nextToken());
			colorCost[i][1] = Integer.parseInt(st.nextToken());
			colorCost[i][2] = Integer.parseInt(st.nextToken());
		}

		// 현재 집을 해당 색깔로 칠하는 비용: 그전 집을 해당 색이 아닌 다른 색으로 칠하는 2가지 방법 중 최솟값 + 해당 색으로 색칠 비용
		for (int i = 1; i <= n; i++) {
			colorCost[i][0] = colorCost[i][0] + Math.min(colorCost[i - 1][1], colorCost[i - 1][2]);
			colorCost[i][1] = colorCost[i][1] + Math.min(colorCost[i - 1][0], colorCost[i - 1][2]);
			colorCost[i][2] = colorCost[i][2] + Math.min(colorCost[i - 1][0], colorCost[i - 1][1]);
		}

		// 정답 출력
		System.out.println(Math.min(colorCost[n][0], Math.min(colorCost[n][1], colorCost[n][2])));
	}
}