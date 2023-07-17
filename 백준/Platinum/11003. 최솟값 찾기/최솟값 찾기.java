// 최솟값 찾기

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 숫자 개수 N
		// 부분최소를 정할 range L
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		// 값과 index를 저장할 deque
		ArrayDeque<int[]> deque = new ArrayDeque<>();

		// N개를 다 입력할 때까지
		st = new StringTokenizer(br.readLine());
		int idx = 1;
		while (idx <= N) {
			// deque에 넣을 {index, value} 배열
			int[] cur = {idx, Integer.parseInt(st.nextToken())};

			// deque 내 숫자들과 비교
			while (!deque.isEmpty() && deque.peekLast()[1] > cur[1]) {
				deque.pollLast();
			}

			// deque에 입력
			deque.offer(cur);

			// 만약 최솟값이 index를 벗어났으면 제거
			if (!deque.isEmpty() && deque.peekFirst()[0] < idx - L + 1) {
				deque.pollFirst();
			}

			// 최솟값 입력
			bw.write(deque.peekFirst()[1] + " ");

			idx++;
		}

		// 정답 출력
		bw.flush();
		bw.close();
	}
}