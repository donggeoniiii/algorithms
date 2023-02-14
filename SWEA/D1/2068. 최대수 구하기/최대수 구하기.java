
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * 입력은 항상 10개
 * max 변수를 생성해 값을 받을 때마다 최대값 갱신
 */
public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			// StringTokenizer를 통해 공백을 기준으로 숫자 구분
			st = new StringTokenizer(input.nextLine());
			
			// max값을 보관할 변수 생성
			int max = 0;
			
			// 배열 입력하면서 max값이 바뀌는지 확인
			for (int i = 0; i < 10; i++) {
				int num = Integer.parseInt(st.nextToken());
				if (num > max) max = num;
			}
			
			// 정답 출력
			System.out.printf("#%d %d\n", tc, max);
		}
		
	}
}
