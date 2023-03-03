// 문자열

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 개수
		int T = input.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 테케별 문자열
			String s = input.next();
			
			// 정답에 추가
			sb.append(s.charAt(0)).append(s.charAt(s.length()-1)).append("\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
	}
}