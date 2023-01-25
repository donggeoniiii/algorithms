import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int T = input.nextInt();
        input.nextLine();
        
        for (int i = 0; i < T; i++) {
        	String tc = input.nextLine();
        	String[] answer = tc.split(",");
        	
        	System.out.println(Integer.parseInt(answer[0])+Integer.parseInt(answer[1]));
        }
        
    }
}
