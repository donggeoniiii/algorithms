// 학점계산

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 점수판
		HashMap<String, Integer> grade = new HashMap() {{
			put("A+", 4.3);
			put("A0", 4.0); 
			put("A-", 3.7); 
			put("B+", 3.3);
			put("B0", 3.0); 
			put("B-", 2.7); 
			put("C+", 2.3); 
			put("C0", 2.0); 
			put("C-", 1.7); 
			put("D+", 1.3);
			put("D0", 1.0); 
			put("D-", 0.7);
			put("F", 0.0);
		}};
		
		// 성적 입력
		String s = input.nextLine();
		
		System.out.println(grade.get(s));
		
	}

}