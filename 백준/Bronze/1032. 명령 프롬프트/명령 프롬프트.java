// 명령 프롬프트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 찾을 파일의 개수
		int n = Integer.parseInt(br.readLine());

		// 출력 결과를 저장할 배열
		char[] result = new char[50];
		// 초기화
		Arrays.fill(result, '-');

		// 파일 이름 입력
		for (int i = 0; i < n; i++) {
			String filename = br.readLine();
			for (int j = 0; j < filename.length(); j++) {
				// 만약 첫 입력이면 그대로 입력줄에 넣고
				if (i == 0)
					result[j] = filename.charAt(j);
					// 아니면
				else {
					// 입력받은 값이 처음 입력값과 다르면
					if (result[j] != filename.charAt(j)) {
						// 와일드카드 처리
						result[j] = '?';
					}
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 50; i++) {
			if (result[i] != '-') {
				sb.append(result[i]);
			}
		}
		System.out.println(sb);
	}
}