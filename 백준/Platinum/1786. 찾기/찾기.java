// 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String t = br.readLine();
		String p = br.readLine();

		int[] failure = new int[p.length()];

		int j = 0;

		// 실패함수 채우기
		for (int i = 1; i < p.length(); i++) {

			while (j > 0 && p.charAt(i) != p.charAt(j)) {
				j = failure[j-1];
			}

			if (p.charAt(i) == p.charAt(j)){
				failure[i] = ++j;
			}
		}

		// 패턴이 등장하는 시점 세기
		j = 0;
		ArrayList<Integer> pattern = new ArrayList<>();
		for (int i = 0; i < t.length(); i++) {
			while (j > 0 && t.charAt(i) != p.charAt(j)) {
				j = failure[j-1];
			}

			if (t.charAt(i) == p.charAt(j)) {
				j++;
			}

			if (j == p.length()) {
				pattern.add(i - (p.length() - 1));
				j = failure[j-1];
			}
		}


		StringBuilder sb = new StringBuilder();
		sb.append(pattern.size()).append("\n");
		for (int idx : pattern) {
			sb.append(idx + 1).append(" ");
		}
		System.out.println(sb);
	}
}