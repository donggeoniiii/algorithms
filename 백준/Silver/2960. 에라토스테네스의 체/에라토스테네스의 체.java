import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int cnt = 0;
		int[] ar = new int[N+1];
		
		for(int i=0; i<=N; i++) {
			ar[i] = i;
		}
		
		ar[0] = ar[1] = 0; 
		
		for(int i=2; i<=N; i++) {
			for(int j=1; i*j<=N; j++) {
				if(ar[i*j] != 0) {  
					cnt++; 
					if(cnt == K) { System.out.println(ar[i*j]); }
					ar[i*j] = 0;
				}
			}
		}
		
	}
}
