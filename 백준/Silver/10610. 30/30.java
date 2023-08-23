// 30

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 배열을 입력 받을 priorityQueue
		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

		// 주어진 수의 자릿수 합
		int sum = 0;

		// 0이 하나라도 있는지 확인하는 변수
		boolean hasZero = false;

		// 값 입력받기
		String line = br.readLine();
		for (int i = 0; i < line.length(); i++) {
			int num = line.charAt(i) - '0';

			// 0 있는지 체크
			if (num == 0)
				hasZero = true;
			sum += num;
			queue.offer(num);
		}

		// 만약 0이 있고 3으로 나누어 떨어지면
		if (hasZero && sum % 3 == 0) {
			StringBuilder sb = new StringBuilder();
			while (!queue.isEmpty()) {
				sb.append(queue.poll());
			}
			// 정답 출력
			System.out.println(sb);
		} else {
			// 아니면 -1 출력(안됨)
			System.out.println(-1);
		}
	}
}