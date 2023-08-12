// 직만방

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 직사각형 수
		int n = Integer.parseInt(br.readLine());

		// 경우의 수
		int cnt = 0;
		for (int r = 1; r < n + 1; r++) {
			for (int c = 1; c <= r; c++) {
				if (r * c <= n)
					cnt++;
			}
		}

		// 정답 출력
		System.out.println(cnt);
	}
}