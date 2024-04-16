// DSLR

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(findMinimum(a, b)).append("\n");
		}
		System.out.println(sb);
	}

	static String findMinimum(int a, int b) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] checked = new boolean[10000];

		// 해당 값을 만드는데 걸린 최소 커맨드 순서
		String[] command = new String[10000];
		Arrays.fill(command, "");

		queue.offer(a);
		checked[a] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			int d = (cur * 2) % 10000;
			if (!checked[d]) {
				queue.offer(d);
				checked[d] = true;
				command[d] = command[cur] + "D";

				if (d == b) break;
			}

			int s = (cur - 1 >= 0) ? (cur - 1) : 9999;
			if (!checked[s]) {
				queue.offer(s);
				checked[s] = true;
				command[s] = command[cur] + "S";

				if (s == b) break;
			}

			int l = (cur % 1000) * 10 + cur / 1000;
			if (!checked[l]) {
				queue.offer(l);
				checked[l] = true;
				command[l] = command[cur] + "L";

				if (l == b) break;
			}

			int r = cur / 10 + (cur % 10) * 1000;
			if (!checked[r]) {
				queue.offer(r);
				checked[r] = true;
				command[r] = command[cur] + "R";

				if (r == b) break;
			}
		}

		return command[b];
	}
}