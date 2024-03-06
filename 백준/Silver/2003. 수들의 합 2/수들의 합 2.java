// 수들의 합2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 수열의 길이
	static int n;

	// 수열의 합
	static long m;

	// 수열
	static int[] nums;
	
	// 경우의 수
	static int answer;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void solution() {
		// 투포인터 알고리즘
		int curSum = 0;
		int start = 0;
		int end = 0;
		while (true) {
			// 부분합이 m이 넘거나 같으면 start 포인터를 땡기면서 합이 m이 되는 새 지점을 찾기
			if (curSum >= m) {
				curSum -= nums[start++];
			}
			// 부분합이 m보다 작을 때는 2가지를 생각해야 한다
			// 만약 out of index이면 어차피 더 커질 수 없으므로 종료
			else if (end == n) {
				break;
			}
			// out of index가 아니면 계속 end 포인터를 옮기며 새로운 수 합산
			else {
				curSum += nums[end++];
			}

			// 만약 이번 턴에 포인터 조작 후 부분합이 딱 m이면 카운트 증가
			if (curSum == m){
				answer++;
			}
		}

		System.out.println(answer);
	}
}