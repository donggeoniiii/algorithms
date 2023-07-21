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

		// 연산을 저장할 priority queue
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {

				if (Math.abs(o1) > Math.abs(o2)) {
					return 1;
				} else if (Math.abs(o1) == Math.abs(o2))
					return o1 - o2;
				else
					return -1;
			}
		});

		// 연산 횟수만큼
		while (N > 0) {

			// 다음 입력
			int next = Integer.parseInt(br.readLine());

			// 입력이 0이면 절댓값의 최솟값 출력
			if (next == 0) {
				if (!queue.isEmpty()) {
					sb.append(queue.poll()).append("\n");
				}
				// queue가 비어있으면 0 출력
				else {
					sb.append(0).append("\n");
				}
			}
			// 입력이 0이 아니면 queue에 추가
			else {
				queue.offer(next);
			}

			// 다음 연산으로 이동
			N--;
		}

		// 출력
		System.out.println(sb);
	}
}