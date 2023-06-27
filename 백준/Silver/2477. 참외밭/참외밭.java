// 참외밭

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 단위면적당 나는 참외의 개수
		int K = input.nextInt();
		
		// 정보를 저장할 배열
		int[] lines = new int[6];
	
		// 길이정보 입력
		int max = Integer.MIN_VALUE;
		int maxIdx = -1;
		for (int idx = 0; idx < 6; idx++) {
			// 방향 정보는 중요하지 않다
			int dir = input.nextInt();
			
			// 길이 정보만 저장, 가장 긴 길이를 찾을 때까지
			int cur = input.nextInt();

			lines[idx] = cur;	
			
			if (cur > max) {
				maxIdx = idx;
				max = cur;
			}
		}
		
		// max값을 기준으로 길이 계산
		int r1 = (maxIdx -1 >= 0) ? maxIdx-1 : 5;
		int r2 = (maxIdx +1 < 6) ? maxIdx+1 : 0;
		int c1 = (maxIdx -2 >= 0) ? maxIdx-2 : (6 + (maxIdx-2));
		int c2 = (maxIdx +2 < 6) ? maxIdx+2 : (maxIdx+2 -6);
		
		int answer = K * (lines[r1]*lines[c1] + lines[r2]*lines[c2]);
		
		System.out.println(answer);
		
	}
}