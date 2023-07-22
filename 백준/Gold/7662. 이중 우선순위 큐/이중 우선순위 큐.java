// 이중 우선순위 큐

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트케이스 개수
		int T = Integer.parseInt(br.readLine());

		// 테스트케이스 별 진행
		for (int i = 0; i < T; i++) {
			// 연산의 개수
			int k = Integer.parseInt(br.readLine());

			// 우선순위 큐 역할을 할 treemap
			TreeMap<Integer, Integer> map = new TreeMap<>();

			for (int j = 0; j < k; j++) {
				// 입력
				StringTokenizer st = new StringTokenizer(br.readLine());

				// 연산 종류
				char type = st.nextToken().charAt(0);

				// 입력 숫자
				int num = Integer.parseInt(st.nextToken());

				// 연산이 '입력'이면
				if (type == 'I') {
					// treemap에 입력
					// getOrDefault == 해당 key가 없으면 default 값 반환, 있으면 value 반환
					// 중복되는 경우를 막기 위해 중복되는 경우 key 값 업데이트 하도록
					map.put(num, map.getOrDefault(num, 0) + 1);
				}
				// 연산이 '삭제'면
				else { // if (type == 'D')
					// map이 비어있지 않을 때
					if (!map.isEmpty())
						// 입력이 1이면
						if (num == 1) {
							// 최댓값 제거
							int pollNum = map.lastKey();

							// value를 보고 key를 제거 or value-1
							if (map.get(pollNum) == 1)
								map.remove(pollNum);
							else
								map.put(pollNum, map.get(pollNum) - 1);
						}
						// 입력이 -1이면 최솟값 빼자
						else {
							// 최솟값 제거
							int pollNum = map.firstKey();
							
							// value를 보고 key를 제거 or value-1
							if (map.get(pollNum) == 1)
								map.remove(pollNum);
							else
								map.put(pollNum, map.get(pollNum) - 1);
						}
				}

			}

			// 남은 값에 대해 최대 최소 입력
			if (map.isEmpty())
				sb.append("EMPTY").append("\n");
			else {
				sb.append(map.lastKey()).append(" ");
				sb.append(map.firstKey()).append("\n");
			}
		}

		// 정답 출력
		System.out.println(sb);
	}
}