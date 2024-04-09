// Four Squares

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int minCnt = 4;
		for (int n1 = 223; n1 >= 0; n1--) {
			for (int n2 = 223; n2 >= 0; n2--) {
				for (int n3 = 223; n3 >= 0; n3--) {
					// 세 개 이하 제곱수의 합으로 n이 만들어진다면
					if (n1 * n1 + n2 * n2 + n3 * n3 == n) {

						// 몇 개의 수를 가지고 만든 건지 확인
						int curCnt = 0;
						if (n1 != 0) curCnt++;
						if (n2 != 0) curCnt++;
						if (n3 != 0) curCnt++;

						minCnt = Math.min(curCnt, minCnt);
					}
				}
			}
		}

		System.out.println(Math.min(4, minCnt));
	}
}