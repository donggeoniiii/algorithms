// 숫자의 개수

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 숫자 계산
		String num = input.nextInt() * input.nextInt() * input.nextInt() + "";
		
		// 숫자별 몇번 나왔는지 카운트하는 배열
		int[] numCnt = new int[10];
		
		// 순회하면서 카운트
		for (char c : num.toCharArray()) {
			numCnt[c-48]++;
		}
		
		// 출력
		for (int cnt : numCnt) System.out.println(cnt);
		
	}
}