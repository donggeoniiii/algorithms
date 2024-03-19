// 접두사 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[][] next;
	private static final int ROOT = 1;
	private static int unused = ROOT + 1;

	private static final int MAX = 501 * 10000;

	// 문자 to 정수
	private static int charToInt(char c) {
		return c - 'a';
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		next = new int[MAX][26];
		for (int i = 0; i < MAX; i++) {
			Arrays.fill(next[i], -1);
		}

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			insert(s);
		}

		int answer = 0;
		for (int i = 0; i < m; i++) {
			String s = br.readLine();
			if (find(s)) {
				answer++;
			}
		}

		System.out.println(answer);
	}

	private static void insert(String s) {
		int cur = ROOT;

		for (char c : s.toCharArray()) {
			int ndx = charToInt(c);
			if (next[cur][ndx] == -1) {
				next[cur][ndx] = unused++;
			}

			cur = next[cur][ndx];
		}
	}


	private static boolean find(String s) {
		int cur = ROOT;

		for (char c : s.toCharArray()) {
			int ndx = charToInt(c);
			if (next[cur][ndx] == -1) {
				return false;
			}

			cur = next[cur][ndx];
		}

		return true;
	}
}