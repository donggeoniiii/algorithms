// 카드 정렬하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	// 카드 뭉치의 수
	static int n;

	// 카드 뭉치별 개수
	static PriorityQueue<Integer> cards;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		cards = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			cards.add(Integer.parseInt(br.readLine()));
		}
	}

	private static void solution() {
		int answer = 0;

		// 제일 작은 거 두개씩 뽑아서 합치는게 최소
		// 최소 힙 형태로 넣으면서 정렬 -> pqueue 사용
		while (cards.size() > 1) {
			// 2개 뽑기
			int deck1 = cards.poll();
			int deck2 = cards.poll();

			// 합치기
			int sum = deck1 + deck2;

			// 카운트에 넣기
			answer += sum;

			// 다시 카드 뭉치 속에서 오름차순 정렬을 위해 pqueue에 넣기
			cards.add(sum);
		}

		// 최소 비교횟수 출력
		System.out.println(answer);
	}
}