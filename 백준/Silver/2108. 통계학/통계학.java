// 통계학

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
	// 수의 개수
	private static int n;

	private static int[] num;
	private static int[] numCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		num = new int[n];
		numCnt = new int[8003];
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(br.readLine());
			numCnt[num[i]+4000]++;
		}

		StringBuilder sb = new StringBuilder();
		// 산술평균
		sb.append(solveAverage()).append("\n");

		// 중앙값
		sb.append(solveMedian()).append("\n");

		// 최빈값
		sb.append(solveMode()).append("\n");

		// 범위
		sb.append(solveRange()).append("\n");

		System.out.println(sb);
	}

	private static int solveAverage() {
		double avg = 0;

		for (int i = 0; i < n; i++) {
			avg += num[i];
		}

		avg /= n;

		return (int) Math.round(avg);
	}

	private static int solveMedian() {
		Arrays.sort(num);

		return num[(n-1)/2];
	}

	private static int solveMode() {
		List<int[]> cntList = new ArrayList<>();

		for (int i = 0; i <= 8000; i++) {
			if (numCnt[i] == 0) continue;

			cntList.add(new int[] {i - 4000, numCnt[i]});
		}

		// 빈도순으로 정렬
		cntList.sort(Comparator.comparingInt((int[] o) -> o[1]));

		int idx = cntList.size() - 1;
		while (idx > 0 && cntList.get(idx)[1] == cntList.get(idx-1)[1]) {
			idx--;
		}

		// 만약 겹치지 않으면 최빈값 그대로, 겹치면 최빈값 중 2번째로 작은 값 반환
		if (idx == cntList.size() - 1) return cntList.get(idx)[0];
		return cntList.get(idx+1)[0];
	}

	private static int solveRange() {
		return num[n-1] - num[0];
	}
}