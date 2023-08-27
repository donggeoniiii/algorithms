// 단어 뒤집기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 테스트 케이스 개수
		int t = Integer.parseInt(br.readLine());

		// 테케별 입력
		for (int tc = 0; tc < t; tc++) {
			// 단어를 저장할 queue
			Queue<String> queue = new LinkedList<>();

			// 단어 입력
			st = new StringTokenizer(br.readLine());

			while (st.hasMoreTokens()) {
				String cur = new StringBuilder(st.nextToken()).reverse().toString();
				// queue에 저장
				queue.offer(cur);
			}

			// 테케 입력
			while (!queue.isEmpty()) {
				sb.append(queue.poll()).append(" ");
			}
			sb.append("\n");
		}

		// 출력
		System.out.println(sb);
	}
}