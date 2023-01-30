import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = 2;
		int N = Integer.parseInt(br.readLine());
		while (N != 1) {
			if (N % num == 0) {
				N /= num;
				System.out.println(num);
			}
			else num++;
		}
	}
}
