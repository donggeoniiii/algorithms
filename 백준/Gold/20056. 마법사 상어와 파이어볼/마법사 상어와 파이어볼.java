// 마법사 상어와 파이어 볼

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 격자 크기, 파이어볼 개수, 이동 횟수
	private static int n, m, k;

	private static class Fire {
		int r;
		int c;
		int m;
		int d;
		int s;

		Fire (int r, int c, int m, int s, int d){
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	// 전체 파이어볼 리스트
	private static List<Fire> fireball;

	// 위치별 파이어볼 리스트
	private static List<Fire>[][] localFireball;

	private static final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	private static final int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		fireball = new ArrayList<>();
		localFireball = new ArrayList[n+1][n+1];
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				localFireball[r][c] = new ArrayList<>();
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			fireball.add(new Fire(r, c, m, s, d));
		}

		while (k-- > 0) {
			// phase 1: 파이어볼 이동
			move();

			// phase 2: 파이어볼 합체 후 분할
			divide();
		}

		// 남은 질량 합산
		int tm = 0;
		for (Fire f : fireball) {
			tm += f.m;
		}

		System.out.println(tm);
	}

	private static void move() {
		// 위치별 파이어볼 리스트 초기화
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				localFireball[r][c].clear();
			}
		}

		for (Fire f : fireball) {
			// 이동할 좌표 계산
			int nr = (f.r + dr[f.d] * f.s) % n;
			int nc = (f.c + dc[f.d] * f.s) % n;

			if (nr > n) nr -= n;
			if (nr < 1) nr += n;
			if (nc > n) nc -= n;
			if (nc < 1) nc += n;

			// 이동할 좌표에 추가
			localFireball[nr][nc].add(new Fire(nr, nc, f.m, f.s, f.d));
		}
	}

	private static void divide() {
		// 수정된 파이어볼 리스트
		List<Fire> newFireball = new ArrayList<>();

		// 위치별 파이어볼 합체
		for (int r = 1; r <= n; r++) {
			for (int c = 1; c <= n; c++) {
				int localCnt = localFireball[r][c].size();

				// 1개의 파이어볼만 있으면
				if (localCnt == 1) {
					newFireball.add(localFireball[r][c].get(0));
				}

				// 2개 이상의 파이어볼이 있는 칸이면
				if (localCnt >= 2) {
					// 합체
					int nm = 0;
					int ns = 0;
					boolean isAllOdds = true;
					boolean isAllEvens = true;
					for (Fire f : localFireball[r][c]) {
						nm += f.m;
						ns += f.s;
						if (f.d % 2 == 0) isAllOdds = false;
						else isAllEvens = false;
					}

					nm /= 5;
					ns /= localFireball[r][c].size();

					// 만약 합치고 나눠질 파이어볼 질량이 0이면 이후 과정 생략
					if (nm == 0) continue;

					// 방향 체크해서 4개의 파이어볼 생성
					int d = (isAllOdds || isAllEvens) ? 0 : 1;
					for (int dt = 0; dt < 4; dt++) {
						int nd = 2 * dt + d;

						newFireball.add(new Fire(r, c, nm, ns, nd));
					}
				}
			}
		}

		// 새 파이어볼 리스트로 변경
		fireball = newFireball;
	}
}