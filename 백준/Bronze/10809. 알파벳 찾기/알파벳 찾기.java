import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 문자열 입력
		String s = input.nextLine();
		
		// 알파벳 a~z를 배열로 생성
		int[] wdx = new int[26];
		
		// index 전부 -1로 초기화
		for (int i = 0; i < 26; i++) {wdx[i] = -1;}
		
		// 아스키코드를 이용해 배열의 index로 사용
		for (int i = 0; i < s.length(); i++) {
			// 만약 각각의 알파벳이 문자열 안에 있고 처음 나왔다면 index 입력
			if (wdx[s.charAt(i)-97] == -1) wdx[s.charAt(i) - 97] = i; 
		}
		
		// 정답 출력을 위해  StringBuilder 사용
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 26; i++) sb.append(wdx[i] + " ");
		
		// 정답 출력
		System.out.println(sb.deleteCharAt(sb.length()-1).toString());
	
	}
}