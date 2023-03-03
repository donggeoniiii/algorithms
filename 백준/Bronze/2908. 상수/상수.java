// 상수

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		// 수 2개 받기(String으로)
		String num1 = input.next();
		String num2 = input.next();
		String bigNum = "";
		
		// 앞자리부터 비교
		for (int digit = 2; digit >= 0; digit--) {
			
			// 만약 해당자리 숫자가 더 크면 쟤가 큼
			if (num1.charAt(digit) > num2.charAt(digit)) {
				bigNum = num1; break;
			}
			else if (num1.charAt(digit) < num2.charAt(digit)) {
				bigNum = num2; break;
			}
			else continue;
		}
		
		// 거꾸로 출력
		for (int digit = 2; digit >= 0; digit--) System.out.print(bigNum.charAt(digit));
		
		
	}

}