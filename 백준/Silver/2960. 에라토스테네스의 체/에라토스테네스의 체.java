
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Stack<Integer> nums = new Stack<>();
		int n = 2;
		
		while (nums.size() < K) {
			for (int i = 1; i * n < N + 1; i++) {
				if (nums.contains(i*n)) {
					continue;
				}
				nums.add(i * n);
				if (nums.size() == K) break;
			}
			n++;
		}		
		System.out.println(nums.pop());
	}
}
