import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int num = Integer.parseInt(bf.readLine());
		System.out.println((int)Math.pow(2, num)-1);
		
		tap(num, "1", "3", "2", bw);
		bw.close();
	}
	
	public static void tap(int n, String start, String to, String via,
														BufferedWriter bw) throws IOException {
		if (n == 1) {
			bw.write(start +" "+ to +"\n");
		}
		else {
			tap(n-1, start, via, to, bw);
			bw.write(start +" "+ to +"\n");
			tap(n-1, via, to, start, bw);
		}
		
	}
}
