// 옥상정원꾸미기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	static class Building {
		private int index;
		private long height;

		public Building(int index, long height) {
			this.index = index;
			this.height = height;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 탑의 개수
		int N = Integer.parseInt(br.readLine());

		// 탑의 위치와 높이를 저장할 deque
		ArrayDeque<Building> deque = new ArrayDeque<>();

		// 높이합
		long totalHeight = 0;

		// 빌딩 입력
		for (int i = 0; i < N; i++) {
			// 이번에 입력할 빌딩의 높이
			long cur = Integer.parseInt(br.readLine());

			while (!deque.isEmpty()) {
				// 만약 top에 있는 값보다 더 큰 값이 들어오면
				if (deque.peek().height <= cur) {
					// 더 큰 값이 마주칠 때까지 빼기
					deque.pop();
				} else
					break;
			}
			// 넣기 전 스택 사이즈가 곧 해당 자리에서 보이는 사이즈
			totalHeight += deque.size();

			// 다음 수 입력
			deque.push(new Building(i, cur));

		}

		// 정답 출력
		System.out.println(totalHeight);
	}
}