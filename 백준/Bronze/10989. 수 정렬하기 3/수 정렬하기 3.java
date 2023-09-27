// 수 정렬하기 3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 수의 개수
		int n = Integer.parseInt(br.readLine());

		// 수를 저장할 배열
		int[] nums = new int[n];

		// 값 저장
		for (int i = 0; i < n; i++) {
			nums[i] =
                
                Integer.parseInt(br.readLine());
		}

		// 정렬
		Arrays.sort(nums);

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(nums[i]).append("\n");
		}
		System.out.println(sb);
	}
}