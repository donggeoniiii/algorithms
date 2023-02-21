import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringTokenizer st;
		StringBuilder sb;
		
		// 테스트케이스 개수
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			
			// 반복횟수, 문자열 S 입력
			st = new StringTokenizer(input.nextLine());
			
			// 반복 횟수
			int R = Integer.parseInt(st.nextToken());
			
			// 문자열
			String S = st.nextToken();
			
			// 반복된 문자열
			sb = new StringBuilder();
			
			// 반복
			for (char c : S.toCharArray()) {
				for (int i = 1; i <= R; i++) {
					sb.append(c);
				}
			}
			
			// 정답 출력
			System.out.println(sb.toString());
		}
	}
}