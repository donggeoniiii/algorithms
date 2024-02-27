// 멀티버스 II

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 우주의 개수, 우주별 행성의 개수
	static int m, n;

	// 행성의 크기
	static int[][] planets;

	// 정렬된 배열
	static int[][] sortedPlanets;

	// 이분탐색을 위한, 중복이 제거된 정렬배열
	static List<Integer>[] uniquePlanets;

	// 압축된 배열
	static int[][] compressedPlanets;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		planets = new int[m][n];
		sortedPlanets = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int curPlanet = Integer.parseInt(st.nextToken());
				planets[i][j] = curPlanet;
				sortedPlanets[i][j] = curPlanet;
			}
			Arrays.sort(sortedPlanets[i]);
		}

		uniquePlanets = new ArrayList[m];

		compressedPlanets = new int[m][n];
	}

	private static void solution() {
		// 크기 순서대로 압축하기 위해 중복이 제거된 배열 필요
		for (int i = 0; i < m; i++) {
			uniquePlanets[i] = new ArrayList<>();

			// 중복 제거
			for (int j = 0; j < n; j++) {
				if (j == 0 || sortedPlanets[i][j-1] != sortedPlanets[i][j]) {
					uniquePlanets[i].add(sortedPlanets[i][j]);
				}
			}
		}

		// 이분탐색을 통해 압축
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				compressedPlanets[i][j] = bSearch(i, planets[i][j], 0, uniquePlanets[i].size() - 1);
			}
		}

		// 두 배열이 같으면 카운트 증가
		int answer = 0;
		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				if (Arrays.equals(compressedPlanets[i], compressedPlanets[j])) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}

	private static int bSearch(int planetIdx, int target, int start, int end) {
		int mdx = (start + end) / 2;
		int mid = uniquePlanets[planetIdx].get(mdx);

		if (mid == target) {
			return mdx;
		}
		else if (mid > target) {
			return bSearch(planetIdx, target, start, mdx - 1);
		}
		else {
			return bSearch(planetIdx, target, mdx + 1, end);
		}
	}
}