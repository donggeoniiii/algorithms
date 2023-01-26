import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		input.nextLine(); // 개행문자 제거
		int min = 1000000;
		int max = -1000000;
		for (int i = 0; i < N; i++) {
			int num = input.nextInt();
			if (num > max) max = num;
			if (num < min) min = num;
		}
		System.out.printf("%d %d", min, max);
		
	}
}
