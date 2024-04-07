// 분해합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 분해합
		int n = Integer.parseInt(br.readLine());

		// 0부터 생성자 구하기
		for (int num = 0; num <= n; num++) {
			String nts = String.valueOf(num);

			int total = num;
			for (char c : nts.toCharArray()) {
				total += c - '0';
			}

			if (total == n) {
				System.out.println(num);
				return;
			}
		}

		System.out.println(0);
	}
}