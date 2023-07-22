import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 계단의 개수
	static int T;

	// 계단까지 최대 점수를 보관할 배열
	static int[] memo;

	// 계단 점수를 저장할 배열
	static int[] point;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 계단의 개수
		T = Integer.parseInt(br.readLine());

		// 계단 점수를 저장할 배열
		point = new int[T + 1];

		// 점수 저장
		for (int i = 1; i <= T; i++) {
			point[i] = Integer.parseInt(br.readLine());
		}

		// 계단까지 최대 점수를 보관할 배열
		memo = new int[T + 1];

		// 첫 3칸은 계산해서 넣고 시작
		memo[1] = point[1];

		// 입력이 '자연수'니까 그에 맞게
		if (T >= 2)
			memo[2] = point[1] + point[2];

		if (T >= 3)
			memo[3] = Math.max(point[1], point[2]) + point[3];

		if (T >= 4) {
			// 마지막 층까지 점수 계산
			for (int i = 4; i <= T; i++) {
				memo[i] = Math.max(memo[i - 2], memo[i - 3] + point[i - 1]) + point[i];
			}
		}
		// 정답 출력
		System.out.println(memo[T]);
	}
}