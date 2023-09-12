// 두 수의 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 수열의 크기
		int n = Integer.parseInt(br.readLine());

		// 수열 입력하면서 최댓값 찾기
		int[] input = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < n; idx++) {
			input[idx] = Integer.parseInt(st.nextToken());
		}

		// 합해서 나와야 하는 값
		int x = Integer.parseInt(br.readLine());

		// 순서쌍 개수
		int answer = 0;

		// 수월한 탐색을 위한 정렬
		Arrays.sort(input);

		// 투포인터
		int idx1 = 0;
		int idx2 = n - 1;

		// 두 포인터가 교차하기 전까지
		while (idx1 < idx2) {
			// 두 index가 가리키는 배열의 값
			int sum = input[idx1] + input[idx2];

			// 두 수의 합이 원하는 값보다 작으면 앞에 포인터 땡기기
			if (sum < x)
				idx1++;
				// 두 수의 합이 원하는 값보다 크면 뒤 포인터 앞으로 땡기기
			else if (sum > x)
				idx2--;
				// 만약 같으면 정답 추가, 두 카운터 다 움직이기(하나 고정할 필요 없음, 어차피 서로 다른 수들이라 안나옴)
			else {
				answer++;
				idx1++;
				idx2--;
			}
		}

		// 정답 출력
		System.out.println(answer);
	}
}