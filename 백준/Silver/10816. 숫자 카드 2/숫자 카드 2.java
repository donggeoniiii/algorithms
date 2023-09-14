// 숫자 카드 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 카드 개수 입력
		int n = Integer.parseInt(br.readLine());

		// 숫자 카드 정보 담을 배열
		int[] numCard = new int[20000001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			// 이번에 확인할 숫자
			int num = Integer.parseInt(st.nextToken());

			// 카운트 체크
			numCard[num + 10000000]++;
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
			sb.append(numCard[num + 10000000]).append("\n");
		}

		// 출력
		System.out.println(sb);
	}
}