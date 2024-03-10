// 가운데를 말해요

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	// 정수의 개수
	static int n;

	// 정렬을 위해 사용할 힙 배열
	static int[] nums;

	// 현재 힙 배열의 크기
	static int total;

	// 최소 힙의 루트(최소값)
	static int root = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());

		nums = new int[100001];

		// 최소힙, 최대힙으로 사용할 두 우선순위 큐
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

		// 입력할 때마다 확인
		for (int i = 1; i <= n; i++) {
			int num = Integer.parseInt(br.readLine());

			// 양쪽 사이즈 맞추기
			if (minHeap.size() >= maxHeap.size()) {
				maxHeap.add(num);
			}
			else {
				minHeap.add(num);
			}

			if (!minHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
				minHeap.add(maxHeap.poll());
				maxHeap.add(minHeap.poll());
			}

			// 가운데 있는거 뽑기, 크기가 같으면 작은거 뽑는 거니까
			sb.append(maxHeap.peek()).append("\n");
		}

		// 출력
		System.out.println(sb);
	}
}