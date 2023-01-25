import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int T = input.nextInt();
        input.nextLine();
        
        for (int i = 0; i < T; i++) {
        	int A = input.nextInt();
        	int B = input.nextInt();
        	input.nextLine();
        	
        	System.out.printf("Case #%d: %d + %d = %d\n", i+1, A, B, A+B);
        }
        
    }
}
