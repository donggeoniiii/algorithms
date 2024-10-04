// 케빈 베이컨의 6단계 법칙

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n+1];
		for (int i = 1; i < n+1; i++) {
			adj[i] = new ArrayList<>();
		}

		int m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj[a].add(b);
			adj[b].add(a);
		}

		// 케빈 베이컨 점수 기록기
		int[] score = new int[n+1];

		for (int src = 1; src < n+1; src++) {
			Queue<Integer> queue = new ArrayDeque<>();
			int[] visited = new int[n+1];
			Arrays.fill(visited, -1);

			queue.offer(src);
			visited[src] = 0;
			while (!queue.isEmpty()) {
				int cur = queue.poll();

				for (int next : adj[cur]) {
					if (visited[next] >= 0) continue;

					queue.offer(next);
					visited[next] = visited[cur] + 1;

				}
			}

			// 점수 합치기
			for (int i = 1; i < n+1; i++) {
				if (visited[i] == -1) continue;

				score[src] += visited[i];
			}
		}

		// 최소 점수 가진 사람 찾기
		int min = Integer.MAX_VALUE;
		int answer = 0;
		for (int i = 1; i < n+1; i++) {
			if (score[i] < min) {
				min = score[i];
				answer = i;
			}
		}

		System.out.println(answer);
	}
}