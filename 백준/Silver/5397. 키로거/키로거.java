// 키로거

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테스트케이스 개수
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			// 입력받은 커맨드 저장
			char[] commands = br.readLine().toCharArray();

			// 입력창의 역할을 할 deque 2개
			ArrayDeque<Character> curLeft = new ArrayDeque<>();
			ArrayDeque<Character> curRight = new ArrayDeque<>();

			// 테케 입력
			for (int j = 0; j < commands.length; j++) {
				// 1. input이 왼쪽 화살표일 때
				if (commands[j] == '<') {
					// 커서가 0에 있으면 더 못 움직임
					if (!curLeft.isEmpty()) {
						// 다른 경우는 커서 한쪽 움직임을 표현하기 위해, 왼쪽 deque에서 오른쪽 deque로 추가
						curRight.offerFirst(curLeft.pollLast());
					}
				}
				// 2. input이 오른쪽 화살표일 때
				else if (commands[j] == '>') {
					// 커서가 입력한 문자 개수와 같으면 더 못감
					if (!curRight.isEmpty()) {
						// 다른 경우는 커서 한쪽 움직임을 표현하기 위해, 오른쪽 deque에서 왼쪽 deque로 추가
						curLeft.offerLast(curRight.pollFirst());
					}
				}
				// 3. input이 백스페이스일 때
				else if (commands[j] == '-') {
					// 커서 위치가 0이면 못 지움
					if (!curLeft.isEmpty()) {
						// 커서 위치에 있는 문자 삭제
						curLeft.pollLast();
					}
				}
				// 4. input이 그냥 문자일 때
				else {
					// 커서 왼쪽에 입력
					curLeft.offerLast(commands[j]);
				}
			}

			// arraylist 순서대로 출력형태에 저장
			for (char word : curLeft) {
				sb.append(word);
			}
			for (char word : curRight) {
				sb.append(word);
			}
			sb.append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}