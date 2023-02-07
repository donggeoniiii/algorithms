import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int factorial = 1;
		if (N == 0) factorial = 1;
		else {
			for (int i = 1; i <= N; i++) {
				factorial *= i;
			}	
		}
		
		System.out.println(factorial);
	}
}
