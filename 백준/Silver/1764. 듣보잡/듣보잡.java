import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 듣도 못한 사람 수 n, 보도 못한 사람 수 m
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 듣도 못한 사람 명단
		HashMap<String, Integer> neverHeard = new HashMap<>();

		// 듣도 못한 사람 입력
		for (int i = 0; i < n; i++) {
			neverHeard.put(br.readLine(), 1);
		}

		// 듣보잡 명단
		PriorityQueue<String> neverHeardAndSeen = new PriorityQueue<>();

		// 보도 못한 사람 입력하면서
		for (int i = 0; i < m; i++) {
			String input = br.readLine();

			// 듣지도 못한 사람인지 판단, 듣지도 못한 사람이면 듣보잡 명단에 추가
			if (neverHeard.getOrDefault(input, 0) == 1) {
				neverHeardAndSeen.offer(input);
			}
		}

		// 명단 게시
		StringBuilder sb = new StringBuilder();

		// 일단 듣보잡 명단의 크기
		sb.append(neverHeardAndSeen.size()).append("\n");

		// 듣보잡 리스트 입력
		while (!neverHeardAndSeen.isEmpty()) {
			sb.append(neverHeardAndSeen.poll()).append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}