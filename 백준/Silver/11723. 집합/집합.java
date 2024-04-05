// 집합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int m = Integer.parseInt(br.readLine());

		// 집합에 숫자가 있는지 확인
		boolean[] isExist = new boolean[20+1];

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			String cmd = st.nextToken();
			int elm = 0;
			if (st.hasMoreTokens()) {
				elm = Integer.parseInt(st.nextToken());
			}

			switch (cmd) {
				case "add":
					isExist[elm] = true;
					break;
				case "remove":
					isExist[elm] = false;
					break;
				case "check":
					sb.append(isExist[elm] ? 1 : 0).append("\n");
					break;
				case "toggle":
					isExist[elm] = !isExist[elm];
					break;
				case "all":
					for (int j = 1; j <= 20; j++) {
						isExist[j] = true;
					}
					break;
				case "empty":
					for (int j = 1; j <= 20; j++) {
						isExist[j] = false;
					}
					break;
			}
		}

		System.out.println(sb);
	}
}