import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// 재귀횟수 측정 위해 static int 변수 cnt 생성;
	static int cnt;
	public static int recursion(String string, int left, int right) {
		// index가 만나거나 교차한다면 1 반환
		if (left >= right) {
			return 1;
		// 그 전에 마주모는 단어가 다르다면 0 반환
		} else if (string.charAt(left) != string.charAt(right)) {
			return 0;
		// 더 가봐야 하면 index이동 후 재귀횟수 카운트 추가
		} else
			cnt++;
			return recursion(string, left+1, right-1);
	}
	
	public static int isPalindrome(String string) {
		return recursion(string, 0, string.length()-1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			// TestCase 바뀔때마다 카운트 초기화
			cnt = 1;
			String string = br.readLine();
			System.out.printf("%d %d\n", isPalindrome(string), cnt);
		}
		br.close();
	}
}
