import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Queue<Integer> q = new LinkedList<>();
		Integer last = null;
		
//		Scanner sc = new Scanner(System.in);
//		int max = sc.nextInt();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int max = Integer.parseInt(bf.readLine());
		
		for(int i=0; i<max; i++) {
			String[] str = bf.readLine().split(" ");
			
			switch(str[0]) {
			case "push" :
				int num = Integer.parseInt(str[1]);
				q.offer(num);
				last = num; 
				break;
				
			case "pop" : 
				Integer itg = q.poll();
				if(itg == null) { System.out.println("-1"); break; }
				else { System.out.println(itg); }
				break;
				
			case "size" :
				System.out.println(q.size()); 
				break;
				
			case "empty" :
				if(q.isEmpty()) { System.out.println(1); } 
				else { System.out.println(0); } 
				break;
				
			case "front" :
				if(q.isEmpty()) { System.out.println(-1); break; }
				System.out.println(q.peek());
				break;
				
			case "back" : 
				if(q.isEmpty()) { System.out.println(-1); break; }
				System.out.println(last); 
				break;

			default :
				System.out.println("illegal input"); break;
			}
		}
	}
}