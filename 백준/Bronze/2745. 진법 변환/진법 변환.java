// 진법 변환

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// B진법 수 N
		StringTokenizer st = new StringTokenizer(br.readLine());

		String n = st.nextToken();

		int b = Integer.parseInt(st.nextToken());

		// 변환해서 계산
		int sum = 0;
		for (int i = 0; i < n.length(); i++) {
			char digit = n.charAt(i);
			int num;

			// 자릿값이 숫자가 아니면 아스키 코드값 참고해서 변환
			if (digit > '9') {
				num = digit - 'A' + 10;
			}
			// 0~9면 하던 대로
			else {
				num = digit - '0';
			}

			// 계산한 자릿값으로 합산
			sum += (int)(num * Math.pow(b, n.length() - 1 - i));
		}

		// 정답 출력
		System.out.println(sum);
	}
}