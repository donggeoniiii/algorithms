// 중간값 구하기

import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	static void selectionSort(int[] arr) {
		int n = arr.length;
		
		for (int i = 0; i < n-1; i++) {
			int minIdx = i;
			for (int j = i; j < n; j++) {
				if (arr[j] < arr[minIdx]) {
					minIdx = j;
				}
			}
			int temp = arr[minIdx];
			arr[minIdx] = arr[i];
			arr[i] = temp;
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// 숫자 개수 입력
		int T = Integer.parseInt(input.nextLine());
		// 숫자를 T개만큼 담을 배열 생성
		int[] nums = new int[T];
		StringTokenizer st = new StringTokenizer(input.nextLine());
		
		// 배열에 숫자 담기
		for (int i = 0; i < T; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 선택정렬
		selectionSort(nums);
		
		// 중간값 출력
		System.out.println(nums[T/2]);
	}
}
