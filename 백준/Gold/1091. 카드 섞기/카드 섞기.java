// 카드 섞기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		// 카드 딜 정보
		int[] p = new int[n];
		int[] start = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
			start[i] = p[i];
		}

		// 섞기 정보
		int[] s = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}

		// 조건을 만족할 때까지 섞기
		int answer = 0;
		while (!isSuccess(p)) {
			// 순서 재배치
			p = shuffle(p, s);

			// 만약 결과가 시작과 같다면 해결 불가능
			if (Arrays.equals(p, start)) {
				answer = -1;
				break;
			}

			answer++;
		}

		// 출력
		System.out.println(answer);
	}

	private static int[] shuffle(int[] p, int[] s) {
		int[] cards = new int[p.length];

		for (int i = 0; i < p.length; i++) {
			// 이번에 배치할 카드
			int cur = p[i];

			// 이번에 배치될 자리
			int idx = s[i];

			cards[idx] = cur;
		}

		return cards;
	}

	private static boolean isSuccess(int[] p) {
		// 받는 대상이 순서에 안 맞으면 false
		for (int i = 0; i < p.length; i++) {
			if (i % 3 != p[i]) return false;
		}

		return true;
	}
}