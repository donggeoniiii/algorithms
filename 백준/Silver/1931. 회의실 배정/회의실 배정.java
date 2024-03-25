// 회의실 배정

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	// 회의의 수
	private static int n;
	
	// 회의 목록
	private static int[][] meet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		meet = new int[n][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				meet[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 회의 시작시간과 종료시간 순으로 정렬
		Arrays.sort(meet, Comparator.comparingInt((int[] o) -> o[1]).thenComparingInt(o -> o[0]));

		int curTime = 0;
		int meetCnt = 0;
		for (int i = 0; i < n; i++) {
			// 현재 시간 이전에 시작하는 회의면 패스
			if (meet[i][0] < curTime) continue;

			// 새 회의 추가, 시간 수정
			meetCnt++;
			curTime = meet[i][1];
		}

		System.out.println(meetCnt);
	}
}