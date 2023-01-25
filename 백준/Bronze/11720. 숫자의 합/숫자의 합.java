import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int sum = 0;
		int N = input.nextInt();
		input.nextLine();
		String[] a = input.nextLine().split("");
		for (int i = 0; i < N; i++) {
			sum += Integer.parseInt(a[i]);
		}
		System.out.println(sum);
	}
}
