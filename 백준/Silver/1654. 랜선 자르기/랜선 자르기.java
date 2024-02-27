// 랜선 자르기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 가지고 있는 랜선의 개수, 필요한 랜선의 개수
	static int k, n;

	// 랜선의 길이
	static int[] lineLength;

	// 랜선 최고길이
	static int maxLength;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		lineLength = new int[k];
		maxLength = -1;
		for (int i = 0; i < k; i++) {
			lineLength[i] = Integer.parseInt(br.readLine());
			maxLength = Math.max(lineLength[i], maxLength);
		}
	}

	private static void solution() {
		long start = 1;
		long end = maxLength;

		while (start < end) {
			long mid = (start + end + 1) / 2; // 밑이 0이 되는 경우 방지

			// 생길 수 있는 랜선 수
			long count = 0;
			for (int i = 0; i < k; i++) {
				count += lineLength[i] / mid;
			}

			// n개 만들 수 있는 경우면 일단 가능하니까 반영, 더 길게 만들 수 있는지 확인
			if (count >= n) {
				start = mid;
			}
			// 최대로 만들 수 있는 길이보다 적으면 더 만들어야 하니까
			else {
				end = mid - 1;
			}
		}

		System.out.println(start);
	}
}