// 국회의원 선거

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] candidates = new int[n];
		for (int i = 0; i < n; i++) {
			candidates[i] = Integer.parseInt(br.readLine());
		}

		int answer = 0;
		while (!dasomWins(candidates)) {
			// 제일 득표수 많은 놈 찾기
			int leader = findLeadingCandidate(candidates);

			// 한명 매수하기
			candidates[leader]--;
			candidates[0]++;
			answer++;
		}

		System.out.println(answer);
	}

	private static int findLeadingCandidate(int[] candidates) {
		int leadingCandidate = -1;
		int point = -1;

		for (int i = 1; i < candidates.length; i++) {
			if (candidates[i] > point) {
				leadingCandidate = i;
				point = candidates[i];
			}
		}

		return leadingCandidate;
	}

	private static boolean dasomWins(int[] candidates) {
		int point = candidates[0];

		for (int i = 1; i < candidates.length; i++) {
			if (candidates[i] >= point) return false;
		}

		return true;
	}
}