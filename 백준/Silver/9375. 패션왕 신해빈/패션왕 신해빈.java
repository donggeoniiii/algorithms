// 패션왕 신해빈

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트케이스 개수
		int t = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < t; tc++) {
			// 의상 수
			int n = Integer.parseInt(br.readLine());

			// 의상을 저장할 hashmap
			HashMap<String, Integer> hashMap = new HashMap<>();

			for (int clothes = 0; clothes < n; clothes++) {
				// 해빈이가 가진 의상의 이름, 종류
				StringTokenizer st = new StringTokenizer(br.readLine());
				String name = st.nextToken();
				String category = st.nextToken();

				// 저장
				int clothesCnt = hashMap.getOrDefault(category, 0);
				hashMap.put(category, clothesCnt + 1);
			}

			// 경우의 수 계산, 각 카테고리별 옷 수 + 1(안 고르는 경우)
			int answer = 0;
			for (String category : hashMap.keySet()) {
				if (answer == 0) {
					answer = hashMap.get(category) + 1;
				} else {
					answer *= hashMap.get(category) + 1;
				}
			}

			// 모든 카테고리에 대해 안 고르는 경우는 알몸이라는 소리니까 제외
			if (n != 0)
				answer -= 1;
			
			// 결과 추가
			sb.append(answer).append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}