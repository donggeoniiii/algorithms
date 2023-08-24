// ATM

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 사람 수
		int n = Integer.parseInt(br.readLine());

		// 사람이 돈을 인출하는데 걸리는 시간
		int[] withdrawalTime = new int[n];

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			withdrawalTime[i] = Integer.parseInt(st.nextToken());
		}

		// 빠른 사람이 먼저 뽑게 정렬
		Arrays.sort(withdrawalTime);

		// 첫 사람부터 걸리는 시간 구해서 합산
		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				sum += withdrawalTime[j];
			}
		}

		// 결과 출력
		System.out.println(sum);
	}
}