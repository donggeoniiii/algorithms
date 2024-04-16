// A -> B

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());

		Queue<Long> queue = new LinkedList<>();
		queue.add(a);

		int searchCnt = 1;
		while (!queue.isEmpty()) {
			int curSize = queue.size();

			// 한 while 문에 queue에 들어오면 다 같은 탐색횟수에 탐색된 수
			for (int i = 0; i < curSize; i++) {
				long cur = queue.poll();
				if (cur == b) {
					System.out.println(searchCnt);
					return;
				}

				long next = cur * 2;
				if (next <= b) queue.offer(next);
				next = cur * 10 + 1;
				if (next <= b) queue.offer(next);
			}
			searchCnt++;
		}

		// 못 만들면 -1
		System.out.println(-1);
	}
}