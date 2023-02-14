package swea1206; // view

import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 조망권이 보장되려면 앞뒤 2칸으로부터 방해를 받지 않아야 하므로
 * (해당 건물의 높이) - (앞뒤 2칸의 건물 중 최대 높이)가 곧 해당 건물에서 조망권이 보장되는 층수.
 * 배열을 순회하면서 해당 index에서 +-2칸의 높이를 확인하고 최대값 구하기
 * 만약 더 큰 층을 찾으면 즉시 해당 index에서 탐색 종료
*/
public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			// N: 건물의 수
			int N = Integer.parseInt(input.nextLine());
			// StringTokenizer를 이용해 공백으로 입력 구분
			st = new StringTokenizer(input.nextLine());
			// 건물의 높이 배열 생성
			int[] building = new int[N];
			for (int i = 0; i < N; i++) {
				int height = Integer.parseInt(st.nextToken());
				building[i] = height;
				
			}
			
			// 조망권이 확보된 층수의 합 
			int sum = 0;
			
			// 건물의 높이가 0이 아닌 index인 2 ~ N-3까지 조망권 확인
			for (int i = 2; i < N-2; i++) {
				
				// 앞뒤 2칸에 있는 빌딩 중 최고 높이
				int max = 0;
				
				for (int j = i-2; j <= i+2; j++) {
					// 자기 위치 제외
					if (i == j) continue;
					
					// 앞뒤 2칸 돌면서 최대층수 계산
					if (building[j] > max) max = building[j];
					
					// 만약 max가 현재 건물 높이보다 크면 더 큰 건물이 있는 것이므로 조망권이 보장되는 층 x
					if (max > building[i]) {
						max = building[i];
						break;
					}
				}
				
				// 해당 건물에서 조망권이 보장되는 층수 체크
				int view = building[i] - max;
				
				// 해당 index의 조망권 보장 층수 덧셈
				sum += view;
				
			}
			// 정답 출력
			System.out.printf("#%d %d\n", tc, sum);
		}
	}
}
