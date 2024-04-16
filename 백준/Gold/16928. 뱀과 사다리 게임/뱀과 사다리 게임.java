// 뱀과 사다리 게임

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
		// 사다리의 수, 뱀의 수
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] ladder = new int[101];
		for (int i = 0; i < n+m; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			ladder[src] = dest;
		}

		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[101];
		int[] turn = new int[101];
		queue.offer(1);
		visited[1] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			// 100번째를 찾으면 종료
			if (cur == 100) {
				System.out.println(turn[cur]);
				return;
			}

			for (int dice = 1; dice <= 6; dice++) {
				int next = cur + dice;

				// 100을 넘어가면 스킵
				if (next > 100) continue;

				// 이미 온적 있으면 절대 가장 빠른 경로가 아님
				if (visited[next]) continue;

				// 사다리나 뱀인 경우
				if (ladder[next] != 0) {
					
					// 이미 한번 가본 길이면 스킵
					if (visited[ladder[next]]) continue;
					
					// 아니면 타고 가보기
					queue.offer(ladder[next]);
					visited[ladder[next]] = true;
					turn[ladder[next]] = turn[cur] + 1;
				}
				// 아닌 경우
				else {
					queue.offer(next);
					visited[next] = true;
					turn[next] = turn[cur] + 1;
				}
			}
		}
	}
}