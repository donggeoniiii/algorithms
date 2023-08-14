// 크로아티아 알파벳

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력받은 크로아티아 단어
		String input = br.readLine();

		// index 돌면서 체크하기
		int wordCnt = 0;
		for (int idx = 0; idx < input.length(); idx++) {
			char word = input.charAt(idx);
			// 만약 특수부호면 넘기고
			if (word - 'a' < 0) {
				continue;
			}
			// 만약 j인데 앞이 l이나 n이었으면 넘기고
			else if (word == 'j' && idx != 0 && ((input.charAt(idx - 1) == 'l') || (input.charAt(idx - 1) == 'n'))) {
				continue;
			}
			// 만약 z인데 앞뒤가 d랑 =면 넘기고
			else if (word == 'z' && (idx < input.length() - 1 && idx > 0) && (input.charAt(idx - 1) == 'd'
				&& input.charAt(idx + 1) == '=')) {
				continue;
			}
			// 나머지면 카운트+1
			else {
				wordCnt++;
			}
		}

		// 정답 출력
		System.out.println(wordCnt);
	}
}