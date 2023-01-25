import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] line = input.nextLine().split("");
		int idx = 0;
		while (idx < line.length) {
			System.out.printf("%s", line[idx++]);
			if (idx % 10 == 0) System.out.println();
		}
	}
}
