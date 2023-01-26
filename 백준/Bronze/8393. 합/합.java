import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int sum = 0; // 합계
		for (int i = 0; i < n+1; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}