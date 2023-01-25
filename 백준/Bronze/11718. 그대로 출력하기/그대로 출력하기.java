import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int size = 0;
    
    while (size < 100 && input.hasNext()) {
    	System.out.println(input.nextLine());
    	size++;
    	}
        
    }
}
