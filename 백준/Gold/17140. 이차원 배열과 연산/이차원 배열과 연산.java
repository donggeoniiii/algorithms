// 이차원 배열과 연산

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// (r, c)에 있는 값이 k가 되기 위한 최소 시간
	private static int r, c, k;

	// 이차원 배열
	private static int[][] num;

	// 배열의 가로 세로 크기
	private static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// 배열 크기는 최대로 만들어놓기
		num = new int[101][101];

		// 초기 배열의 크기는 3x3 1-indexed
		n = 3;
		m = 3;
		for (int r = 1; r <= n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= m; c++) {
				num[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		// print("초기 입력: ");

		// 100초 안에 (r, c)가 k가 되는지 확인
		System.out.println(solve());
	}

	private static int solve() {
		for (int time = 0; time <= 100; time++) {
			// 원하는 지점의 값이 k가 되면 종료
			if (num[r][c] == k) {
				return time;
			}

			boolean flag = false;

			// print("수행 전: " + time);

			// 열 길이가 더 길어서 c연산을 해야 되면 뒤집어서 r연산으로 통일
			if (m > n) {
				transpose();
				flag = true;
				// print("뒤집은 후: ");
			}

			// 행별로 개미배열로 정렬
			for (int r = 1; r <= n; r++) {
				antSort(r);
			}

			// print("정렬 후");

			// 아까 뒤집었으면 다시 원위치
			if (flag) {
				transpose();
			//	print("뒤집은 후");
			}

		}

		// 100초 안에 안 끝나면 종료
		return -1;
	}

	private static void antSort(int cr) {
		// 숫자별로 등장 횟수 세기
		int[] numCnt = new int[101];
		for (int c = 1; c <= m; c++) {
			numCnt[num[cr][c]]++;
		}

		// 숫자와 숫자별 등장 횟수를 배열로 만들어 저장
		List<int[]> ant = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			if (numCnt[i] == 0) continue;
			ant.add(new int[] {i, numCnt[i]});
		}

		// 빈도순으로 먼저 정렬하고, 같으면 크기 순
		ant.sort(Comparator.comparingInt((int[] o) -> o[1]).thenComparingInt(o -> o[0]));

		// 원래 배열에 덮어쓰기
		int idx = 0;
		for (int[] a : ant) {
			// index가 100을 넘어가면 버림
			if (idx >= 100) break;

			num[cr][++idx] = a[0];
			num[cr][++idx] = a[1];
		}

		// 열의 길이 최대값 갱신했는지 확인
		m = Math.max(m, idx);

		// 남은 부분 0 처리
		while (idx < 100) {
			num[cr][++idx] = 0;
		}
	}

	private static void transpose() {
		int l = Math.max(m, n); // long side
		for (int r = 1; r <= l; r++) {
			for (int c = r + 1; c <= l; c++) {
				int temp = num[r][c];
				num[r][c] = num[c][r];
				num[c][r] = temp;
			}
		}

		// 가로 세로 index 크기 변경
		int temp = m;
		m = n;
		n = temp;
	}

	private static void print(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append(msg).append("\n");
		for (int r = 0; r <= n; r++) {
			for (int c = 0; c <= m; c++) {
				sb.append(num[r][c]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}