// 엄청난 부자

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 돈의 양
		BigInteger total = new BigInteger(st.nextToken());

		// 생명체의 수
		BigInteger cnt = new BigInteger(st.nextToken());

		// 정답 출력
		System.out.println(total.divide(cnt));
		System.out.println(total.remainder(cnt));
	}
}