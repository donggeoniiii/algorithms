// 조합 0의 개수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;

	static int answer;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		answer();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	}

	private static void solution() {
		// 2와 5의 승수 구하기
		int count2 = countPow(2, n) - countPow(2, n - m) - countPow(2, m);
		int count5 = countPow(5, n) - countPow(5, n - m) - countPow(5, m);

		// 10이 만들어질 수 있는 개수
		answer = Math.min(count2, count5);
	}


	static int countPow(int pow, long num) {
		int count = 0;

		while (num >= pow) {
			count += num / pow;
			num /= pow;
		}

		return count;
	}

	private static void answer() {
		System.out.println(answer);
	}
}