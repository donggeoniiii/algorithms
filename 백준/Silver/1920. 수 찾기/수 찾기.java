// 수 찾기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] nums;

	// 이분탐색 메소드
	static int isExist(int num) {
		// 시작점 끝점 설정
		int start = 0;
		int end = nums.length - 1;

		// 두 점이 만나기 전까지
		while (start <= end) {
			// 중간 지점
			int mid = (start + end) / 2;

			// 만약 찾으려는 수가 mid index의 값보다 크면
			if (num > nums[mid]) {

				// mid보다 작은 값들은 볼 필요 없으니까 시작 지점 변경
				start = mid + 1;
			}
			// 만약 찾으려는 수가 mid index의 값보다 작으면
			else if (num < nums[mid]) {

				// mid보다 큰 값들은 볼 필요 없으니까 끝 지점 변경
				end = mid - 1;
			}
			// 만약 mid 지점의 값이 정확히 찾는 값이면
			else {
				// 찾으려는 수가 있다고 알림
				return 1;
			}
		}

		// 다 뒤져도 안 나오면 없다고 알림
		return 0;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 주어지는 수의 개수
		int n = Integer.parseInt(br.readLine());

		// 배열에 저장
		nums = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// 이분탐색을 위해 정렬
		Arrays.sort(nums);

		// 찾으려는 수의 개수
		int m = Integer.parseInt(br.readLine());

		// 정답 형태 생성
		StringBuilder sb = new StringBuilder();

		// 찾으려는 수 입력하면서 존재하는지 찾기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			sb.append(isExist(Integer.parseInt(st.nextToken()))).append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}