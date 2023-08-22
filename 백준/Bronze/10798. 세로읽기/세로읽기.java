// 세로 읽기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 5개의 글자를 저장할 배열
		char[][] words = new char[5][15];

		// 초기화
		for (int i = 0; i < 5; i++) {
			Arrays.fill(words[i], '-');
		}

		// 저장
		for (int i = 0; i < 5; i++) {
			String input = br.readLine();
			for (int idx = 0; idx < input.length(); idx++) {
				words[i][idx] = input.charAt(idx);
			}
		}

		// 세로읽은 결과를 저장할 stringBuilder
		StringBuilder sb = new StringBuilder();

		// 읽기
		for (int i = 0; i < 15; i++) {
			// 통째로 빈 줄은 건너뛰어야 하니까
			int emptyCnt = 0;
			for (int j = 0; j < 5; j++) {
				// 만약 비어있으면
				if (words[j][i] == '-') {
					// 빈칸 카운트 증가
					emptyCnt++;
				}
				// 아니면 정답에 추가
				else {
					sb.append(words[j][i]);
				}

			}
			// 다섯줄 다 비었으면 종료
			if (emptyCnt == 5) {
				break;
			}
		}

		// 정답 출력
		System.out.println(sb);
	}
}