// 나무 재테크

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 땅의 크기, 나무 정보, 햇수
	private static int n, m, k;

	// 양분 정보 배열
	private static int[][] power;

	// 현재 땅 상황
	private static int[][] land;

	private static class Tree {
		int r;
		int c;
		int age;
		int dead;

		Tree (int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
			this.dead = 0;
		}

	}

	// 땅 별 나무 정보 저장할 리스트
	private static List<Tree> tree;

	// 봄에 죽은 나무들의 index
	private static Queue<Integer> deadTree;

	// 주변에 나무를 심기 위한 델타배열
	private static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	private static int[] dc = {-1, 0, 1, -1, -1, 0, 1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		power = new int[n+1][n+1];
		for (int r = 1; r <= n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= n; c++) {
				power[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		land = new int[n+1][n+1];
		for (int r = 1; r <= n; r++) {
			Arrays.fill(land[r], 5);
		}

		tree = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());

			tree.add(new Tree(r, c, age));
		}

		// 현재 있는 나무 나이별로 정렬
		tree.sort(Comparator.comparingInt(o -> o.age));

		for (int year = 1; year <= k; year++) {
			// 봄: 나이만큼 양분을 넣고 나이를 증가시킴
			spring();

			// 여름: 봄에 죽은 나무가 양분이 됨
			summer();

			// 가을: 나무 번식, 팔방에 1짜리 나이 나무가 생김
			fall();

			// 겨울: 양분을 추가함
			winter();
		}

		// 나무의 개수 출력하기
		System.out.println(tree.size());
	}

	private static void spring() {
		deadTree = new LinkedList<>();
		// 어린 나무부터 양분 먹이기
		for (int tdx = 0; tdx < tree.size(); tdx++) {
			Tree ct = tree.get(tdx);
			int cr = ct.r;
			int cc = ct.c;
			int ca = ct.age;

			// 나무가 양분을 먹을 수 없으면 죽은 나무로 추가
			if (land[cr][cc] < ca) {
				deadTree.add(tdx);
				continue;
			}

			// 먹을 수 있으면 나이 올리기
			land[cr][cc] -= ca;
			ct.age++;
		}
	}

	private static void summer() {
		// 죽은 나무 양분으로 바꾸기
		while (!deadTree.isEmpty()) {
			Tree ct = tree.get(deadTree.poll());

			land[ct.r][ct.c] += ct.age / 2;
			ct.dead = 1; // 죽었다고 표시
		}
	}

	private static void fall() {
		List<Tree> newTree = new ArrayList<>();
		for (Tree t : tree) {
			// 죽은 나무 제외
			if (t.dead == 1) continue;

			// 나무 중에 번식할 수 있는 나무면
			if (t.age % 5 == 0) {
				// 번식시키기
				plant(newTree, t.r, t.c);
			}
		}

		// 새로운 리스트로 리뉴얼
		for (Tree t : tree) {
			if (t.dead == 1) continue;
			newTree.add(t);
		}
		tree = newTree;
	}

	private static void plant(List<Tree> newTree, int sr, int sc) {
		for (int dt = 0; dt < 8; dt++) {
			int nr = sr + dr[dt];
			int nc = sc + dc[dt];

			if (outOfIndex(nr, nc)) continue;

			// 1짜리 나무 심기
			newTree.add(new Tree(nr, nc, 1));
		}
	}

	private static boolean outOfIndex(int r, int c) {
		return r <= 0 || c <= 0 || r > n || c > n;
	}

	private static void winter() {
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				land[r][c] += power[r][c];
			}
		}
	}
}