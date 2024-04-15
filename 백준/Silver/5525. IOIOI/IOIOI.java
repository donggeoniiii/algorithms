// IOIOI

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		String s = br.readLine();

		// 'oi' 패턴의 수
		int patternCnt = 0;

		// 찾으려는 Pn 패턴의 수
		int pnCnt = 0;

		int idx = 1;
		while (idx < m - 1) {
			// 패턴을 찾으면 스택 + 1
			if (s.charAt(idx) == 'O' && s.charAt(idx + 1) == 'I') {
				patternCnt++;

				// 만약 패턴이 n개 쌓이면 처음이 I인지 확인
				if (patternCnt == n) {
					int stringSize = 2 * patternCnt;
					if (s.charAt(idx - (stringSize - 1)) == 'I') {
						pnCnt++;
					}
					// 다음 index로 넘어가서 볼 땐 'oi' 패턴이 한번만 더 나오면 됨
					patternCnt--;
				}

				// 맞았을 때 index 이동
				idx += 2;
			}
			// 틀렸으면 다음 위치로, 스택 초기화
			else {
				idx++;
				patternCnt = 0;
			}
		}

		System.out.println(pnCnt);
	}
}