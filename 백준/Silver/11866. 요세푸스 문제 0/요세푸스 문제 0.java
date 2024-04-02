// 요세푸스 문제 0

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		ArrayDeque<Integer> people = new ArrayDeque<>();

		for (int i = 1; i <= n; i++) {
			people.add(i);
		}

		// 하나씩 빼면서 출력하기
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while (!people.isEmpty()) {
			// k-1번 턴 넘기기
			for (int i = 1; i <= k-1; i++) {
				int cur = people.pollFirst();
				people.addLast(cur);
			}

			// k번째 사람 제거
			int cur = people.pollFirst();
			if (people.isEmpty()) sb.append(cur).append(">");
			else sb.append(cur).append(", ");
		}

		// 출력
		System.out.println(sb);
	}
}