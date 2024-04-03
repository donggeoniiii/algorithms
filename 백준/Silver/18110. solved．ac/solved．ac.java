// solved.ac

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 의견 수
		int n = Integer.parseInt(br.readLine());

		int[] comment = new int[n];
		int answer = 0;
		for (int i = 0; i < n; i++) {
			comment[i] = Integer.parseInt(br.readLine());
			answer += comment[i];
		}

		// 정렬
		Arrays.sort(comment);

		// 절삭평균 반영
		int cut = (int) Math.round(n * 0.15);
		for (int i = 0; i < cut; i++) {
			answer -= comment[i];
			answer -= comment[n-1 - i];
		}

		int total = n - cut * 2;
		System.out.println(Math.round((double) answer / total));
	}
}