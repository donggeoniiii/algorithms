// 줄 세우기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static class Line {
		int val;
		int idx;

		Line (int val, int idx) {
			this.val = val;
			this.idx = idx;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int p = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < p; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());

			sb.append(t).append(" ");

			// 물러선 횟수 카운트
			int backCnt = 0;

			int[] nums = new int[20];
			for (int i = 0; i < 20; i++) {
				nums[i] = Integer.parseInt(st.nextToken());

				// 자기 앞에 서있는 사람 중에 큰 사람만큼 모으면 
				for (int j = 0; j < i; j++) {
					if (nums[j] > nums[i]) {
						backCnt++;
					}
				}
			}

			sb.append(backCnt).append("\n");
		}

		System.out.println(sb);
	}
}