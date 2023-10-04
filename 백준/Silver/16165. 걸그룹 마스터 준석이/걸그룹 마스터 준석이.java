// 걸그룹 마스터 준석이

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		// 걸그룹의 수, 문제 수
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 입력받을 맵
		HashMap<String, String[]> group = new HashMap<>(); // 걸그룹 별 멤버 목록
		HashMap<String, String> idol = new HashMap<>(); // 멤버 별 소속 그룹

		// 입력하기
		for (int i = 0; i < n; i++) {
			// 그룹 이름
			String groupName = br.readLine();

			// 멤버 수
			int memberCnt = Integer.parseInt(br.readLine());

			// 멤버 저장할 배열
			String[] members = new String[memberCnt];

			// 그룹별로 멤버정보 저장
			for (int j = 0; j < memberCnt; j++) {
				// 이름
				String name = br.readLine();

				// 그룹 멤버 배열에 정보 입력
				members[j] = name;

				// 개인 멤버 정보 입력
				idol.put(name, groupName);
			}

			// 사전순 정렬
			Arrays.sort(members);

			// 그룹 정보 저장
			group.put(groupName, members);
		}

		// 문제 맞추기
		for (int i = 0; i < m; i++) {
			// 문제
			String question = br.readLine();

			// 문제 유형
			int questionType = Integer.parseInt(br.readLine());

			// 유형에 맞게 출력
			if (questionType == 1) {
				bw.write(idol.get(question));
				bw.write("\n");
			} else {
				String[] members = group.get(question);
				for (String member : members) {
					bw.write(member);
					bw.write("\n");
				}
			}
		}

		// 결과 출력
		bw.flush();
		bw.close();
		br.close();
	}
}