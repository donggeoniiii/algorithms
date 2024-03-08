// 무한수열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	// 세 정수
	static long n;
	static int p, q;

	// 수열 저장할 해시맵
	static HashMap<Long, Long> map;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Long.parseLong(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		map = new HashMap<>();
	}

	private static void solution() {
		// A_n 출력
		System.out.println(getNumber(n));
	}

	private static long getNumber(long n) {
		if (n == 0) {
			return 1;
		}
		// 값이 있으면 해당 값 출력
		if (map.getOrDefault(n, -1L) != -1L) {
			return map.get(n);
		}

		// 없으면 입력하고 출력
		map.put(n, getNumber(n/p) + getNumber(n/q));

		return map.get(n);
	}
}