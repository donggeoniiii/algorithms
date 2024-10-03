// 히스토그램에서 가장 큰 직사각형

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			if (n == 0) {
				System.out.println(sb);
				return;
			}

			long answer = 0;

			// 스택에 {현재 높이, 현재 높이가 연속적으로 처음 등장한 위치} 저장
			Deque<int[]> stack = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				int curHeight = Integer.parseInt(st.nextToken());

				int index = i;

				// 이번에 입력할 직사각형의 높이가 현재 높이 이상이면 넓이 계산 후 스택 업데이트
				while (!stack.isEmpty() && stack.peek()[0] >= curHeight) {
					answer = Math.max(answer, (long) stack.peek()[0] * (i - stack.peek()[1]));
					index = stack.peek()[1];
					stack.pop();
				}
				stack.push(new int[] {curHeight, index});
			}

			// 스택에 남은 부분에서 최대값 비교
			while (!stack.isEmpty()) {
				answer = Math.max(answer, (long) stack.peek()[0] * (n - stack.peek()[1]));
				stack.pop();
			}

			sb.append(answer).append("\n");
		}
	}
}