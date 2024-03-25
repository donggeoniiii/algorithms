// 보석 도둑

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 보석의 개수, 가방의 개수
	private static int n, k;

	// 보석의 정보
	private static int[][] jewelry;

	// 가방의 무게 정보
	private static int[] bag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		jewelry = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				jewelry[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bag = new int[k];
		for (int i = 0; i < k; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}

		// 두 배열 다 정렬, 무게 우선
		Arrays.sort(jewelry, Comparator.comparingInt((int[] o) -> o[0]));
		Arrays.sort(bag);

		int jdx = 0; // 보석 index;
		long totalPrice = 0;

		// 현재 가방에 담을 수 있는 보석을 가격 순으로 정렬할 우선순위 큐
		PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

		for (int bdx = 0; bdx < k; bdx++) {
			// 이번에 담을 수 있는 보석까지 큐에 추가
			while (jdx < n && jewelry[jdx][0] <= bag[bdx]) {
				queue.offer(jewelry[jdx++][1]);
			}

			// 이번 가방에 맞는 무게의 보석이 없으면 다음으로 이동
			if (queue.isEmpty()) continue;

			// 그 중 가장 비싼 애 가방에 넣기
			totalPrice += queue.poll();
		}

		System.out.println(totalPrice);
	}
}