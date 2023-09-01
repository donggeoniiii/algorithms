// 방배정

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 수학여행에 참여하는 학생 수, 방 최대 인원 수
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		// 학생 분류해서 숫자 저장할 배열
		int[][] students = new int[2][6];

		// 정보 저장하기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			// 성별
			int gender = Integer.parseInt(st.nextToken());

			// 학년
			int grade = Integer.parseInt(st.nextToken());

			// 저장
			students[gender][grade - 1]++;
		}

		// 필요한 방 수 세기
		int roomCnt = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				if (students[i][j] % k == 0)
					roomCnt += students[i][j] / k;
				else
					roomCnt += students[i][j] / k + 1;
			}
		}

		// 정답 출력
		System.out.println(roomCnt);
	}
}