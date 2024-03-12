// 트리의 높이와 너비

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 노드의 개수
	static int n;

	// 왼쪽 자식 노드와 오른쪽 자식 노드
	static int[] left, right;

	// 탐색 기준이 될 루트 노드
	static int root;

	// 노드별 행, 열 index
	static int[] row, col;

	// 중위순회로 배정한 행 index
	static int rdx;

	// 최대 높이
	static int maxLevel;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		left = new int[n+1];
		right = new int[n+1];

		// 루트 노드를 찾기 위한 배열
		boolean[] isRoot = new boolean[n+1];
		Arrays.fill(isRoot, true);

		// 연결정보 입력
		StringTokenizer st;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			int lc = Integer.parseInt(st.nextToken());
			int rc = Integer.parseInt(st.nextToken());

			left[node] = lc;
			right[node] = rc;

			// 자식으로 달리는 애들은 루트일 수 없음
			if (lc != -1) {
				isRoot[lc] = false;
			}
			if (rc != -1) {
				isRoot[rc] = false;
			}
		}
		// 루트 노드 찾기
		for (int i = 1; i <= n; i++) {
			if (isRoot[i]) {
				root = i;
				break;
			}
		}

		row = new int[n+1];
		col = new int[n+1];
	}

	private static void solution() {
		// 너비(행 위치)와 높이(열 위치)는 중위순회를 통해 구할 수 있다
		inorder(root, 1);

		// 높이별로 탐색하기
		int maxDepth = -1;
		int maxWidth = -1;
		for (int depth = 1; depth <= maxLevel; depth++) {
			// 이번 열의 최소, 최대 index
			int minIdx = rdx + 1;
			int maxIdx = -1;
			for (int j = 1; j <= rdx; j++) {
				// 같은 열끼리 비교
				if (col[j] != depth) {
					continue;
				}

				// 최소, 최대 index 찾아놓기
				minIdx = Math.min(minIdx, row[j]);
				maxIdx = Math.max(maxIdx, row[j]);
			}

			// 이번 레벨의 너비
			int curWidth = maxIdx - minIdx + 1;

			// 최대값 갱신 시 저장
			if (curWidth > maxWidth) {
				maxDepth = depth;
				maxWidth = curWidth;
			}
		}

		// 정답 출력
		StringBuilder sb = new StringBuilder();
		sb.append(maxDepth).append(" ").append(maxWidth);
		System.out.println(sb);
	}

	private static void inorder(int node, int depth) {
		if (left[node] != -1) {
			inorder(left[node], depth + 1);
		}
		row[node] = ++rdx;
		col[node] = depth;
		maxLevel = Math.max(maxLevel, depth);
		if (right[node] != -1) {
			inorder(right[node], depth + 1);
		}
	}
}