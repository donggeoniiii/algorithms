// 컨베이어 벨트 위의 로봇

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 벨트의 길이, 종료조건이 되는 내구도 0인 칸 수
	private static int n, k;

	// 벨트의 내구도
	private static int[] belt;

	// 각 칸별 로봇의 유무
	private static boolean[] isRobot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		belt = new int[2 * n + 1];
		isRobot = new boolean[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 2 * n; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		int level = 0;
		while (!terminated()) {
			// 이번 라운드 진행
			level++;

			// 컨베이어 이동
			moveConveyor();

			// 로봇 이동
			moveRobot();

			// 새로운 로봇 올리기
			addNewRobot();
		}
		System.out.println(level);
	}

	private static boolean terminated() {
		// 내구도가 0인 칸 수 세기
		int zeroCnt = 0;
		for (int idx = 1; idx <= 2 * n; idx++) {
			if (belt[idx] == 0) zeroCnt++;
		}

		return zeroCnt >= k;
	}

	private static void moveConveyor() {
		// 한 칸씩 이동
		int[] newBelt = new int[2 * n + 1];
		for (int i = 2; i <= 2 * n; i++) {
			newBelt[i] = belt[i - 1];
		}
		newBelt[1] = belt[2 * n];
		belt = newBelt;

		// 로봇은 올라온 순서대로 뒤에서부터
		for (int i = n-1; i >= 1; i--) {
			isRobot[i] = isRobot[i-1];
		}
		// 올리는 칸은 비어 있고 내리는 칸은 로봇이 내려갔을 거니까
		isRobot[1] = false;
		isRobot[n] = false;
	}

	private static void moveRobot() {
		// 로봇은 올라온 순서대로 뒤에서부터
		for (int i = n-1; i >= 1; i--) {
			// 현재 위치에 로봇이 없으면 스킵
			if (!isRobot[i]) continue;

			// 다음 위치에 로봇이 있으면 스킵
			if (isRobot[i+1]) continue;

			// 이동할 칸에 내구도가 안 남았으면 스킵
			if (belt[i+1] == 0) continue;

			// 원래 로봇이 있던 위치에 없다고 표시
			isRobot[i] = false;
			belt[i+1]--;

			// 다음 위치가 내리는 칸이면 바로 내려오고
			if (i+1 == n) continue;

			// 아니면 그 다음 칸으로 로봇 이동
			isRobot[i+1] = true;
		}
	}

	private static void addNewRobot() {
		if (belt[1] == 0) return;

		isRobot[1] = true;
		belt[1]--;
	}
}