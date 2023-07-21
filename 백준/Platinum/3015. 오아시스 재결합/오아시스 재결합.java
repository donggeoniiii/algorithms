// 오아시스 재결합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static class Person {
		private int height;
		private int cnt;

		public Person(int height, int cnt) {
			this.height = height;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 사람의 수
		int N = Integer.parseInt(br.readLine());

		// 사람들의 순서와 키를 저장할 stack, index 0은 값, 1은 겹쳐 있던 같은 키의 사람의 개수
		Stack<Person> stack = new Stack<>();

		// 서로 볼 수 있는 쌍의 수
		long answer = 0;

		// 수 입력
		for (int i = 0; i < N; i++) {
			// 다음에 줄 설 사람 생성
			Person next = new Person(Integer.parseInt(br.readLine()), 1);

			// top과 비교
			while (!stack.isEmpty() && stack.peek().height <= next.height) {

				// 일단 작든 같든 top에 있는 사람은 필요가 없다(합치거나 더 볼 수 있는 사람이 없기 때문)
				Person cur = stack.pop();

				// 카운트만큼 더해줌
				answer += cur.cnt;

				// 만약 키가 같으면 카운트 갱신
				if (cur.height == next.height) {
					next.cnt += cur.cnt;
				}
			}

			// 만약 끝났는데 stack이 비어있지 않으면, 마지막에 더 큰 값에 막힌 것이므로 한명 더 만난 거니까 +1
			if (!stack.isEmpty())
				answer += 1;

			// stack에 줄 세우고 다음으로 이동
			stack.push(next);
		}

		// 정답 출력s
		System.out.println(answer);
	}
}