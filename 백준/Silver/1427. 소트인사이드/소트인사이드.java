// 소트인사이드

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 수 입력받기
		char[] input = br.readLine().toCharArray();

		// 정렬
		Arrays.sort(input);

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = input.length - 1; i >= 0; i--) {
			sb.append(input[i]);
		}
		System.out.println(sb);
	}
}