// 계보 복원가 호석

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 석호촌에 살고 있는 사람 수
	static int n;

	// 살고 있는 사람
	static String[] member;

	// 살고 있는 사람들을 index로 연결할 해시맵
	static HashMap<String, Integer> memberToIdx;

	// 정보의 개수
	static int m;

	// 자손 리스트
	static List<Integer>[] adj;

	// 시조 판별과 직속 자손 확인을 위한 indegree 배열
	static int[] indegree;

	// 자식들 저장할 리스트
	static List<String>[] children;

	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		member = new String[n];
		int idx = 0;
		while (st.hasMoreTokens()) {
			member[idx++] = st.nextToken();
		}
		// 사전순 출력을 위해 정렬
		Arrays.sort(member);

		// 해시맵으로 이름과 index 연결, member 배열과 상호작용해 사용
		memberToIdx = new HashMap<>();
		for (int i = 0; i < n; i++) {
			memberToIdx.put(member[i], i);
		}

		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		m = Integer.parseInt(br.readLine());

		indegree = new int[n];

		// 앞쪽이 자식, 뒤쪽이 조상님
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			// 해시 맵을 이용해서 index로 변환
			int child = memberToIdx.get(st.nextToken());
			int parent = memberToIdx.get(st.nextToken());

			// 부모쪽 연결리스트에 정보 저장
			adj[parent].add(child);

			// 자식의 indegree 1 증가
			indegree[child]++;
		}

		children = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			children[i] = new ArrayList<>();
		}
	}

	private static void solution() {
		// 출력형태
		StringBuilder sb = new StringBuilder();

		// 순회를 위한 queue
		Queue<Integer> queue = new LinkedList<>();

		// indegree 수치를 보고 root를 찾아 가문의 수 세기
		ArrayList<Integer> roots = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (indegree[i] == 0) {
				roots.add(i);
				queue.offer(i);
			}
		}
		sb.append(roots.size()).append("\n");

		// 시조들 이름 입력
		for (int idx : roots) {
			sb.append(member[idx]).append(" ");
		}
		sb.append("\n");

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			// 직속 자손 체크하기
			for (int next : adj[cur]) {
				indegree[next]--;
				if (indegree[next] == 0) {
					children[cur].add(member[next]);
					queue.offer(next);
				}
			}
		}

		// 한 명씩 돌아가면서 자식 관계 사전순 출력
		for (int i = 0; i < n; i++) {
			sb.append(member[i]).append(" ");
			
			sb.append(children[i].size()).append(" ");
			
			Collections.sort(children[i]);
			for (String child : children[i]) {
				sb.append(child).append(" ");
			}
			sb.append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}