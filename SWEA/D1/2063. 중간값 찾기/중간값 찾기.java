
import java.util.Scanner;

public class Solution{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 입력갯수 들어간다.
		int n = sc.nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
			// 우리가 배운 정렬을 사용해서 값을 입력하자
		}
		// 값들이 들어갔다.
		int longg = arr.length;
		// n-1만큼 돌면서 최소 값을 찾느다.
		for (int i = 0; i < n - 1; i++) {
			//최소값을 i라고 잡는다.
			int minIdx = i;
			//매패스마다 첫번째 원소를 최소값으로 잡고
			
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIdx]) {
					minIdx = j;
				}
			}
			// swap
			int temp = arr[minIdx];
			arr[minIdx] = arr[i];
			arr[i] = temp;
		}
		System.out.println(arr[(n/2)]);
	}
	
}