// 트리순회

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 노드의 수
	static int n;

	// 왼쪽 노드, 오른쪽 노드를 저장할 배열
	static int[] left;
	static int[] right;

	// 출력형태 저장
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		left = new int[n+1];
		right = new int[n+1];

		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char cur = st.nextToken().charAt(0);
			char lc = st.nextToken().charAt(0);
			char rc = st.nextToken().charAt(0);

			// 아스키코드를 이용해 숫자 index로 변경
			// cdx == current idx
			int cdx = cur - 'A' + 1;
			left[cdx] = lc - 'A' + 1;
			right[cdx] = rc - 'A' + 1;
		}
	}

	private static void solution() {
		// 루트노드부터 순회
		preorder(1);
		sb.append("\n");
		inorder(1);
		sb.append("\n");
		postorder(1);

		// 결과 출력
		System.out.println(sb);
	}

	private static void preorder(int cdx) {
		// 부모 - 왼쪽 - 오른쪽
		sb.append((char) (cdx + 'A' - 1));

		// 자식이 없어 '.'이 입력되는 경우 값이 음수이므로
		if (left[cdx] > 0) {
			preorder(left[cdx]);
		}
		if (right[cdx] > 0) {
			preorder(right[cdx]);
		}
	}

	private static void inorder(int cdx) {
		// 왼쪽 - 부모 - 오른쪽
		if (left[cdx] > 0) {
			inorder(left[cdx]);
		}
		sb.append((char) (cdx + 'A' - 1));
		if (right[cdx] > 0) {
			inorder(right[cdx]);
		}
	}

	private static void postorder(int cdx) {
		// 왼쪽 - 오른쪽 - 부모
		if (left[cdx] > 0) {
			postorder(left[cdx]);
		}
		if (right[cdx] > 0) {
			postorder(right[cdx]);
		}
		sb.append((char) (cdx + 'A' - 1));
	}
}