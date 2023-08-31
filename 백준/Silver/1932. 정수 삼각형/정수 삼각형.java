// 정수삼각형

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 삼각형의 크기
		int n = Integer.parseInt(br.readLine());

		// 트리 구조를 저장할 배열
		int[][] intTree = new int[n][n];

		// 구조 저장
		for (int i = 0; i < n; i++) {
			// 트리에 넣을 숫자
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 입력
			for (int j = 0; j <= i; j++) {
				intTree[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// memoization 하면서 값 할당
		for (int i = 1; i < n; i++) {
			// 양 끝 부분은 비교해볼 필요 없음
			intTree[i][0] += intTree[i - 1][0];
			intTree[i][i] += intTree[i - 1][i - 1];

			// 가운데 낀 부분은 값 비교해서 입력
			for (int j = 1; j < i; j++) {
				intTree[i][j] += Math.max(intTree[i - 1][j - 1], intTree[i - 1][j]);
			}
		}

		// 마지막 줄에 있는 값 중에 제일 큰 값 찾기
		int answer = -1;
		for (int i = 0; i < n; i++) {
			answer = Math.max(answer, intTree[n - 1][i]);
		}

		// 정답 출력
		System.out.println(answer);
	}
}