import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 이동 횟수와 위치를 갖고 있는 객체
	static class Soobin {
		int idx;
		int move;

		public Soobin(int idx, int move) {
			this.idx = idx;
			this.move = move;
		}
	}

	// 동생 좌표
	static int sisterIdx;

	// 동생 찾는 방법 수
	static int findCnt;

	// 가장 빠른 시간
	static int minMove;

	static void findSister(int src) {

		// bfs를 위한 queue
		Queue<Soobin> queue = new LinkedList<>();

		// 방문배열
		boolean[] visited = new boolean[100001];

		// 시작점 입력
		queue.offer(new Soobin(src, 0));

		// 방문체크
		visited[src] = true;

		// 더 방문할 지점이 없을 떄까지
		while (!queue.isEmpty()) {

			// 다음 방문 좌표 가져오기
			Soobin cur = queue.poll();
			int cdx = cur.idx;

			// 방문 체크
			visited[cdx] = true;

			for (int i = 0; i <= 2; i++) {
				int ndx = cdx;
				switch (i) {
					case 0:
						ndx++;
						break;
					case 1:
						ndx--;
						break;
					case 2:
						ndx *= 2;
						break;
				}

				// out of index
				if (ndx < 0 || ndx > 100000)
					continue;

				// 이미 방문했으면 스킵
				if (visited[ndx])
					continue;

				// 만약 방문한 좌표가 동생이 있는 좌표면
				if (ndx == sisterIdx) {
					// 이동 횟수로 최솟값 비교
					if (cur.move + 1 < minMove) {
						minMove = cur.move + 1;
						findCnt = 1;
					} else if (cur.move + 1 == minMove) {
						findCnt++;
					}
				}
				// 아니면 다음 탐색을 위해 추가
				else {
					queue.offer(new Soobin(ndx, cur.move + 1));
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 수빈이가 있는 위치, 동생이 있는 위치
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		sisterIdx = Integer.parseInt(st.nextToken());

		// 최소시간
		minMove = Integer.MAX_VALUE;

		if (n == sisterIdx) {
			minMove = 0;
			findCnt = 1;
		} else {
			// 찾기
			findSister(n);
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(minMove).append("\n").append(findCnt);
		System.out.println(sb);
	}
}