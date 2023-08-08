// 개미

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 가로 세로길이
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		// 시작 위치
		st = new StringTokenizer(br.readLine());
		int sw = Integer.parseInt(st.nextToken());
		int sh = Integer.parseInt(st.nextToken());

		// 움직일 시간
		int t = Integer.parseInt(br.readLine());

		// 최종좌표
		// 어차피 위로 내려가고 아래로 내려가고가 완벽하게 한 주기니까, 2를 곱한 값으로 나머지를 구하자.
		int tw = w - Math.abs(w - (t + sw) % (2 * w));
		int th = h - Math.abs(h - (t + sh) % (2 * h));

		// 출력
		sb.append(tw).append(" ").append(th);
		System.out.println(sb);
	}
}