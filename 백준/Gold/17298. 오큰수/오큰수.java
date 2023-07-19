// 오큰수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	// 숫자 정보를 저장하는 객체 생성
	static class Num {
		private int idx;
		private int value;

		public Num(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 숫자 개수 N
		int N = Integer.parseInt(br.readLine());

		// 오큰수 저장 배열
		int[] rightBigNumList = new int[N + 1];

		// 안 빠지는 애들은 -1로 출력할 거니까 -1로 초기화
		Arrays.fill(rightBigNumList, -1);

		// 입력받은 숫자모음
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 숫자를 저장할 stack
		Stack<Num> stack = new Stack<>();

		// 모든 숫자 입력
		for (int i = 1; i <= N; i++) {
			// 들어올 숫자
			int next = Integer.parseInt(st.nextToken());

			// stack의 top과 비교
			while (!stack.isEmpty() && stack.peek().value < next) {
				// 출력을 위해 list에 추가
				rightBigNumList[stack.pop().idx] = next;
			}

			// stack에 새로운 수 입력
			stack.push(new Num(i, next));
		}

		// 정답 출력
		for (int i = 1; i <= N; i++) {
			sb.append(rightBigNumList[i]).append(" ");
		}
		System.out.println(sb);
	}
}