// 나머지

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 단어를 넣을 배열
		int[] nums = new int[10];

		// 나머지별로 몇번 나오는지 세는 배열
		int[] mods = new int[42];
		

		for (int tc = 0; tc < 10; tc++) {
			// 42로 나눈 값 입력
			nums[tc] = input.nextInt() % 42;
			
			// 해당 나머지배열 index +1
			mods[nums[tc]]++;
		}
		
		// 나온 나머지가 몇갠지 카운트
		int modCnt = 0;
		
		for (int num = 0; num < 42; num++) {
			
			// 출현횟수가 0이 아니면 카운트
			if (mods[num] != 0) modCnt++;
			
		}
		
		// 정답 출력
		System.out.println(modCnt);
		
	}
}