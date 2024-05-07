// 가장 긴 짝수 연속한 부분 수열

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int l = 0, r = 0;
		int oddCnt = 0;
		int length = 0;
		int answer = 0;
		while (r < n) {
			// 이번자리 값이 짝수면 한칸 증가
			if (num[r] % 2 == 0) {
				length++;
				r++;
				answer = Math.max(answer, length - oddCnt);
			}
			// 이번자리 값이 홀수면
			else {
				// 카운트에 여유가 있을 때
				if (oddCnt < k) {
					// 한칸 이동하면서 길이 갱신
					oddCnt++;
					length++;
					answer = Math.max(answer, length - oddCnt);
					r++;
				}
				// 카운트에 여유가 없을 때
				else {
					// 두 포인터가 같은 지점이 아니면
					if (l < r) {
						// 왼쪽 자리 한칸 땡겨오기, 홀수면 카운트 감소
						length--;
						if (num[l] % 2 == 1) oddCnt--;
						l++;
					}
					// 같은 지점이면 여기서는 모든 경우를 확인했으니 두 포인터 모두 한칸씩 이동
					else {
						l++;
						r++;
					}
				}
			}
		}

		System.out.println(answer);
	}
}