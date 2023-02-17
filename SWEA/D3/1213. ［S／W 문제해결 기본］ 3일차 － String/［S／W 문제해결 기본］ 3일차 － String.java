 //string

import java.util.Scanner;

public class Solution {
	static int search(String string, String key) {
		int N = string.length();
		int K = key.length();

		char[] strArr = string.toCharArray();
		char[] keyArr = key.toCharArray();
		
		int cnt = 0;
		
		for (int i = 0; i < N-K+1; i++) {
			// 각 index마다 앞으로 K-1개의 index만큼 이동해보면서 확인
			boolean match = true;
			for (int j = 0; j < K; j++) {
				if (strArr[i+j] != keyArr[j]) {
					match = false;
					break;
				}
			}
			if (match) cnt++;
		}
		
		
		return cnt;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(input.nextLine()); 
			String key = input.nextLine(); // 검색할 문자열
			String string = input.nextLine(); // 찾을 문자열
			
			System.out.printf("#%d %d\n", T, search(string, key));
		}
	}
}
