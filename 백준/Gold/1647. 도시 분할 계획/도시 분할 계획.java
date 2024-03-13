// 도시 분할 계획

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 집의 개수, 길의 개수
	static int n, m;

	// 선택 여부 저장 배열
	static boolean[] selected;

	// 연결 정보
	static List<int[]>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		selected = new boolean[n+1];

		adj = new ArrayList[n+1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		// 연결정보 저장
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[l].add(new int[] {r, cost});
			adj[r].add(new int[] {l, cost});
		}

		// 가격이 최소인 형태를 선택하기 위한 우선순위 큐
		PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));


		// 어차피 어디든 연결되어야 하니까 임의의 점에서 시작해도 됨
		selected[1] = true;
		for (int[] next : adj[1]) {
			queue.offer(next);
		}

		// 하나씩 뽑아가며 연결
		int wayCnt = 0;
		int totalCost = 0;
		int maxCost = -1;
		while (wayCnt < n-1) {
			// 가장 비용이 싼 간선 하나 뽑기
			int[] cur = queue.poll();
			int r = cur[0];
			int cost = cur[1];

			// 이미 선택된 루트라면 스킵
			if (selected[r])
				continue;

			// 다음에 이어지는 집은 포함 안할 때 추가
			selected[r] = true;
			totalCost += cost;
			maxCost = Math.max(maxCost, cost);
			wayCnt++;

			// 다음 지점 모두 추가
			for (int[] next : adj[r]) {
				// 이미 선택된 집은 패스
				if (selected[next[0]])
					continue;

				// 다음 지점 추가
				queue.offer(next);
			}
		}

		/*
		두 마을로 나누는 가장 싼 방법:
		모든 집을 최소 가격으로 연결하는 경우에서
		가장 비싼 가격을 가지는 길을 제외한다
		*/
		totalCost -= maxCost;
		System.out.println(totalCost);
	}
}