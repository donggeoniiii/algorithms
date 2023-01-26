import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		// 
		for (int i = 1; i < N+1; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			for (int j = 0; j < 2*(N-i); j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		// 내려감
		for (int i = N-1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			for (int j = 0; j < 2*(N-i); j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}