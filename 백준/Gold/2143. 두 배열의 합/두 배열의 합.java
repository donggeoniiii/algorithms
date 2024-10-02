// 두 배열의 합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// target
		long t = Long.parseLong(br.readLine());

		// 배열 A
		int n = Integer.parseInt(br.readLine());
		int[] arrA = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}

		// 배열 B
		int m = Integer.parseInt(br.readLine());
		int[] arrB = new int[m];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}


		// 구간합 구하기
		long[] subA = new long[n * (n + 1) / 2];
		int idx = 0;
		for (int i = 0; i < n; i++) {
			int curSum = 0;
			for (int j = i; j < n; j++) {
				curSum += arrA[j];
				subA[idx++] = curSum;
			}
		}

		long[] subB = new long[m * (m + 1) / 2];
		idx = 0;
		for (int i = 0; i < m; i++) {
			int curSum = 0;
			for (int j = i; j < m; j++) {
				curSum += arrB[j];
				subB[idx++] = curSum;
			}
		}

		// 이분탐색을 위해 구간합 정렬
		Arrays.sort(subA);
		Arrays.sort(subB);

		long answer = 0;

		idx = 0;
		while (idx < n * (n + 1) / 2) {
			long curA = subA[idx];
			long curB = t - curA;
			int aCnt = upperBound(subA, curA) - lowerBound(subA, curA);
			int bCnt = upperBound(subB, curB) - lowerBound(subB, curB);

			answer += (long) aCnt * bCnt;

			idx += aCnt; // 중복 체크 방지
		}

		System.out.println(answer);
	}

	// 최초로 큰 수가 등장하는 index 찾기
	private static int upperBound(long[] arr, long target) {
		int start = 0;
		int end = arr.length; // 배열의 값이 모두 target보다 작으면 index는 곧 length가 됨(모든 값보다 위)

		while (start < end) {
			int mid = (start + end) / 2;

			// 큰 값을 찾으면 최초인지 알기 위해 이 값을 기준으로 아래쪽에서 찾기
			if (arr[mid] > target) {
				end = mid;
			}

			// target 보다 작거나 같으면 더 위에서 찾아야 함
			else start = mid + 1;
		}

		return end;
	}

	// 같은 값이 등장, 또는 최초로 작은 값에서 큰 값이 등장하는 가장 낮은 index 찾기
	private static int lowerBound(long[] arr, long target) {
		int start = 0;
		int end = arr.length; // 배열의 값이 모두 target보다 작으면 index는 곧 length가 됨(모든 값보다 위)

		while (start < end) {
			int mid = (start + end) / 2;

			// 크거나 같은 값을 찾으면 아래에 target과 같은 값이 있는지 알기 위해 밑에서 탐색
			if (arr[mid] >= target) {
				end = mid;
			}

			// target 보다 더 작은 값을 만나면 위쪽에서 찾아야 함
			else start = mid + 1;
		}

		// end나 start 어떤 것을 반환해도 상관 없음(무조건 한 점으로 수렴)
		return end;
	}

}