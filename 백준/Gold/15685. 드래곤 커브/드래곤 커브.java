// 드래곤 커브

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	// 전체 배열
	static boolean[][] map;

	// 델타배열
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};

	static void drawDragonCurve(int x, int y, int dir, int gen) {

		// 방향을 저장할 arrayList
		ArrayList<Integer> arrayList = new ArrayList<>();

		// 시작점 드래곤커브 표시
		map[x][y] = true;

		// 시작방향 저장(0세대)
		arrayList.add(dir);

		// 세대를 거듭하면서
		for (int g = 1; g <= gen; g++) {
			// 이전 세대만큼 커브를 그려야 되니까
			for (int idx = arrayList.size() - 1; idx >= 0; idx--) {
				// 시계방향만큼 돌리기
				arrayList.add((arrayList.get(idx) + 1) % 4);
			}
		}

		// 전부 그렸으면 여기에 드래곤커브가 지나간다고 표시
		for (Integer integer : arrayList) {
			x += dx[integer];
			y += dy[integer];
			map[x][y] = true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 전체 배열
		map = new boolean[101][101];

		// 드래곤 커브 개수
		int n = Integer.parseInt(br.readLine());

		// 드래곤 커브 그리기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int gen = Integer.parseInt(st.nextToken());

			drawDragonCurve(x, y, dir, gen);
		}

		// 전 배열을 돌면서 드래곤커브 위에 있는 정사각형 갯수 세기
		int cnt = 0;

		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {

				// 만약 탐색하는 정사각형이 다 탐색이 가능하다면
				if (map[x][y] && map[x][y + 1] && map[x + 1][y] && map[x + 1][y + 1]) {
					// 카운트 증가
					cnt++;
				}
			}
		}

		// 정답 출력
		System.out.println(cnt);
	}
}