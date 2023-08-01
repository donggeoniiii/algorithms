
// 텀 프로젝트

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 학생 수
	static int n;

	// 팀이 있는지 없는지 여부를 기록하는 배열(false: 모름, true: 팀 있음)
	static boolean[] checked;

	// 같은 팀을 하고 싶어하는 팀원의 이름
	static int[] teammate;

	// dfs를 위한 방문배열
	static boolean[] visited;

	// 팀이 있는 학생 수
	static int hasTeamCnt;

	private static void checkIfJoinProject(int cur) {
		// 방문했으니까 방문 처리
		visited[cur] = true;

		// 같이 하고 싶은 친구의 방문여부 체크
		int next = teammate[cur];

		// 만약 이어지는 친구가 방문이 되어있는데
		if (visited[next]) {

			// 아직 확인된 학생이 아니라면 사이클!
			if (!checked[next]) {
				hasTeamCnt++;
				// 사이클 내에 있는 나머지 조원들(나 제외)
				for (int student = next; student != cur; student = teammate[student]) {
					hasTeamCnt++;
				}
			}
		} else {
			// 처음 보는 애면 dfs on
			checkIfJoinProject(next);
		}

		// 팀 여부 확인 종료
		checked[cur] = true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 테케 개수
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			// 사람 수
			n = Integer.parseInt(br.readLine());

			// 사람 수+1해서 배열 생성
			checked = new boolean[n + 1];
			teammate = new int[n + 1];
			visited = new boolean[n + 1];

			// 팀 있는 사람 카운트
			hasTeamCnt = 0;

			// 선호조사 결과 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				teammate[i] = Integer.parseInt(st.nextToken());

				// 만약 자기 자신을 가리키고 있다면 바로 체크하고 팀 있다고 표시
				if (teammate[i] == i) {
					checked[i] = true;
					hasTeamCnt++;
				}
			}

			// 플젝을 진행할 수 있는지 여부 확인
			for (int i = 1; i <= n; i++) {
				if (!checked[i])
					checkIfJoinProject(i);
			}

			// 확인이 끝나면, 팀이 없는 사람의 수 기록
			sb.append(n - hasTeamCnt).append("\n");
		}

		// 정답 출력
		System.out.println(sb);
	}
}