// 쉽게 푸는 문제

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 구간을 나타내는 두 수
	static int a, b;

	static int answer;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		answer();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		answer = 0;
	}

	private static void solution() {
		// 1000개짜리 배열 채우기
		int[] nums = new int[1001];
		int idx = 1;
		for (int i = 1; idx <= 1000; i++) {
			for (int j = 1; j <= i && idx <= 1000; j++) {
				nums[idx++] = i;
			}
		}

		// 영역에 걸리는 숫자 더하기
		for (int i = a; i <= b; i++) {
			answer += nums[i];
		}
	}

	private static void answer() {
		System.out.println(answer);
	}
}