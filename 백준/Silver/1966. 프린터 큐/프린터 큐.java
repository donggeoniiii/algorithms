// 프린터 큐

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < t; tc++) {
			// 문서의 개수, 몇 번째 인쇄인지 궁금한 문서
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			// 문서 저장하면서 최댓값 저장
			int maxPrior = -1;
			int[] priorCnt = new int[10];
			Queue<int[]> queue = new LinkedList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int prior = Integer.parseInt(st.nextToken());
				queue.offer(new int[] {i, prior});
				maxPrior = Math.max(maxPrior, prior);
				priorCnt[prior]++;
			}

			int printCnt = 0;
			while (!queue.isEmpty()) {
				// 이번에 출력할 문서
				int[] cur = queue.poll();
				int cdx = cur[0];
				int cp = cur[1]; // prior

				// 만약 현재 문서보다 중요도가 높은 문서가 있으면 다시 queue에 배치
				if (cp < maxPrior) {
					queue.offer(cur);
					continue;
				}

				// 아니면 출력, 현재 남은 문서중 최고 중요도 체크
				printCnt++;
				priorCnt[cp]--;
				if (priorCnt[maxPrior] == 0) {
					int pdx = maxPrior;
					while (pdx > 0 && priorCnt[pdx] == 0) pdx--;
					maxPrior = pdx;
				}

				// 만약 이번에 원하는 문서가 출력됐으면 종료
				if (cdx == m) break;
			}

			// 테스트케이스 결과 입력
			sb.append(printCnt).append("\n");
		}

		System.out.println(sb);


	}
}