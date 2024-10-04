import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		int answer = 0;
		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String sign = st.nextToken();
			int b = Integer.parseInt(st.nextToken());
			
			
			switch (sign) {
				case "+":
					answer += a+b;
					break;
				case "-":
					answer += a-b;
					break;
				case "*":
					answer += a*b;
					break;
				case "/":
					answer += a/b;
					break;
			}
		}
		
		System.out.println(answer);
	}
}