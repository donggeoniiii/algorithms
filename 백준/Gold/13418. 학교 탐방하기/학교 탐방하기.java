// 학교 탐방하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	// 건물의 개수, 도로의 개수
	static int n, m;

	// 도로 정보 입력할 리스트
	static ArrayList<int[]> adj; // {left, right, 오르막(0) or 내리막(1)}

	// 선택 여부를 저장할 union-find 알고리즘
	static int[] root;

	static int find(int building) {
		// 경로 압축
		if (root[building] < 0) {
			return building;
		}

		return building = find(root[building]);
	}

	static boolean union(int b1, int b2) {
		b1 = find(b1);
		b2 = find(b2);

		if (b1 == b2) {
			return true;
		}
		if (root[b1] == root[b2]) {
			root[b1]--;
		}
		if (root[b1] < root[b2]) {
			root[b1] = b2;
		}
		else root[b2] = root[b1];

		return false;
	}

	static int answer;

	public static void main(String[] args) throws IOException {
		input();
		// 최악의 코스 -> 오르막길 우선 선택
		worstCase();
		// 최적의 코스 -> 내리막길 우선 선택
		bestCase();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adj = new ArrayList<>();

		for (int i = 1; i <= m+1; i++) {
			st = new StringTokenizer(br.readLine());

			int b1 = Integer.parseInt(st.nextToken());
			int b2 = Integer.parseInt(st.nextToken());
			int slope = Integer.parseInt(st.nextToken());

			// 양쪽으로 이동 가능하므로
			adj.add(new int[] {b1, b2, slope});
		}
	}

	private static void worstCase() {
		// 이미 선택한 건물을 체크하기 위한 root 배열
		root = new int[n+1];
		for (int i = 0; i <= n; i++) {
			root[i] = -1;
		}

		// 이동 가능한 길 중 오르막길을 먼저 봐야 하므로
		adj.sort(Comparator.comparingInt(o -> o[2]));

		int wayCnt = 0;
		int slopeCnt = 0;

		for (int i = 0; i <= m; i++) {
			int[] curWay = adj.get(i);
			int lb = curWay[0];
			int rb = curWay[1];
			int slope = curWay[2];

			// 이미 선택한 지점이 들어가면 스킵
			if (union(lb, rb)) {
				continue;
			}

			// 선택하지 않은 지점이 있으면 오르막길 카운트 갱신
			wayCnt++;
			if (slope == 0){
				slopeCnt ++;
			}
			// 만약 n개의 길을 선택하면 종료
			if (wayCnt == n) {
				break;
			}
		}

		// worstcase 정답 입력
		answer += slopeCnt * slopeCnt;
	}

	private static void bestCase() {
		// 이미 선택한 건물을 체크하기 위한 root 배열
		root = new int[n+1];
		for (int i = 0; i <= n; i++) {
			root[i] = -1;
		}

		// 이동 가능한 길 중 내리막길을 먼저 봐야 하므로
		Collections.reverse(adj);

		int wayCnt = 0;
		int slopeCnt = 0;

		for (int i = 0; i <= m; i++) {
			int[] curWay = adj.get(i);
			int lb = curWay[0];
			int rb = curWay[1];
			int slope = curWay[2];

			// 이미 선택한 지점이 들어가면 스킵
			if (union(lb, rb)) {
				continue;
			}

			// 선택하지 않은 지점이 있으면 오르막길 카운트 갱신
			wayCnt++;
			if (slope == 0) {
				slopeCnt++;
			}

			// 만약 n개의 길을 선택하면 종료
			if (wayCnt == n) {
				break;
			}
		}

		// bestcase 정답 입력
		answer -= slopeCnt * slopeCnt;
	}

	private static void solution() {
		// 정답 출력
		System.out.println(answer);
	}
}