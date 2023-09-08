// 집합의 표현

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// find 연산을 위한 대표값 저장 배열
	static int[] parent;

	// find 연산을 위한 rank 저장 배열
	static int[] rank;

	// find 연산(경로압축)
	static int findParent(int a) {
		if (parent[a] == a)
			return parent[a];
		else
			return parent[a] = findParent(parent[a]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 원소의 개수, 연산 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 대표값 저장 배열, rank 저장 배열
		parent = new int[n + 1];
		rank = new int[n + 1];
		// 대표값은 처음엔 자기 자신이니까, rank도 기본값은 0
		for (int i = 0; i < n + 1; i++) {
			parent[i] = i;
		}

		// 출력형태 생성
		StringBuilder sb = new StringBuilder();

		// 연산 횟수만큼 시행
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			// 연산의 종류
			int type = Integer.parseInt(st.nextToken());

			// 두 원소
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 연산의 종류가 0이면 합집합 연산
			if (type == 0) {
				parent[findParent(b)] = parent[findParent(a)];
			}
			// 연산의 종류가 1이면 두 원소가 같은 집합의 원소인지 확인
			else {
				sb.append(findParent(a) == findParent(b) ? "YES" : "NO").append("\n");
			}
		}

		// 결과 출력
		System.out.println(sb);
	}
}