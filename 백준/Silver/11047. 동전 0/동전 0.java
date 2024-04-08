// 동전 0

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 동전의 종류, 만들려는 가치의 합
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] coin = new int[n];
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		// 비싼 동전부터 사용 가능한지 확인
		int coinCnt = 0;
		int remains = k;
		for (int i = n-1; i >= 0; i--) {
			// 이번 동전 금액이 남은 금액보다 크면 해당 없음
			if (coin[i] > remains) continue;

			// 이번 동전으로 살 수 있을 때까지 사기
			coinCnt += remains / coin[i];

			// 남은 금액 갱신
			remains %= coin[i];
		}

		// 정답 출력
		System.out.println(coinCnt);
	}
}