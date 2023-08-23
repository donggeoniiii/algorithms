// 30

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 지나가다 본 값
		String line = br.readLine();

		// 각 자릿수를 입력 받을 배열
		char[] digit = line.toCharArray();

		// 정렬
		Arrays.sort(digit);
		
		// 주어진 수의 자릿수 합
		int sum = 0;

		// 0이 하나라도 있는지 확인하는 변수
		boolean hasZero = (digit[0] == '0');

		// 30으로 나누어지는지 확인
		for (int i = 0; i < line.length(); i++) {
			int num = digit[i] - '0';

			sum += num;
		}

		// 만약 0이 있고 3으로 나누어 떨어지면
		if (hasZero && sum % 3 == 0) {
			StringBuilder sb = new StringBuilder();
			for (int idx = 0; idx < digit.length; idx++) {
				sb.append(digit[digit.length - 1 - idx] - '0');
			}
			// 정답 출력
			System.out.println(sb);
		} else {
			// 아니면 -1 출력(안됨)
			System.out.println(-1);
		}
	}
}