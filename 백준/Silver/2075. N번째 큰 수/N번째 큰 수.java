// n번째 큰 수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// x번째 큰 수
		int n = Integer.parseInt(br.readLine());

		// 정렬을 위한 우선순위 큐
		PriorityQueue<Integer> nums = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				nums.add(Integer.parseInt(st.nextToken()));

				// 만약 크기가 n을 초과하면 맨 앞에 값은 쳐내기
				if (nums.size() > n) {
					nums.poll();
				}
			}
		}

		// 출력
		System.out.println(nums.peek());
	}
}