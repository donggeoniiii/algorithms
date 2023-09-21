import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	별 4, 동그라미 3, 네모 2, 세모 1
	우선순위 별 > 동그라미 > 네모 > 세모
	 */
	static int checkWhoWins(int[] a, int[] b) {
		if (a[4] > b[4]) {
			return -1;
		} else if (a[4] < b[4]) {
			return 1;
		} else {
			if (a[3] > b[3]) {
				return -1;
			} else if (a[3] < b[3]) {
				return 1;
			} else {
				if (a[2] > b[2]) {
					return -1;
				} else if (a[2] < b[2]) {
					return 1;
				} else {
					if (a[1] > b[1]) {
						return -1;
					} else if (a[1] < b[1]) {
						return 1;
					} else {
						return 0;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 라운드수
		int n = Integer.parseInt(br.readLine());

		// 라운드별 경기 결과 출력
		for (int i = 0; i < n; i++) {
			// a,b가 낸 결과 저장 배열
			int[] resultA = new int[5];
			int[] resultB = new int[5];

			// a저장
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 첫 값은 날려도 됨
			st.nextToken();
			while (st.hasMoreTokens()) {
				resultA[Integer.parseInt(st.nextToken())]++;
			}

			// b저장
			st = new StringTokenizer(br.readLine());

			// 첫 값은 날려도 됨
			st.nextToken();
			while (st.hasMoreTokens()) {
				resultB[Integer.parseInt(st.nextToken())]++;
			}

			// 결과 체크
			int result = checkWhoWins(resultA, resultB);

			// 결과에 따라 결과 입력
			if (result == -1) {
				sb.append("A");
			} else if (result == 1) {
				sb.append("B");
			} else {
				sb.append("D");
			}
			sb.append("\n");
		}
		// 결과 출력
		System.out.println(sb);
	}
}