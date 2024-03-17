// 운동

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 마을의 수, 도로의 수
	private static int v, e;

	// 최단거리 저장
	private static int[][] minDist;

	// 이동 불가능 표시
	private static final int IMPOSSIBLE = 987987987;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		minDist = new int[v+1][v+1];

		for (int i = 1; i <= v; i++) {
			Arrays.fill(minDist[i], IMPOSSIBLE);
		}

		for (int i = 1; i <= e; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			// 도로는 하나 뿐이므로 그대로 대입
			minDist[l][r] = dist;
		}
	}

	private static void solution() {
		for (int mid = 1; mid <= v; mid++) {
			for (int src = 1; src <= v; src++) {
				for (int dest = 1; dest <= v; dest++) {
					int curDist = minDist[src][mid] + minDist[mid][dest];
					minDist[src][dest] = Math.min(minDist[src][dest], curDist);
				}
			}
		}

		// 최소 길이가 되는 왕복 코스 찾기
		int answer = IMPOSSIBLE;
		for (int src = 1; src <= v; src++) {
			for (int dest = 1; dest <= v; dest++) {
				if (src == dest) continue;
				answer = Math.min(answer, minDist[src][dest] + minDist[dest][src]);
			}
		}

		System.out.println((answer == IMPOSSIBLE) ? -1 : answer);
	}
}