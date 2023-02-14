
import java.util.Scanner;

public class Solution {
	// 선택정렬
	static void selectonSort(int[] arr) {
		int n = arr.length;
		
		// pass는 n-1번 일어나므로
		for (int i = 0; i < n-1; i++) {
			int minIdx = i;
			
			// 시작점보다 큰 index에서 더 값이 작은 index를 찾으면
			for (int j = i; j < n; j++) {
				if (arr[minIdx] > arr[j]) {
					minIdx = j;
				}
			}
			
			// 정렬되지 않은 가장 작은 index와 자리바꾸기
			int temp = arr[minIdx];
			arr[minIdx] = arr[i];
			arr[i] = temp;
		}
	
		
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// 숫자 입력은 항상 10개
		int[] arr = new int[10];
	
		int T = input.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			
			for (int i = 0; i < 10; i++) {
				arr[i] = input.nextInt();
			}
			input.nextLine(); // 개행문자 제거
			
			// 선택정렬
			selectonSort(arr);
			
			// 평균 계산
			float sum = 0;
			for (int i = 1; i < 9; i++) {
				sum += arr[i];
			}
			
			System.out.printf("#%d %d\n", test_case, Math.round(sum / 8));
		}
	}
}