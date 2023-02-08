import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Hashtable<String, String> ht = new Hashtable<>();
		
		String[] input = bf.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int searchNum = Integer.parseInt(input[1]);
		
		for(int i=0; i<n; i++) {
			String[] info = bf.readLine().split(" ");
			ht.put(info[0], info[1]);
		}
		
		for(int i=0; i<searchNum; i++) {
			System.out.println(ht.get(bf.readLine()));
		}
	}
}
