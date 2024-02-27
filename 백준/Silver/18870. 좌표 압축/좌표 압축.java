// 좌표 압축

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 좌표의 개수
	static int n;

	// 좌표 모음
	static int[] location;

	// 정렬된 좌표 모음
	static int[] sortedLocation;

	// 중복 제거된 좌표 모음
	static List<Integer> uniqueSortedLocation;

	// 압축된 좌표 모음(정답)
	static long[] answer;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		answer();
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		location = new int[n];
		sortedLocation = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int curLoc = Integer.parseInt(st.nextToken());
			location[i] = curLoc;
			sortedLocation[i] = curLoc;
		}

		// 더 빠른 탐색을 위한 정렬
		Arrays.sort(sortedLocation);

		// 이분탐색을 위한 중복 제거 배열
		uniqueSortedLocation = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (i == 0 || sortedLocation[i-1] != sortedLocation[i]) {
				uniqueSortedLocation.add(sortedLocation[i]);
			}
		}

		// 정답 입력할 배열
		answer = new long[n];
	}

	private static void solution() {
		for (int i = 0; i < n; i++) {
			// 이번에 기준이 될 좌표값
			int curLoc = location[i];

			// 이분탐색으로 값 찾기
			answer[i] = findBiggerNums(curLoc, 0, uniqueSortedLocation.size() - 1);
		}
	}

	private static long findBiggerNums(int target, int start, int end) {
		// 중간 지점, 중간 지점의 좌표
		int mid = (start + end) / 2;
		int midNum = uniqueSortedLocation.get(mid);

		// 중간 지점의 좌표값이 정확히 찾으려는 값이면
		if (midNum == target) {
			// 이거보다 작은 값은 딱 이 좌표 위치만큼 있는 거니까 그대로 반환
			return mid;
		}
		// 만약 중간 지점의 좌표값보다 찾는 값이 더 작으면
		else if (midNum > target) {
			// 찾는 값보다 더 아래쪽에 있는 거니까 탐색범위 수정
			return findBiggerNums(target, start, mid - 1);
		}
		// 만약 중간 지점의 좌표값보다 찾는 값이 더 크면
		else {
			// 찾는 값보다 더 위쪽에 있는 거니까 탐색범위 수정
			return findBiggerNums(target, mid + 1, end);
		}
	}

	private static void answer() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb);
	}
}