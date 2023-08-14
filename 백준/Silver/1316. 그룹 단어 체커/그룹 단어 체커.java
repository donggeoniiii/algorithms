// 그룹단어체커

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 입력받은 단어에 대해 그룹단어인지 체크하는 메소드
	static boolean checkGroupWordCnt(String word) {
		// 등장하는 알파벳이 이미 전에 등장했었는지 확인하는 배열
		boolean[] checked = new boolean[26];

		// 첫번째 index값 보고 카운트
		checked[word.charAt(0) - 'a'] = true;

		// 하나씩 보면서 뭉치 확인
		for (int idx = 1; idx < word.length(); idx++) {
			// 그전 index의 값과 다른데
			if (word.charAt(idx) != word.charAt(idx - 1)) {
				// 이미 본 값이면 그룹단어가 아니므로 종료
				if (checked[word.charAt(idx) - 'a']) {
					return false;
				}
				// 아니면 처음 본 단어 체크
				else {
					checked[word.charAt(idx) - 'a'] = true;
				}
			}
		}

		// 다 통과하면 그룹단어
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력할 단어의 수
		int n = Integer.parseInt(br.readLine());

		// 그룹단어 카운트
		int groupWordCnt = 0;

		// 입력받을 때마다 체크
		for (int i = 0; i < n; i++) {
			// 입력받은 단어
			String input = br.readLine();

			// 입력받은 값이 그룹단어면 카운트 증가
			if (checkGroupWordCnt(input)) {
				groupWordCnt++;
			}
		}

		// 정답 반환
		System.out.println(groupWordCnt);
	}
}