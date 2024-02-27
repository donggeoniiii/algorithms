// 차집합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	// 두 집합 원소의 개수
	static int a, b;

	// 두 집합
	static int[] union1, union2;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		union1 = new int[a];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < a; i++) {
			union1[i] = Integer.parseInt(st.nextToken());
		}

		union2 = new int[b];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < b; i++) {
			union2[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void solution() {
		// 차집합 원소의 개수
		int count = 0;

		// 이분 탐색을 위한 정렬
		Arrays.sort(union2);

		// 차집합
		ArrayList<Integer> answer = new ArrayList<>();

		for (int i = 0; i < a; i++) {
			int cur = union1[i];
			if (!isDuplicate(cur)) {
				count++;
				answer.add(cur);
			}
		}

		// 출력을 위한 정렬
		Collections.sort(answer);

		StringBuilder sb = new StringBuilder();
		sb.append(count).append("\n");
		for (int elm : answer) {
			sb.append(elm).append(" ");
		}
		System.out.println(sb);
	}

	private static boolean isDuplicate(int target) {
		int start = 0;
		int end = b - 1;

		while (start <= end) {
			int mdx = (start + end) / 2;
			int mid = union2[mdx];

			if (mid == target) {
				return true;
			}
			else if (mid > target) {
				end = mdx - 1;
			}
			else {
				start = mdx + 1;
			}
		}
		return false;
	}
}