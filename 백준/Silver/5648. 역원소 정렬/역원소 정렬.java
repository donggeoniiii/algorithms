// 역원소정렬

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long[] nums = new long[n];

		int idx = 0;
		while (idx < n) {
			if (st.hasMoreTokens()) {
				String next = st.nextToken();
				StringBuilder reverseNum = new StringBuilder();

				for (int i = next.length() - 1; i >= 0; i--) {
					reverseNum.append(next.charAt(i));
				}
				nums[idx++] = Long.parseLong(reverseNum.toString());
			}
			else {
				st = new StringTokenizer(br.readLine());
			}
		}

		Arrays.sort(nums);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(nums[i]).append("\n");
		}

		System.out.println(sb);
	}
}