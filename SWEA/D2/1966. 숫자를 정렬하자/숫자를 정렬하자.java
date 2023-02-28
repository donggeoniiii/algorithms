// 숫자를 정렬하자

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		// 테스트케이스별 수행
		for (int tc = 1; tc <= 10; tc++) {
			
			// 양식 맞추기
			sb.append("#").append(tc).append(" ");
			
			// 정렬할 숫자 개수
			int N = input.nextInt();
			
			// 정렬할 숫자를 담을 배열
			int[] unsorted = new int[N];
			
			
			// 최댓값 저장
			int numMax = Integer.MIN_VALUE;
			
			// 정렬할 숫자 입력
			for (int i = 0; i < N; i++) {
				unsorted[i] = input.nextInt();
				
				// 최댓값 비교
				if (unsorted[i] > numMax) numMax = unsorted[i];
			}

			// 빈도수를 보관하는 배열(숫자를 그대로 index로 쓰기 위해 +1)
			int[] count = new int[numMax+1];
			
			// 빈도수 입력
			for (int num : unsorted) {
				count[num] ++;
			}
			
			// 누적합으로 변경
			for (int i = 0; i < numMax; i++) {
				count[i+1] += count[i]; 
			}
			
			// 빈도수 정렬을 위한 정렬된 배열
			int[] sorted = new int[N];
			for (int idx = N-1; idx >= 0; idx--) {
				
				// 정렬시킬 값을  count의 index로 사용
				int cdx = unsorted[idx];
				
				// 원소 하나를 넣을 때마다 해당 index의 값 -1
				count[cdx]--;
				
				// count[cdx] 자체가 sorted의 index
				sorted[count[cdx]] = cdx;
				
			}
			
			// 정답 추가
			for (int num : sorted) sb.append(num).append(" ");
			sb.append("\n");
			
		}
		
		// 정답출력
		System.out.println(sb.toString());
		
	}
}