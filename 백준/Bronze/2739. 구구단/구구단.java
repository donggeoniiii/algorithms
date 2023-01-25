import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		for (int i = 0; i < 9; i++) {
			System.out.printf("%-2d* %-2d= %-2d\n", N, i+1, N*(i+1));
		}
	}
}
