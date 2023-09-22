// 화성수학

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테케 개수
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			// 입력 저장
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 첫 토큰은 초기값
			float num = Float.parseFloat(st.nextToken());

			// 편한 계산을 위해 100 곱해서 계산(정수자리 반올림하고 다시 100으로 나눔)
			num *= 100;

			// 연산 실행
			while (st.hasMoreTokens()) {
				// 다음 연산
				char next = st.nextToken().toCharArray()[0];

				// 1. @면 x3
				if (next == '@') {
					num *= 3;
				}
				// 2. %면 +5
				else if (next == '%') {
					num += 500;
				}
				// 3. #면 -7
				else if (next == '#') {
					num -= 700;
				}
				// 다른 값이 들어올 일은 없겠지만 명시적으로 표시
				else {
					continue;
				}
			}

			// 반올림하고 다시 변환
			num = Math.round(num);

			// 결과 입력
			sb.append(String.format("%.2f", num / 100.0)).append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}