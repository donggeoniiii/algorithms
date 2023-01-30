import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 2 ~ 10000사이 소수 구하기
		int[] numAr = new int[10001];
		numAr[0] = numAr[1] = 0;
		for(int i=2; i<=10000; i++) { numAr[i] = i; }
		
		ArrayList<Integer> prime = new ArrayList<>();
		for(int i=2; i<=10000; i++) {
			if(numAr[i] != 0) { prime.add(i); numAr[i] = 0; }
			for(int j=2; i*j<=10000; j++) { numAr[i*j] = 0; }
		}
		
		// for(Integer i : prime) { System.out.println(i);}
		
		// 골드바흐 구하기
		Scanner sc = new Scanner(System.in);
		int test = sc.nextInt();
		int[] tempAr = new int[test];
		for(int i=0; i<test; i++) {
			tempAr[i] = sc.nextInt()/2;
		}
		
		
		for(int i=0; i<tempAr.length; i++) {
			//System.out.println(test);
			//int num = sc.nextInt()/2;
			int numC = tempAr[i];
			while(prime.indexOf(numC) == -1) { numC--; }
			int idx = prime.indexOf(numC);
			
			for(int j=idx; j>=0; j--) {
				if(prime.indexOf(2*tempAr[i]-prime.get(j)) != -1) {
					System.out.println(prime.get(j) +" "
							+ prime.get(prime.indexOf(2*tempAr[i]-prime.get(j))));
					break;
				}
			}
		}
		
	}
}
