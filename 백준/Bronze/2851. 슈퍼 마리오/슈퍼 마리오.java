// 슈퍼마리오

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// 입력받을 수 list
		int[] numList = new int[10];

		// 입력하기
		for (int i = 0; i < 10; i++) {
			numList[i] = input.nextInt();
		}

		// 100을 처음으로 넘을 때까지
		int sum = 0;
		int idx = 0;
		while (idx < 10 && sum < 100) {
			// 만약 합한 값이 100과의 절댓값에서 이전보다 작거나 같으면 계속 진행
			if (Math.abs(sum + numList[idx] - 100) <= Math.abs(sum - 100)) {
				sum += numList[idx];

				// 다음 index로 이동
				idx++;
			} else {
				// 아니면 종료
				break;
			}
		}

		// 정답 출력
		System.out.println(sum);
	}
}