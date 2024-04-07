// Hashing

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int CO = 31;
	private static final int MOD = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int l = Integer.parseInt(br.readLine());
		String input = br.readLine();

		long total = 0;
		for (int i = 0; i < input.length(); i++) {
			total += (long)((input.charAt(i) - 'a' + 1) * Math.pow(CO, i));
		}

		System.out.println(total % MOD);
	}
}