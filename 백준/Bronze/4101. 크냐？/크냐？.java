// 크냐????

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while (true) {
			
			// 입력받기
			st = new StringTokenizer(input.nextLine());
			int testcase1 = Integer.parseInt(st.nextToken());
			int testcase2 = Integer.parseInt(st.nextToken());
			
			// 만약 입력이 "0 0"이면 종료
			if (testcase1 == 0 && testcase2 == 0) break;
			
			// 입력 1이 2보다 크면 yes, 아니면 no 출력
			if (testcase1 > testcase2) sb.append("Yes\n");
			else sb.append("No\n");
		}
		
		// 정답 출력
		System.out.println(sb.toString());
		input.close();
	}
}