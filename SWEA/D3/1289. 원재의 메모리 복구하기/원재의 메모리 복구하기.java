
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 개수
		int T = Integer.parseInt(br.readLine());
		
		/*
		 * 목표값에서 0 -> 1, 1-> 0으로 바뀌는 부분의 갯수가 최소 수정횟수
		 * charAt() 메소드를 이용해 앞뒤 값 비교, 다를때마다 cnt +1
		 * 돌고 나서 cnt 반환 
		 */
		for (int i = 1; i <= T; i++) {
			String s = br.readLine();
			int cnt = 0;
			
			// 맨 앞자리가 1인경우 전체를 1로 만들고 시작해야 하므로 cnt +1
			if (s.charAt(0) == '1') cnt++;
			
			for (int j = 1; j < s.length(); j++) {
				if (s.charAt(j) != s.charAt(j-1)) cnt++;
			}
			
			System.out.printf("#%d %d\n", i, cnt);
			
		}
	}
}