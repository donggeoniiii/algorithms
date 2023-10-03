import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 점수 입력
		int total = 0;
		for (int i = 0; i < 5; i++) {
			int score = Integer.parseInt(br.readLine());

			// 점수 입력
			total += Math.max(score, 40);
		}

		// 정답 출력
		System.out.println(total / 5);
	}
}