// 소수찾기

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 수의 개수
		int N = input.nextInt();

		// 정렬할 배열
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = input.nextInt();
		}

		// 정렬
		Arrays.sort(nums);

		// 출력
		for (int i = 0; i < N; i++) {
			sb.append(nums[i]).append("\n");
		}

		System.out.println(sb);
	}
}