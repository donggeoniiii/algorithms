// 회사에 있는 사람

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 출입 기록의 수
		int n = Integer.parseInt(br.readLine());

		// 출입기록 기록할 해시맵
		HashMap<String, Boolean> isEnter = new HashMap<>();

		// 출퇴근 데이터 입력
		for (int i = 0; i < n; i++) {
			// 사람 이름, 출퇴근 기록
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String type = st.nextToken();

			// 만약 출입이면 true, 퇴근이면 false
			if (type.charAt(0) == 'e') {
				isEnter.put(name, true);
			} else {
				isEnter.put(name, false);
			}
		}

		// 기록부를 보고 현재 출근한 사람 리스트 뽑기
		ArrayList<String> enterList = new ArrayList<>();
		for (String name : isEnter.keySet()) {
			if (isEnter.get(name)) {
				enterList.add(name);
			}
		}

		// 사전역순 정렬
		Collections.sort(enterList);
		Collections.reverse(enterList);

		// 출력
		StringBuilder sb = new StringBuilder();
		for (String name : enterList) {
			sb.append(name).append("\n");
		}
		System.out.println(sb);
	}
}