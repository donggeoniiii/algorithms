// 용액 합성하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] solutions = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			solutions[i] = Integer.parseInt(st.nextToken());
		}

		int answer = Integer.MAX_VALUE;

		int start = 0;
		int end = n-1;

		while (start < end) {
			int result = solutions[start] + solutions[end];

			// 절댓값으로 최솟값 비교
			if (Math.abs(result) < Math.abs(answer)) {
				answer = result;
			}
			
			if (result == 0) {
				answer = 0;
				break;
			}
			else if (result > 0) {
				end--;
			}
			else {
				start++;
			}
		}

		System.out.println(answer);
	}

}