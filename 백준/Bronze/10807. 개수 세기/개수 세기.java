// 개수세기

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 정수의 개수
		int N = input.nextInt();
		
		// 정수 입력할 배열
		int[] nums = new int[N];
		
		// 정수 입력
		for (int i = 0; i < N; i++) nums[i] = input.nextInt();
		
		// 찾을 정수
		int v = input.nextInt();
		
		// 찾을 정수의 개수
		int numCnt = 0;
		
		// 찾으려는 정수 개수 세기
		for (int num: nums) if (num == v) numCnt++;
		
		// 정답 출력
		System.out.println(numCnt);
	}
}