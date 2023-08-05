// 알파벳 개수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 단어 입력
		String word = br.readLine();

		// 알파벳 배열
		int[] alphabet = new int[26];

		// 빈도 입력
		for (int i = 0; i < word.length(); i++) {
			alphabet[word.charAt(i) - 'a']++;
		}

		// 출력
		for (int i = 0; i < alphabet.length; i++) {
			sb.append(alphabet[i]).append(" ");
		}
		System.out.println(sb);
	}
}