// 날짜 계산

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// E: 지구, S: 태양, M: 달
	static int e, s, m;

	// 정답
	static int answer;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		print();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		e = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	}

	private static void solution() {
		// 준규가 사는 나라의 시간
		int je = 1, js = 1, jm = 1;

		// 1년부터 시작
		answer = 1;

		// 처음으로 마지막에 닿는 때까지 시행을 해보면 되긴 한다
		while (!(match(je, js, jm))) {
			// 안 맞으면 다음 해로 이동
			je++;
			js++;
			jm++;

			// 카운트 넘기기
			if (je > 15) je = 1;
			if (js > 28) js = 1;
			if (jm > 19) jm = 1;

			answer++;
		}
	}

	private static boolean match(int je, int js, int jm) {
		return je == e && js == s && jm == m;
	}

	private static void print() {
		System.out.println(answer);
	}
}