// 단어공부

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 알파벳 등장횟수 카운트
		int[] words = new int[26];
		
		// 단어 입력(소문자 통일)
		String s = input.nextLine().toLowerCase();
		
		// 제일 많이 등장한 단어 index 
		int charMax = 0;
		
		// 등장횟수 카운트, 아스키코드 이용 ('a' = 97 / 'A' = 65)
		for (char c : s.toCharArray()) {
			words[c-97]++;
		}
		
		// 가장 많이 등장한 단어 index 찾기
		for (int idx = 0; idx < 26; idx++) {
			if (words[idx] > words[charMax]) charMax = idx;
		}
		
		// 만약 최고값을 가지는 알파벳이 여러 개인지 확인
		boolean multiple = false;
		for (int idx = 0; idx < 26; idx++) {
			if (idx != charMax && words[idx] == words[charMax]) {
				multiple = true;
				break;
			}
		}
		
		// 최고값을 가지는지 여부에 따라 물음표 또는 해당 알파벳 대문자 출력
		if (multiple) System.out.println("?");
		else System.out.println((char) (charMax + 65));
		
		
	}	
}