 // sum

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			// 배열에 테스트케이스 입력
			int T = Integer.parseInt(input.nextLine());
			int[][] arr = new int[100][100];
			for (int r = 0; r < 100; r++) {
				st = new StringTokenizer(input.nextLine());
				for (int c = 0; c < 100; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
		
			// 행 / 열 / 대각선별 최대값 저장 변수 생성
			int rowmax = 0;
			int colmax = 0;
			// 대각선은 두 개
			int dia1 = 0;
			int dia2 = 0;
				
			// 행별 합계와 열별 합계는 같은 반복문에서 구하기 어려우므로 행만 따로 탐색
			for (int r = 0; r < 99; r++) {
				// 행 합계 초기화
				int rowsum = 0;
				// index의 대칭 구조 이용해서 100개의 배열 중 절반만 돌기
				for (int c = 0; c < 50; c++) {
					// 행별 합계 계산
					rowsum += arr[r][c] + arr[r][99-c];
				}
				// rowmax 갱신여부 판별
				if (rowsum > rowmax) rowmax = rowsum;
			}
			
			// 열별 탐색
			// 반복문에서 위치 반대로
			for (int c = 0; c < 99; c++) {
				// 열 합계 초기화
				int colsum = 0;
				// index의 대칭 구조 이용해서 100개의 배열 중 절반만 돌기
				for (int r = 0; r < 50; r++) {
					// 열별 합계 계산
					colsum += arr[r][c] + arr[99-r][c];
				}
				// colmax 갱신여부 판별
				if (colsum > colmax) colmax = colsum;	
			}
			
			// 좌상 -> 우하로 내려가는 대각선
			// row == col이면 좌상 -> 우하로 내려가는 대각선 위에 있으므로 dia1에 더해주기
			// index의 대칭 이용하여 row만, index의 절반만 순회
			for (int r = 0; r < 50; r++) {
				dia1 += arr[r][r] + arr[99-r][99-r];
			}
			
			// 우상 -> 좌하로 내려가는 대각선
			// row + col == 99이면 우상 -> 좌하로 내려가는 대각선 위에 있으므로 dia2에 더해주기
			// index의 대칭 이용하여 row만, index의 절반만 순회
			for (int r = 50; r < 99; r++) {
				dia2 += arr[r][99-r] + arr[99-r][r];
			}
			
			// colmax, rowmax, dia1, dia2 정렬을 통해 크기 비교;
			int[] maxArr = {colmax, rowmax, dia1, dia2};	
			Arrays.sort(maxArr);
			
			// 정답 출력
			System.out.printf("#%d %d\n", T, maxArr[3]);
		}
		
	}
}

