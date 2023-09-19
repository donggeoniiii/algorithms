import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 롤케이크 길이
		int l = Integer.parseInt(br.readLine());

		// 롤케이크 배열
		int[] cake = new int[l + 1];

		// 방청객의 수
		int n = Integer.parseInt(br.readLine());

		// 제일 많이 쓰인 조각
		int predictMaxCnt = -1;

		// 제일 많이 먹을 줄 알았던 사람
		int predictMax = 0;

		// 각 방청객이 종이에 적어낸 수
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 시작
			int start = Integer.parseInt(st.nextToken());

			// 끝
			int end = Integer.parseInt(st.nextToken());

			// 지금까지 쓴 범위중에 제일 넓으면 갱신
			if (end - start > predictMaxCnt) {
				predictMaxCnt = end - start;
				predictMax = i;
			}

			// 칸에 입력, 비어있는 경우만
			for (int j = start; j <= end; j++) {
				if (cake[j] == 0)
					cake[j] = i;
			}
		}

		// 방청객 별로 몇개나 가져가나 세기
		int maxCnt = -1;

		// 제일 많이 먹는 방청객
		int trueMax = 0;

		for (int i = 1; i <= n; i++) {
			// 이번 사람이 가져갈 케이크 조각 수
			int curCnt = 0;
			for (int j = 1; j <= l; j++) {
				if (cake[j] == i) {
					curCnt++;
				}
			}
			// 값을 갱신하면 방청객 번호 입력
			if (curCnt > maxCnt) {
				maxCnt = curCnt;
				trueMax = i;
			}
		}

		// 정답 출력
		System.out.println(predictMax + "\n" + trueMax);
	}
}