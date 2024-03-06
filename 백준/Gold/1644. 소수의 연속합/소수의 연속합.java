// 소수의 연속합

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	// 소수의 합으로 나타낼 자연수
	static int n;

	// 소수
	static List<Integer> primeNums;

	// 소수의 합으로 표현 가능한 경우의 수
	static int answer;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		primeNums = new ArrayList<>();
	}

	private static void solution() {
		// 400만까지 소수 찾기(에라토스테네스의 체)
		boolean[] isPrime = new boolean[4000001];
		Arrays.fill(isPrime, true);
		for (int i = 2; i*i <= 4000000; i++) {
			// 이미 합성수로 판명난 수는 패스
			if (!isPrime[i]) {
				continue;
			}
			// 소수의 배수들은 모두 합성수니까 처리하기
			for (int j = i * i; j <= 4000000; j += i)
				isPrime[j] = false;
		}

		// 2보다 큰 소수 리스트에 추가
		for (int i = 2; i <= 4000000; i++) {
			if (isPrime[i]) {
				primeNums.add(i);
			}
		}

		// 투포인터 알고리즘으로 모든 경우 찾기
		int start = 0;
		int end = 0;

		// 현재까지 구한 수열의 부분합
		int curSum = 0;
		while (true) {
			// 구하려는 수보다 크거나 같으면 start 포인터를 옮기며 새로운 조합 찾기
			if (curSum >= n) {
				curSum -= primeNums.get(start++);
			}
			// 구하려는 수보다 작을 때는 2가지 경우가 있다
			// 포인터를 옮겼는데 마지막 소수까지 탐색이 끝났으면 종료
			else if (end == primeNums.size()) {
				break;
			}
			// 아직 end 포인트가 도달할 지점이 남았으면 end 포인터를 옮기며 합산
			else {
				curSum += primeNums.get(end++);
			}

			// 이번 시행 후에 합이 우리가 찾는 수면 카운트 증가
			if (curSum == n) {
				answer++;
			}
		}

		System.out.println(answer);
	}
}