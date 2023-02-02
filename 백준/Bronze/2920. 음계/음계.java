
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] input = new int[8];
		for (int i = 0; i < 8; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] ascending = {1,2,3,4,5,6,7,8};
		int[] descending = {8,7,6,5,4,3,2,1};
		
		int acount = 0;
		for (int i = 0; i < 8; i++) {
			if (input[i] == ascending[i]) acount++;
		}
		
		int dcount = 0;
		for (int i = 0; i < 8; i++) {
			if (input[i] == descending[i]) dcount++;
		}
		
		if (acount == 8) {
			bw.write("ascending");
		} else if (dcount == 8) {
			bw.write("descending");
		} else 
			bw.write("mixed");
		
		bw.close();
	}
}
