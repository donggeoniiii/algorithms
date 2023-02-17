// 초심자의 회문 검사

import java.util.Scanner;

// 거꾸로 읽은 문자열이 제대로 읽은 것과 같으면 회문
// 맞은 편 index의 문자와 서로 비교해 다른 부분이 있는지 비교, 하나라도 다를 경우 회문이 아님
// 가운데 index까지 도달했을 때 모두 서로 같으면 회문
public class Solution {
	// 주어진 문자열이 회문인지 확인하는 메소드
	static int isPalindrome(String string) {
		int N = string.length();
		// 자리별 비교를 위해 charArray로 변환
		char[] arr = string.toCharArray();
		
		// 문자열 절반만 돌아도 비교 가능
		for (int i = 0; i < N/2; i++) {
			// 만약 맞은편의 문자와 서로 다르면 회문이 아니므로 0 반환
			if (arr[i] != (arr[N-1-i])) return 0;
		}
		// 다 돌았는데 같으면 회문이므로 true 반환
		return 1;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int T = Integer.parseInt(input.nextLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String string = input.nextLine();
			System.out.printf("#%d %d\n", tc, isPalindrome(string));
		}
	}
}
