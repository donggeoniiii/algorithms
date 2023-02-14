// Flatten

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	// 처음엔 전범위
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
		StringTokenizer st;
		
		// 정렬
		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(input.nextLine());
			
			int[] boxes = new int[100];
			int answer = 0;
			
			st = new StringTokenizer(input.nextLine());
			
			for (int i = 0; i < 100; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}

			selectionSort(boxes);
	
			for (int i = 0; i < T; i++) {
				int max = boxes[99];
				int min = boxes[0];
				// 최고점, 최저점 차이 비교
				answer = max - min;
				// 차이가 1 또는 0이면 덤프를 수행할 수 없으므로 종료, 그때의 높이차 반환
				if (max - min <= 1) {
					break;
				}
				// 끝나지 않으면 평탄화 작업 진행
				else {
					boxes[99] -= 1;
					boxes[0] += 1;
					selectionSort(boxes);
					answer = boxes[99] - boxes[0];
				}
			}
			
			System.out.printf("#%d %d\n", tc, answer);
		}
	}
	
}