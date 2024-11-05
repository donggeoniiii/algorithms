// 주사위 게임

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int round = Integer.parseInt(br.readLine());

		int aScore = 100;
		int bScore = 100;
		for (int i = 0; i < round; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a < b) {
				aScore -= b;
			} else if (a > b) {
				bScore -= a;
			}
		}

		System.out.println(aScore);
		System.out.println(bScore);
	}
}