// 큰 수 A+B

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 숫자 string으로 입력
		String numA = input.next();
		String numB = input.next();

		// 두 개의 수 중 더 자릿수가 많은 값 기준으로 배열 선언
		int N = Math.max(numA.length(), numB.length());

		// 마지막 자리수 올림을 감안해 index+1
		int[] A = new int[N + 1];
		int[] B = new int[N + 1];

		// 맨 뒷자리부터 하나씩 저장
		for (int i = numA.length() - 1, idx = 0; i >= 0; i--, idx++) {
			A[idx] = numA.charAt(i) - '0';
		}
		for (int i = numB.length() - 1, idx = 0; i >= 0; i--, idx++) {
			B[idx] = numB.charAt(i) - '0';
		}

		// 덧셈
		for (int i = 0; i < N; i++) {
			int sum = A[i] + B[i];
			A[i] = sum % 10;
			// 올림값 반영
			A[i + 1] += (sum / 10);
		}

		// 출력
		// 마지막 자리가 올림이 안됐을 수도 있으니까 0이 아닐 때만 
		if (A[N] != 0) {
			sb.append(A[N]);
		}
		for (int i = N - 1; i >= 0; i--) {
			sb.append(A[i]);
		}

		// 출력
		System.out.println(sb);
	}
}