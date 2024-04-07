// 달팽이는 올라가고 싶다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 올라가는 높이, 내려가는 높이, 목표
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		// 마지막날 오르기 전까지 걸리는 날 수
		int day = (v - a) / (a - b);

		// 만약 남은 높이가 있어 하루가 더 필요하면 추가
		if ((v-a) % (a-b) != 0) day++;

		// 마지막날 등반하며 완등
		System.out.println(day + 1);
	}
}