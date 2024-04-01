// 좌표 정렬하기 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		List<int[]> loc = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			loc.add(new int[] {x, y});
		}

		loc.sort(Comparator.comparingInt((int[] o) -> o[1]).thenComparingInt(o -> o[0]));

		StringBuilder sb = new StringBuilder();
		for (int[] l : loc) {
			sb.append(l[0]).append(" ").append(l[1]).append("\n");
		}
		System.out.println(sb);
	}
}