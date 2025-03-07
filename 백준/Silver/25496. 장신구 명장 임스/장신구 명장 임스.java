// 장신구 명장 임스

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] accessories = new int[n];
		for (int i = 0; i < n; i++) {
			accessories[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(accessories);

		int idx = 0;
		while (idx < n && p < 200) {
			p += accessories[idx++];
		}

		System.out.println(idx);
	}
}