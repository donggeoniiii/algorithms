// 전화번호 목록

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static final int ROOT = 1;
	private static final int MAX = 10000 * 10 + 5;

	private static int unused = ROOT + 1;

	private static int[][] next;

	private static boolean[] checked;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			unused = ROOT + 1;
			
			next = new int[MAX][10];
			for (int i = 0; i < MAX; i++) {
				Arrays.fill(next[i], -1);
			}
			checked = new boolean[MAX];


			boolean isConsistent = true;
			for (int i = 0; i < n; i++) {
				if (insertAndIsDuplicate(br.readLine())) {
					isConsistent = false;
				}
			}

			sb.append((isConsistent) ? "YES" : "NO").append("\n");
		}

		System.out.println(sb);
	}

	private static boolean insertAndIsDuplicate(String s) {
		int cur = ROOT;

		for (char c : s.toCharArray()) {
			int idx = c - '0';
			if (next[cur][idx] == -1) {

				next[cur][idx] = unused++;
			}
			cur = next[cur][idx];

			// 만약 해당 지점에 체크가 돼있으면 이번 번호는 어떤 번호를 포함하므로
			if (checked[cur]) return true;
		}

        if (cur != unused - 1) return true;
		checked[cur] = true;
		return false;
	}
}