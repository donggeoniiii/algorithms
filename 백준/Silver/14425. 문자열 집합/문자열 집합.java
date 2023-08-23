import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 집합의 크기 n, 입력받을 문자열의 개수 m
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 문자열 n개가 있는 집합 S
		HashMap<String, Integer> s = new HashMap<>();

		// 입력
		for (int i = 0; i < n; i++) {
			s.put(br.readLine(), 1);
		}

		// m 개의 문자열 입력하면서 집합에 있는지 확인
		int answer = 0;
		for (int i = 0; i < m; i++) {
			String input = br.readLine();
			if (s.getOrDefault(input, 0) == 1) {
				answer++;
			}
		}

		// 정답 출력
		System.out.println(answer);
	}
}