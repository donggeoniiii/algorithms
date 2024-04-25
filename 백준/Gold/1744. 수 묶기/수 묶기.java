// 수묶기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> minus = new PriorityQueue<>(Comparator.comparingInt((Integer o) -> Math.abs(o)).reversed());

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num > 0) plus.add(num);
			else minus.add(num); // 0의 경우 음수가 홀수개일때 묶어서 처리하는게 제일 좋으므로
		}

		int answer = 0;
		while (!plus.isEmpty()) {
			int num1 = plus.poll();

			int curCal = 0;
			if (plus.isEmpty()) {
				curCal = num1;
			}
			else {
				int num2 = plus.poll();

				// 만약 더하는 두 수중 1이 있으면 더하는게 더 큼
				if (num1 == 1 || num2 == 1) {
					curCal = num1 + num2;
				}
				else curCal = (num1 * num2);
			}

			answer += curCal;
		}

		while (!minus.isEmpty()) {
			int num1 = minus.poll();

			int curCal = 0;
			if (minus.isEmpty()) {
				curCal = num1;
			}
			else {
				int num2 = minus.poll();
				curCal = (num1 * num2);
			}

			answer += curCal;
		}

		System.out.println(answer);
	}
}