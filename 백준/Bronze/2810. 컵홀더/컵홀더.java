// 컵홀더

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 좌석의 수
		int N = input.nextInt();

		// 좌석 입력
		String line = input.next();

		// 컵홀더 수
		int answer = 0;
		int idx = 0;
		while (idx < line.length()) {

			// 만약 해당 자리가 싱글석이면
			if (line.charAt(idx) == 'S') {
				// 카운트 +1
				answer++;

				// 다음 좌석 확인
				idx++;
			}
			// 아니면 커플석이니까
			else {
				// 카운트 +1
				answer++;

				// 다음 좌석은 2칸 넘겨야
				idx += 2;
			}

			// 만약 마지막 칸이면 바깥쪽에 컵홀더 하나 더 놓을 수 있으니까 +1
			if (idx == line.length())
				answer++;
		}
		// 정답 출력, 총 칸 수가 사람 수보다 많으면 모두 둘 수 있으니까 사람 수, 적으면 컵홀더 수 출력
		System.out.println((answer > N) ? N : answer);
	}
}