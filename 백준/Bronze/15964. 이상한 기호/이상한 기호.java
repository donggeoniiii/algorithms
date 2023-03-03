// 이상한 기호

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 숫자 입력
		int A = input.nextInt();
		int B = input.nextInt();
		
		// 정답 출력
		System.out.println((A+B)*(A-B));
		
	}
}