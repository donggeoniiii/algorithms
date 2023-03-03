// 대소문자바꾸기
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 숫자 입력받기
		String[] s = input.nextLine().split("");
		
		// 대소문자 확인해서 변환(아스키코드 이용)
		for (String word : s) {
			if (word.charAt(0) >= 65 && word.charAt(0) <= 90) sb.append(word.toLowerCase());
			else if (word.charAt(0) >= 97 && word.charAt(0) <= 122) sb.append(word.toUpperCase());
			else continue;
		}
		
		// 정답 출력
		System.out.println(sb.toString());
	}
}