// 방번호

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		char[] digit = input.toCharArray();

		// 숫자별 등장 횟수 저장, 6이랑 9는 같은 칸(6)에 보관
		int[] numCnt = new int[9];
		for (int i = 0; i < digit.length; i++) {
			int num = digit[i] - '0';
			if (num == 9) {
				numCnt[6]++;
			} else {
				numCnt[num]++;
			}
		}

		// 6과 9의 등장수를 합친 값이 홀수면 2로 나누고+1, 짝수면 그대로 2로 나눔
		numCnt[6] = (numCnt[6] % 2 == 1) ? numCnt[6] / 2 + 1 : numCnt[6] / 2;

		// 최대로 많이 등장한 숫자에 맞춰서 플라스틱 세트 사기
		int maxCnt = -1;
		for (int i = 0; i < 9; i++) {
			maxCnt = Math.max(maxCnt, numCnt[i]);
		}

		// 정답 출력
		System.out.println(maxCnt);
	}
}