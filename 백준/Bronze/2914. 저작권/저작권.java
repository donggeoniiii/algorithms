import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 곡수
		int a = Integer.parseInt(st.nextToken());

		// 평균 멜로디
		int b = Integer.parseInt(st.nextToken());

		// 정답 출력
		System.out.println(a * (b - 1) + 1);
	}
}