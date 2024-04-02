// 영화감독 숌

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// n번째 종말의 수
		int n = Integer.parseInt(br.readLine());

		int devilCnt = 0;
		int curNum = 665;
		while (devilCnt != n) {
			curNum++;

			String cur = String.valueOf(curNum);

			// 현재 숫자에 6이 연속 3개 이상 들어가면 종말수로 체크하고 다음으로
			if (cur.contains("666")) {
				devilCnt++;
			}
		}

		System.out.println(curNum);
	}
}