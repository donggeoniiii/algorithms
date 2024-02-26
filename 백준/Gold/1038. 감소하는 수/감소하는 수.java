// 감소하는 수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	// 감소 수 순서
	static int n;

	// 감소하는 수를 저장할 리스트, 최댓값이 100억에 근접하므로 Long
	static List<Long> descendingNumList;

	// 정답
	static long answer;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		answer();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		descendingNumList = new ArrayList<>();
	}

	private static void solution() {
		// 가장 큰 감소하는 수: 9876543210 이므로 10자리 이상을 요구하면 감소하는 수일 수 없음
		if (n >= 1023) {
			answer = -1;
		}
		else {
			// 최대 10자리부터 1자리까지 감소하는 수 구하기
			for (int i = 0; i < 10; i++) {
				findDescendingNum(i);
			}

			// 다 구했으면 정렬
			Collections.sort(descendingNumList);

			// n번째 감소수 저장
			answer = descendingNumList.get(n);
		}

	}

	private static void findDescendingNum(long num) {
		// 0~10은 기본적으로 감소하는 수, 감소하는 수를 또 감소시키면 당연히 감소수니까 추가
		descendingNumList.add(num);

		// 다음 시행을 위해 남은 값 확인
		long mod = num % 10;

		// 만약 일의 자리까지 탐색이 끝났으면 종료
		if (mod == 0) {
			return;
		}

		// 나머지가 있으면 이어서 자리수 별로 감소하는 수 찾기
		for (long i = mod - 1; i >= 0; i--) {
			long newValue = num * 10 + i;
			findDescendingNum(newValue);
		}
	}

	private static void answer() {
		System.out.println(answer);
	}
}