import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] basket = new int[n+1];
		for (int i = 0; i < n + 1; i++) {
			basket[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int temp = basket[a];
			basket[a] = basket[b];
			basket[b] = temp;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n + 1; i++) {
			sb.append(basket[i]).append(" ");
		}
		System.out.println(sb);
	}
}