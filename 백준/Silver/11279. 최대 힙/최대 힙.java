import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 연산의 개수
		int N = Integer.parseInt(br.readLine());

		// 연산을 담을 priority queue
		PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

		// 연산 개수만큼
		while (N > 0) {

			// 입력값
			int next = Integer.parseInt(br.readLine());

			// 만약 입력이 0이면 최댓값 출력
			if (next == 0) {
				if (!queue.isEmpty()) {
					sb.append(queue.poll()).append("\n");
				}
				// 만약 queue가 비었으면 0 출력
				else {
					sb.append(0).append("\n");
				}
			}
			// 아니면 입력
			else {
				queue.offer(next);
			}

			// 다음 연산으로 이동
			N--;
		}

		// 정답 출력
		System.out.println(sb);
	}
}