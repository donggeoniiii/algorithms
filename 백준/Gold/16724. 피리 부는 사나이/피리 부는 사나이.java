// 피리 부는 사나이

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	// 지도의 크기
	private static int n, m;

	private static char[][] map;

	private static int[] parent;
	private static int[] rank;

	private static int find(int idx) {
		if (idx == parent[idx])
			return idx;

		return parent[idx] = find(parent[idx]);
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y) return;

		if (rank[x] == rank[y]) rank[y]--;
		if (rank[x] > rank[y]) parent[y] = x;
		else parent[x] = y;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		parent = new int[n*m];
		rank = new int[n*m];
		for (int i = 0; i < n*m; i++) {
			parent[i] = i;
		}

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				int nr = r;
				int nc = c;

				switch (map[nr][nc]) {
					case 'U':
						nr--;
						break;
					case 'D':
						nr++;
						break;
					case 'L':
						nc--;
						break;
					case 'R':
						nc++;
						break;
				}
				
				if (outOfIndex(nr, nc)) continue;

				int cdx = r * m + c;
				int ndx = nr * m + nc;

				union(cdx, ndx);
			}
		}

		HashSet<Integer> union = new HashSet<>();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				int cdx = r * m + c;
				union.add(find(cdx));
			}
		}

		System.out.println(union.size());
	}

	private static boolean outOfIndex(int r, int c) {

		return r < 0 || c < 0 || r >= n || c >= m;
	}

	private static int[] move(int cr, int cc) {
		int nr = cr;
		int nc = cc;

		return new int[] {nr, nc};
	}
}