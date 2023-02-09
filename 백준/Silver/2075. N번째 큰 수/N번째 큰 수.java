import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		int N = Integer.parseInt(bf.readLine());
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0; j<N; j++) {
				pq.offer(Integer.parseInt(st.nextToken()));
			}
		}
		
		for(int i=1; i<N; i++) {
			pq.poll();
		}
		System.out.println(pq.poll());
	}
}
