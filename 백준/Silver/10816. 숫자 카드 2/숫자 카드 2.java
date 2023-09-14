// 숫자 카드 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 카드 개수 입력
		int n = Integer.parseInt(br.readLine());

		// 숫자 카드 정보 담을 해시맵
		HashMap<Integer, Integer> numCards = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			// 이번에 확인할 숫자
			int num = Integer.parseInt(st.nextToken());

			// 이전 카운트 불러와서
			int prev = numCards.getOrDefault(num, 0);

			// 하나 증가한 값으로 변경
			numCards.put(num, prev + 1);
		}

		// m개의 정수에 대해, 해당 카드가 있는지 검사
		int m = Integer.parseInt(br.readLine());

		// 숫자 정보
		st = new StringTokenizer(br.readLine());

		// 정답 형태 생성
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= m; i++) {
			// 이번에 확인할 숫자
			int num = Integer.parseInt(st.nextToken());

			// 결과 입력
			sb.append(numCards.getOrDefault(num, 0)).append("\n");
		}

		// 출력
		System.out.println(sb);
	}
}