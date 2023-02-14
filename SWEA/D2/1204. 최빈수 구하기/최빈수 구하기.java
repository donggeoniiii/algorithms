package swea1204; // 최빈값 구하기

import java.util.Scanner;
import java.util.StringTokenizer;

// 정렬을 한다.
// mode라는 값으로 현재 최빈값이 무엇인지 저장한다.
// max라는 값으로 최빈값의 출현횟수를 기록한다.
// cnt = 1이라는 값으로 현재까지 이 값이 몇번 나왔는지 센다.
// 1~ 999까지 for문을 돌려서  
//	  만약 전과 같은 값이면 
//	    cnt 1씩 올려준다.
// 	  만약 전과 다른 값이면 
//		cnt와 max를 비교한다.
//		  만약 크거나 같으면 최빈값을 갱신한다.
//		cnt를 다시 1로 만든다.

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
		StringTokenizer st;
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// 점수 배열 생성
			int[] grades = new int[1000];
			int testCase = Integer.parseInt(input.nextLine());
			st = new StringTokenizer(input.nextLine());
			
			// 점수 입력
			for (int i = 0; i < 1000; i++) {
				grades[i] = Integer.parseInt(st.nextToken());
			}
			
			// 오름차순 정렬(selection sort)
			selectionSort(grades);

			// mode: 현재 최빈값이 무엇인지 저장한다.
			int mode = grades[0];
			// max: 현재 최빈값의 출현횟수를 기록한다.
			int max = 1;
			// cnt: 현재까지 이 값이 몇번 나왔는지 센다.
			int cnt = 1;
			
			// 1~ 999까지 for문을 돌려서  
			for (int i = 1; i < 999; i++) {
				// 먄약 전과 같으면 cnt+1;
				if (grades[i] == grades[i-1]) {
					cnt++;
				} 
				// 만약 전과 다르면
				else {
					// cnt와 max를 비교해 갱신 가능한지 확인
					if (cnt >= max) {
						// 가능시 max, mode 갱신
						max = cnt;
						mode = grades[i-1];
					}
					// 새로 카운트해야 하므로 cnt 1로 초기화
					cnt = 1;
				}
			}
			
			// 정답 출력
			System.out.printf("#%d %d\n", testCase, mode);

		}
		
	}
}
