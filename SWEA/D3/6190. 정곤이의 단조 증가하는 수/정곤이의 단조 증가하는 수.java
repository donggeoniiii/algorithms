// 정곤이의 단조 증가하는 수

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 숫자의 개수
			int N = input.nextInt();
			
			// 입력받은 숫자들의 배열
			int[] inputNums = new int[N];
			
			// 배열에 숫자 입력
			for (int i = 0; i < N; i++) inputNums[i] = input.nextInt();
			
			// 단조증가수 간 크기 비교
			int numMax = Integer.MIN_VALUE;
			
			// 모든 숫자 사이의 곱 계산
			for (int i = 0; i < N; i++) {
				for (int j = i; j < N; j++) {
					if (i != j) { // (자기자신과의 곱 제외)
						// 단조증가수인지 확인하는 변수
						boolean flag = true;
						
						String num = inputNums[i] * inputNums[j] + "";
						
						// 해당 수가 단조증가수인지 확인
						for (int idx = 1; idx < num.length(); idx++) {
							
							// 만약 단조증가 수가 아니라면 표시
							if (num.charAt(idx-1)-'0' > num.charAt(idx)-'0') flag = false;
						}
						
						// 단조증가수라면 현재 최댓값과 비교해서 더 크면 갱신
						if (flag && Integer.parseInt(num) > numMax) numMax = Integer.parseInt(num); 
						
					}
				}
			}
			
			// 조건에 맞춰 정답 추가
			if (numMax < 0) sb.append(-1).append("\n");
			else sb.append(numMax).append("\n"); 
			
		}
		// 정답 출력
		System.out.println(sb.toString());
	}
}