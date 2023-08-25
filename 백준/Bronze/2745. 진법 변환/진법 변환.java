// 진법 변환

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// B진법 수 N
		StringTokenizer st = new StringTokenizer(br.readLine());

		String n = st.nextToken();

		int b = Integer.parseInt(st.nextToken());

		// 수 변환을 위한 hashmap
		HashMap<Character, Integer> trans = new HashMap<>();

		// 입력
		char idx = 'A';
		for (int i = 10; i < 36; i++) {
			trans.put(idx++, i);
		}

		// 변환해서 계산
		int sum = 0;
		for (int i = 0; i < n.length(); i++) {
			char digit = n.charAt(i);
			int num;

			// 자릿값이 숫자가 아니면 해시맵 이용해서 변환
			if (digit > '9') {
				num = trans.get(digit);
			}
			// 0~9면 그대로
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