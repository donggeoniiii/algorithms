// 공유기 설치

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
		int c = Integer.parseInt(st.nextToken());

		int[] houses = new int[n];
		for (int i = 0; i < n; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}

		// 집을 차례대로 정렬
		Arrays.sort(houses);

		// 거리를 바탕으로 가장 긴 최단거리 찾기 -> 처음으로 안되는 지점을 찾으면 됨
		int start = 1;
		int end = houses[n-1] - houses[0] + 1;

		while (start < end) {

			int mid = (start + end) / 2;

			// 해당 거리에서 c개 이상 설치가 가능하면
			if (countRouter(houses, mid) >= c) {
				// 더 늘릴 수 있는지 체크
				start = mid + 1;
			} else {
				end = mid;
			}
		}

		// 최소거리 출력
		System.out.println(end - 1);
	}

	private static int countRouter(int[] houses, int length) {
		int count = 1; // 첫번째 집에 설치하고 시작
		int prevHouse = houses[0];

		for (int i = 1; i < houses.length; i++) {
			int curLength = houses[i] - prevHouse;

			if (curLength < length) continue;

			count++;
			prevHouse = houses[i];
		}

		return count;
	}
}