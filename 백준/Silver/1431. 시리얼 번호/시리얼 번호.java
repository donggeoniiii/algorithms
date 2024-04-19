// 시리얼 번호

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		String[] serials = new String[n];
		for (int i = 0; i < n; i++) {
			serials[i] = br.readLine();
		}

		// 1. 일단 String 길이순 정렬
		Arrays.sort(serials, Comparator.comparingInt(String::length)
			// 2. 길이가 같으면 String에 있는 숫자의 합으로 정렬
			.thenComparingInt((String o) -> {
			int total = 0;
			for (int i = 0; i < o.length(); i++) {
				if (o.charAt(i) >= 'A' && o.charAt(i) <= 'Z') continue;
				int num = o.charAt(i) - '0';
				total += num;
			}
			return total;
			})
			// 3. 이것도 같으면 사전순
			.thenComparing(Comparator.naturalOrder()));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(serials[i]).append("\n");
		}
		System.out.println(sb);
	}
}