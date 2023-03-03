// 과제 안 내신 분?

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 출석표, 1-indexed 하기위해 +1
		int[] part = new int[31];
		
		// 출석 부르기, 28명 온거니까
		for (int i = 1; i <= 28; i++) {
			int std = input.nextInt();
			part[std]++;
		}
		
		// 안온사람 찾기
		for (int i = 1; i <= 30; i++) {
			// 안왔으면 이르기
			if (part[i] == 0) System.out.println(i);
		}
	}
}