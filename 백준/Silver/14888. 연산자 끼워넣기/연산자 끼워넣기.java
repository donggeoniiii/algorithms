// 연산자 끼워 넣기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 주어질 수열의 길이
	static int n;

	// 연산자별 개수 저장 배열
	static int[] operator;

	// 숫자를 순서대로 저장할 queue
	static int[] numOrder;

	// 사용할 연산자를 순서대로 저장할 배열
	static int[] operatorOrder;

	// 최대, 최소
	static int maxVal;
	static int minVal;

	// 연산자 순서 정하기
	static void selectOperationOrder(int cnt) {
		// base case: n-1번의 연산자 선택이 끝나면
		if (cnt >= n - 1) {

			// 주어진 대로 연산
			int curVal = numOrder[0];
			for (int i = 0; i < n - 1; i++) {
				switch (operatorOrder[i]) {
					case 0:
						curVal += numOrder[i + 1];
						break;
					case 1:
						curVal -= numOrder[i + 1];
						break;
					case 2:
						curVal *= numOrder[i + 1];
						break;
					case 3:
						// 만약 제수가 음수면
						if (curVal < 0) {
							curVal = Math.abs(curVal) / numOrder[i + 1];
							curVal = -curVal;
						} else
							curVal /= numOrder[i + 1];
						break;
				}
			}

			// 최대최소 비교
			maxVal = Math.max(maxVal, curVal);
			minVal = Math.min(minVal, curVal);
		}

		// recursive case
		for (int i = 0; i < 4; i++) {
			// 만약 해당 연산자를 다 골랐으면 스킵
			if (operator[i] > 0) {

				// 연산자 선택하고 연산자에 맞게 값 추가
				operator[i]--;
				operatorOrder[cnt] = i;

				// 다음 선택을 위해 이동
				selectOperationOrder(cnt + 1);

				// 다른 선택을 위해 값 원래대로
				operator[i]++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 수열의 길이
		n = Integer.parseInt(br.readLine());

		// 수열 입력
		numOrder = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			numOrder[i] = Integer.parseInt(st.nextToken());
		}

		// 연산자 개수 입력
		operator = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}

		// 최대 최솟값 초기화
		maxVal = Integer.MIN_VALUE;
		minVal = Integer.MAX_VALUE;

		// 순서 넣을 배열 초기화
		operatorOrder = new int[n - 1];

		// 순서 정하고 최대최소 구하기
		selectOperationOrder(0);

		// 출력
		System.out.println(maxVal + "\n" + minVal);
	}
}