// 이항계수 1

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int a = 1;
		int b = 1;
		for (int i = 0; i < k; i++) {
			a *= n - i;
			b *= i + 1;
		}

		System.out.println(a / b);
	}
}