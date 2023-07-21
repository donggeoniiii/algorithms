// 최소힙

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 최소힙이 구현되어 있는 priority queue 사용
		PriorityQueue<Integer> queue = new PriorityQueue<>();

		// 연산의 개수
		int N = Integer.parseInt(br.readLine());

		// 개수만큼 연산 진행
		while (N > 0) {

			// 입력값
			int next = Integer.parseInt(br.readLine());

			// 만약 입력값이 0이면 최솟값 pop
			if (next == 0) {
				if (queue.size() != 0) {
					sb.append(queue.poll()).append("\n");
				}
				// 빈 배열이면 0 출력
				else
					sb.append(0).append("\n");
			}
			// 입력값이 0이 아니면 그대로 추가
			else
				queue.offer(next);

			// 다음 연산으로 이동
			N--;
		}

		// 정답 출력
		System.out.println(sb);
	}
}