// 로프

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] rope = new int[n];
		for (int i = 0; i < n; i++) {
			rope[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(rope);

		int maxWeight = 0;
		for (int i = 0; i < n; i++) {
			// 해당 밧줄을 사용해서 들 수 있는 최다 중량
			int curWeight = rope[i] * (n - i);

			maxWeight = Math.max(maxWeight, curWeight);
		}

		System.out.println(maxWeight);
	}
}