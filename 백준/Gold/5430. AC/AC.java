import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 테스트케이스
		int T = input.nextInt();

		// 테스트케이스 별 입력
		for (int i = 0; i < T; i++) {
			// 함수 실행 순서
			String oper = input.next();

			// 함수 순서 저장할 배열
			char[] operation = new char[oper.length()];

			// 함수 저장
			for (int j = 0; j < oper.length(); j++) {
				operation[j] = oper.charAt(j);
			}

			// 입력으로 주어지는 수의 개수
			int N = input.nextInt();

			// 입력으로 주어지는 값
			String numString = input.next();

			// 괄호 빼고
			numString = numString.substring(1, numString.length() - 1);

			// , 기준으로 값끼리 남기고
			String[] numStringArr = numString.split(",");

			// 정수형태로 배열에 저장
			int[] nums = new int[N];
			for (int j = 0; j < N; j++) {
				nums[j] = Integer.parseInt(numStringArr[j]);
			}

			// 출력, 방향전활을 위한 deque
			ArrayDeque<Integer> deque = new ArrayDeque<>();

			// 입력
			for (int j = 0; j < nums.length; j++) {
				deque.offerLast(nums[j]);
			}

			// 전환되는 방향을 저장하는 변수
			boolean flag = true;

			// error 여부를 저장하는 변수
			boolean isError = false;

			// 함수 순서대로 실행
			boolean terminated = false;
			int idx = 0;
			while (!terminated) {
				// 마지막 index면 종료
				if (idx == operation.length) {
					terminated = true;
				} else {
					// 전환하라고 하면 전환
					if (operation[idx] == 'R') {
						flag = !flag;
						idx++;
					}
					// 빼야되면
					else {
						// size가 0이면 종료, 아니면 하나 빼고 index 증가
						if (deque.size() == 0) {
							isError = true;
							terminated = true;
						} else if (flag)
							deque.pollFirst();
						else
							deque.pollLast();
						idx++;
					}
				}
			}
			// error 추가, 아니면 결과 추가
			if (isError)
				sb.append("error").append("\n");
			else {
				sb.append("[");
				// 방향에 따라 입력
				if (flag) {
					while (!deque.isEmpty()) {
						sb.append(deque.pollFirst());
						if (deque.size() >= 1)
							sb.append(",");
					}
				} else {
					while (!deque.isEmpty()) {
						sb.append(deque.pollLast());
						if (deque.size() >= 1)
							sb.append(",");
					}
				}
				sb.append("]").append("\n");
			}
		}
		// 정답 출력
		System.out.println(sb);
	}
}