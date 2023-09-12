// 두 수의 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 수열의 크기
		int n = Integer.parseInt(br.readLine());

		// 수열 입력하면서 최댓값 찾기
		int[] input = new int[n];
		HashSet<Integer> nums = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < n; idx++) {
			input[idx] = Integer.parseInt(st.nextToken());
			nums.add(input[idx]);
		}

		// 합해서 나와야 하는 값
		int x = Integer.parseInt(br.readLine());

		// 수열의 크기가 1인 경우는 자명함
		if (n == 1) {
			System.out.println(0);
		} else {
			// 수 등장 여부를 가지고 카운트하기
			int answer = 0;

			// a + b = x를 만족하는 (a,b)를 찾으려면, a가 있을 떄 x-a도 있으면 되니까
			for (int i = 0; i < n; i++) {
				if (nums.contains(x - input[i])) {
					answer++;
				}
			}

			// 정답 출력
			System.out.println(answer / 2);
		}
	}
}