// 수 이어 쓰기 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 1부터 이어 쓸 수
	static int n;

	// 찾으려는 자리수
	static long k;

	// 찾아야 되는 숫자
	static long targetNum;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Long.parseLong(st.nextToken());
	}

	private static void solution() {
		// k번째 숫자가 포함된 숫자
		targetNum = 0;

		// 현재 숫자 자리수
		long idx = 1;

		// 자릿수별 숫자 개수 카운트, 1자리는 9개
		long numCnt = 9;

		// 찾으려는 숫자가 이번 자릿수 내에 있을 때까지
		while (k > numCnt * idx) {
			// 없으면 탐색범위에서 제외, 해당하지 않는 범위만큼 숫자 제외
			k -= (numCnt * idx);

			// 해당하지 않는 숫자 개수만큼 카운트 증가
			targetNum += numCnt;

			// 다음 자릿수에서 탐색
			idx++;

			// 다음 자릿수 숫자 개수
			numCnt *= 10;
		}

		// 해당하는 자리수에서 찾으려는 숫자 찾기
		targetNum = (targetNum + 1) + (k-1) / idx;

		// 만약 범위를 벗어났으면 -1
		if (targetNum > n) {
			System.out.println(-1);
		} else {
			// 벗어나지 않았으면
			int targetIdx = (int) ((k-1) % idx);
			System.out.println(String.valueOf(targetNum).charAt(targetIdx));
		}
	}
}