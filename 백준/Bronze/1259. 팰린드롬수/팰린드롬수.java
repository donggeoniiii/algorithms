// 팰린드롬 수

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 입력이 끝났는지 확인하는 flag 변수
		boolean terminated = false;
		while (!terminated) {

			// 입력 받기
			String number = input.next();

			// 만약 입력이 0이면 종료
			if (number.equals("0"))
				terminated = true;
			else {
				// index를 체크할 변수
				int idx = 0;

				// 중간 index에 도달할 때까지
				while (idx <= number.length() / 2) {
					// 두 수가 다르면 종료
					if (number.charAt(idx) != number.charAt(number.length() - 1 - idx)) {
						sb.append("no").append("\n");
						break;
					}
					// 맞으면 다음으로 이동
					idx++;
				}
				// 통과했으면 yes
				if (idx > number.length() / 2) {
					sb.append("yes").append("\n");
				}
			}
		}
		// 출력
		System.out.println(sb);
	}
}