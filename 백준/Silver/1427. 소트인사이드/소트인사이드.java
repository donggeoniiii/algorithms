// 소트인사이드

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 수 입력받기
		String input = br.readLine();
		ArrayList<Character> sortedList = new ArrayList<>();
		for (int i = 0; i < input.length(); i++) {
			sortedList.add(input.charAt(i));
		}
		// 정렬
		Collections.sort(sortedList);

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = sortedList.size() - 1; i >= 0; i--) {
			sb.append(sortedList.get(i));
		}
		System.out.println(sb);
	}
}