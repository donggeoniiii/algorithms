import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테케 개수
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			// 단어 입력
			String[] word = new String[2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			word[0] = st.nextToken();
			word[1] = st.nextToken();

			// 알파벳 배열
			int[][] alphabet = new int[2][26];

			// 빈도 입력
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < word[i].length(); j++) {
					alphabet[i][word[i].charAt(j) - 'a']++;
				}
			}

			// 비교해보기
			boolean isPossible = true;
			for (int i = 0; i < 26; i++) {
				if (alphabet[0][i] != alphabet[1][i]) {
					isPossible = false;
					break;
				}
			}

			// 정답 추가
			sb.append((isPossible) ? "Possible" : "Impossible").append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}