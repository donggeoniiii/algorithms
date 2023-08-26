import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 두 단어
		String word1 = br.readLine();
		String word2 = br.readLine();

		// 각 단어에 대해 알파벳 별 개수 입력할 배열
		int[] alphaCnt1 = new int[26];
		int[] alphaCnt2 = new int[26];
		for (int i = 0; i < word1.length(); i++) {
			int idx = word1.charAt(i) - 'a';

			alphaCnt1[idx]++;
		}

		for (int i = 0; i < word2.length(); i++) {
			int idx = word2.charAt(i) - 'a';

			alphaCnt2[idx]++;
		}

		// 두 값이 다를때마다 지워야 할 수 증가
		int answer = 0;
		for (int i = 0; i < 26; i++) {
			if (alphaCnt1[i] != alphaCnt2[i]) {
				answer += Math.abs(alphaCnt1[i] - alphaCnt2[i]);
			}
		}

		// 정답 출력
		System.out.println(answer);
	}
}