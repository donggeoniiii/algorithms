// 상근이의 여행

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 테케 개수
	static int t;

	// 국가의 수, 비행기 종류
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= t; i++) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			for (int j = 1; j <= m; j++) {
				st = new StringTokenizer(br.readLine());
			}

			// 이미 방문한 국가를 거쳐가도 되니까 n개국을 다 잇기만 하면 된다.
			System.out.println(n-1);
		}
	}
}