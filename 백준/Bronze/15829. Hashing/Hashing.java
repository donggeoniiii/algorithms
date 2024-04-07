// Hashing

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	private static final int CO = 31;
	private static final int MOD = 1234567891;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int l = Integer.parseInt(br.readLine());
		String input = br.readLine();

		BigInteger total = new BigInteger("0");
		BigInteger pow = new BigInteger("1");
		for (int i = 0; i < input.length(); i++) {
			total = total.add(BigInteger.valueOf(input.charAt(i) - 'a' + 1).multiply(pow));
			pow = pow.multiply(BigInteger.valueOf(CO));
		}

		total = total.mod(BigInteger.valueOf(MOD));

		System.out.println(total);
	}
}