// 탑

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 입력이 반복문 안에서 무수히 일어나므로 이번엔 bufferedReader 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 탑의 개수
		int N = Integer.parseInt(br.readLine());

		// 탑의 높이를 저장할 stack
		Stack<int[]> stack = new Stack<>();

		// 하나씩 넣으면서 레이저 수신 확인
		StringTokenizer st = new StringTokenizer(br.readLine());
		int idx = 1;
		while (idx <= N) {
			// 입력할 수
			int cur = Integer.parseInt(st.nextToken());

			// 스택이 비거나 더 큰 값이 top에 올 때 까지 pop
			while (!stack.isEmpty() && stack.peek()[1] <= cur) {
				stack.pop();
			}

			// 스택에 원소가 없으면 신호를 못받으므로 0, 아니면 top에 있는 index 추가
			if (stack.size() == 0)
				sb.append(0);
			else
				sb.append(stack.peek()[0]);

			sb.append(" ");

			// 스택에 입력
			stack.push(new int[] {idx, cur});

			// 다음 입력하러 이동
			idx++;
		}

		// 정답 출력
		System.out.println(sb);
	}
}