import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
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

	// 시작지점
	static int[] parent;

	// bfs로 위치 찾기
	static int findSister(int src) {

		// bfs를 위한 queue
		Queue<Soobin> queue = new LinkedList<>();

		// 방문배열
		boolean[] visited = new boolean[100001];

		// 이전 방문지점
		parent = new int[100001];

		// 시작점 입력
		queue.offer(new Soobin(src, 0));

		// 더 방문할 지점이 없을 때까지
		while (!queue.isEmpty()) {

			// 이번 방문좌표
			Soobin cur = queue.poll();
			int cdx = cur.idx;

			// 만약 방문한 좌표가 동생이 있는 좌표면
			if (cdx == sisterIdx) {
				// 종료
				return cur.move;
			}

			// 다음 이동
			for (int i = 0; i < 3; i++) {
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
				if (!visited[ndx]) {

					// 방문체크
					visited[ndx] = true;
					parent[ndx] = cdx;
					queue.offer(new Soobin(ndx, cur.move + 1));
				}
			}
		}

		// 못찾는 경우(그럴 일은 없다)
		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 수빈이가 있는 위치, 동생이 있는 위치
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		sisterIdx = Integer.parseInt(st.nextToken());

		// bfs on
		int fastestTime = findSister(n);

		// 반대로 출력하기 위해 스택에 넣고 거슬러 올라가기
		int idx = sisterIdx;
		Stack<Integer> reverseIdxStack = new Stack<>();
		reverseIdxStack.push(sisterIdx);
		while (idx != n) {
			reverseIdxStack.push(parent[idx]);
			idx = parent[idx];
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(fastestTime).append("\n");
		while (!reverseIdxStack.isEmpty()) {
			sb.append(reverseIdxStack.pop()).append(" ");
		}

		System.out.println(sb);
	}
}