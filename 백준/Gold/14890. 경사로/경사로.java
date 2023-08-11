// 경사로

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 배열의 크기
	static int n;

	// 경사로의 길이
	static int l;

	// 전체 map
	static int[][] map;

	// 슬로프 검사
	static boolean checkSlopeRow(int sr) {
		// 경사로가 놓여졌는지 체크하는 배열
		boolean[] placed = new boolean[n];

		for (int c = 0; c < n - 1; c++) {
			// 차이
			int diff = map[sr][c] - map[sr][c + 1];

			// 차이가 없을 때까지는 그대로 진행
			if (diff == 0) {
				continue;
			}
			// 차이가 있으면 : +1칸(원래 칸이 더 높음)
			if (diff == 1) {

				// 앞으로 l칸에 대해 테스트
				for (int i = c + 1; i <= c + l; i++) {

					// 범위 넘어가면 종료
					if (i >= n)
						return false;

					// 높이가 달라지면 종료
					if (map[sr][i] != map[sr][c + 1])
						return false;

					// 이미 경사로가 놓인 곳이면 종료
					if (placed[i])
						return false;

					// 괜찮으면 경사로 놓기
					placed[i] = true;
				}
			}
			// 차이가 있다면 : -1칸(원래 칸이 더 낮음)
			else if (diff == -1) {

				// 뒤로 l칸에 대해 테스트
				for (int i = c; i > c - l; i--) {

					// 범위 넘어가면 종료
					if (i < 0)
						return false;

					// 높이가 달라지면 종료
					if (map[sr][i] != map[sr][c])
						return false;

					// 이미 경사로가 놓인 곳이면 종료
					if (placed[i])
						return false;

					// 괜찮으면 경사로 놓기
					placed[i] = true;
				}
			} else {
				// 차이가 있다면 : 2칸 이상
				return false;
			}
		}

		// 다 통과하면 가능
		return true;
	}

	static boolean checkSlopeColumn(int sc) {
		// 경사로가 놓여졌는지 체크하는 배열
		boolean[] placed = new boolean[n];

		for (int r = 0; r < n - 1; r++) {
			// 차이
			int diff = map[r][sc] - map[r + 1][sc];

			// 차이가 없을 때까지는 그대로 진행
			if (diff == 0) {
				continue;
			}
			// 차이가 있으면 : +1칸(원래 칸이 더 높음)
			if (diff == 1) {

				// 앞으로 l칸에 대해 테스트
				for (int i = r + 1; i <= r + l; i++) {

					// 범위 넘어가면 종료
					if (i >= n)
						return false;

					// 높이가 달라지면 종료
					if (map[i][sc] != map[r + 1][sc])
						return false;

					// 이미 경사로가 놓인 곳이면 종료
					if (placed[i])
						return false;

					// 괜찮으면 경사로 놓기
					placed[i] = true;
				}
			}
			// 차이가 있다면 : -1칸(원래 칸이 더 낮음)
			else if (diff == -1) {

				// 뒤로 l칸에 대해 테스트
				for (int i = r; i > r - l; i--) {

					// 범위 넘어가면 종료
					if (i < 0)
						return false;

					// 높이가 달라지면 종료
					if (map[i][sc] != map[r][sc])
						return false;

					// 이미 경사로가 놓인 곳이면 종료
					if (placed[i])
						return false;

					// 괜찮으면 경사로 놓기
					placed[i] = true;
				}
			} else {
				// 차이가 있다면 : 2칸 이상
				return false;
			}
		}

		// 다 통과하면 가능
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 배열의 크기, 경사로의 길이
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		// 배열 선언
		map = new int[n][n];

		// 값 입력
		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 가로로 n칸에 대해 검사
		int answer = 0;
		for (int r = 0; r < n; r++) {
			if (checkSlopeRow(r))
				answer++;
		}
		// 세로로 n칸에 대해 검사
		for (int c = 0; c < n; c++) {
			if (checkSlopeColumn(c))
				answer++;
		}

		// 정답 출력
		System.out.println(answer);
	}
}